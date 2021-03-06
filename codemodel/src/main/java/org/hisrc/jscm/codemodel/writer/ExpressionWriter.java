package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.util.List;

import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSAdditiveExpression.Additive;
import org.hisrc.jscm.codemodel.expression.JSArrayElement;
import org.hisrc.jscm.codemodel.expression.JSArrayElementVisitor;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression.Assignment;
import org.hisrc.jscm.codemodel.expression.JSBinaryExpression;
import org.hisrc.jscm.codemodel.expression.JSBitwiseANDExpression.Band;
import org.hisrc.jscm.codemodel.expression.JSBitwiseORExpression.Bor;
import org.hisrc.jscm.codemodel.expression.JSBitwiseXORExpression.Xor;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallArgs;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallElement;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallProperty;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.MemberCall;
import org.hisrc.jscm.codemodel.expression.JSConditionalExpression.Conditional;
import org.hisrc.jscm.codemodel.expression.JSElision;
import org.hisrc.jscm.codemodel.expression.JSEqualityExpression.Equality;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression.Comma;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression.Function;
import org.hisrc.jscm.codemodel.expression.JSIdentifierReference;
import org.hisrc.jscm.codemodel.expression.JSInvocationExpression;
import org.hisrc.jscm.codemodel.expression.JSLogicalANDExpression.And;
import org.hisrc.jscm.codemodel.expression.JSLogicalORExpression.Or;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberElement;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberNew;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberProperty;
import org.hisrc.jscm.codemodel.expression.JSMultiplicativeExpression.Multiplicative;
import org.hisrc.jscm.codemodel.expression.JSNewExpression.New;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSPropertyAssignment;
import org.hisrc.jscm.codemodel.expression.JSPostfixExpression.Postfix;
import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression.Brackets;
import org.hisrc.jscm.codemodel.expression.JSRelationalExpression.Relational;
import org.hisrc.jscm.codemodel.expression.JSShiftExpression.Shift;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.expression.JSUnaryExpression.Unary;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSLiteral;

public class ExpressionWriter implements
		JSExpressionVisitor<CodeWriter, IOException> {

	protected final CodeWriter f;

	public ExpressionWriter(CodeWriter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public CodeWriter visitThis(JSThis value) throws IOException {
		f.keyword("this");
		return f;
	}

	@Override
	public CodeWriter visitIdentifierReference(JSIdentifierReference value)
			throws IOException {
		f.identifier(value.getName());
		return f;
	}

	// @Override
	// public CodeWriter visitVariable(JSVariable value) throws IOException {
	// f.identifier(value.getName());
	// return f;
	// }

	// @Override
	// public CodeWriter visitGlobalVariable(JSGlobalVariable value)
	// throws IOException {
	// f.identifier(value.getName());
	// return f;
	// }

	@Override
	public CodeWriter visitLiteral(JSLiteral value) throws IOException {
		return f.literal(value);
	}

	@Override
	public CodeWriter visitArrayLiteral(JSArrayLiteral value)
			throws IOException {

		f.openSquareBracket();
		f.indent();

		final List<JSArrayElement> elements = value.getElements();
		final int elementsCount = elements.size();

		for (int index = 0; index < elementsCount; index++) {
			final boolean first = (index == 0);
			final boolean last = (index == elementsCount - 1);
			final JSArrayElement element = elements.get(index);

			element.acceptArrayElementVisitor(new JSArrayElementVisitor<CodeWriter, IOException>() {

				@Override
				public CodeWriter visitAssignment(
						JSAssignmentExpression expression) throws IOException {
					if (!first) {
						f.whiteSpace();
					}
					f.expression(expression);
					if (!last) {
						f.comma();
					}
					return f;
				}

				@Override
				public CodeWriter visitElision(JSElision value)
						throws IOException {
					return f.whiteSpace().comma();
				}
			});

		}
		f.unindent();
		f.closeSquareBracket();

		return f;
	}

	@Override
	public CodeWriter visitObjectLiteral(JSObjectLiteral value)
			throws IOException {

		f.openCurlyBracket();

		final List<JSPropertyAssignment> propertyAssignments = value
				.getPropertyAssignments();
		if (!propertyAssignments.isEmpty()) {

			f.lineTerminator();

			f.indent();

			for (int index = 0; index < propertyAssignments.size(); index++) {
				if (index > 0) {
					f.comma().lineTerminator();
				}
				final JSPropertyAssignment propertyAssignment = propertyAssignments
						.get(index);
				f.propertyAssignment(propertyAssignment);
			}
			f.unindent();
			f.lineTerminator();
		}
		f.closeCurlyBracket();
		return f;
	}

	@Override
	public CodeWriter visitBrackets(Brackets value) throws IOException {
		f.openRoundBracket();
		f.indent().expression(value.getBase()).unindent();
		f.closeRoundBracket();
		return f;
	}

	@Override
	public CodeWriter visitFunction(Function value) throws IOException {

		f.keyword("function").whiteSpace();

		if (value.getName() != null) {
			f.identifier(value.getName());
		}

		f.openRoundBracket();
		for (int index = 0; index < value.getParameters().size(); index++) {
			final JSVariable parameter = value.getParameters().get(index);
			if (index > 0) {
				f.comma().whiteSpace();
			}
			f.identifier(parameter.getName());
		}
		f.closeRoundBracket().whiteSpace();
		f.openCurlyBracket().lineTerminator();

		f.indent();
		final List<JSSourceElement> sourceElements = value.getBody()
				.getSourceElements();
		for (int index = 0; index < sourceElements.size(); index++) {
			final JSSourceElement sourceElement = sourceElements.get(index);
			f.sourceElement(sourceElement);
			f.lineTerminator();
		}
		f.unindent();
		f.closeCurlyBracket();
		return f;
	}

	@Override
	public CodeWriter visitMemberElement(MemberElement value)
			throws IOException {
		f.expression(value.getBase());
		f.openSquareBracket();
		f.indent().expression(value.getIndex()).unindent();
		f.closeSquareBracket();
		return f;
	}

	@Override
	public CodeWriter visitMemberProperty(MemberProperty value)
			throws IOException {
		f.expression(value.getBase());
		f.dot();
		f.indent().propertyName(value.getName()).unindent();
		return f;
	}

	@Override
	public CodeWriter visitMemberNew(MemberNew value) throws IOException {
		f.keyword("new").whiteSpace();
		return visitInvocation(value);
	}

	protected CodeWriter visitInvocation(JSInvocationExpression value)
			throws IOException {
		f.expression(value.getBase());
		visitInvocationArgs(value.getArgs());
		return f;
	}

	protected CodeWriter visitInvocationArgs(List<JSAssignmentExpression> args)
			throws IOException {
		f.openRoundBracket();

		f.indent();

		for (int index = 0; index < args.size(); index++) {
			if (index > 0) {
				f.comma().whiteSpace();

			}
			JSExpression arg = args.get(index);
			f.expression(arg);
		}
		f.unindent();
		f.closeRoundBracket();
		return f;
	}

	@Override
	public CodeWriter visitMemberCall(MemberCall value) throws IOException {
		return visitInvocation(value);
	}

	@Override
	public CodeWriter visitNew(New value) throws IOException {
		f.keyword("new").whiteSpace();
		f.expression(value.getBase());
		return f;
	}

	@Override
	public CodeWriter visitCallArgs(CallArgs value) throws IOException {
		return visitInvocation(value);
	}

	@Override
	public CodeWriter visitCallElement(CallElement value) throws IOException {
		f.expression(value.getBase());
		f.openSquareBracket();
		f.indent().expression(value.getIndex()).unindent();
		f.closeSquareBracket();
		return f;
	}

	@Override
	public CodeWriter visitCallProperty(CallProperty value) throws IOException {
		f.expression(value.getBase());
		f.dot();
		f.indent().propertyName(value.getName()).unindent();
		return f;
	}

	@Override
	public CodeWriter visitPostfix(Postfix value) throws IOException {
		f.expression(value.getBase());
		f.operator(value.getOperator());
		return f;
	}

	@Override
	public CodeWriter visitUnary(Unary value) throws IOException {
		f.operator(value.getOperator());
		f.expression(value.getBase());
		return f;
	}

	@Override
	public CodeWriter visitMultiplicative(Multiplicative value)
			throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitAdditive(Additive value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitShift(Shift value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitRelational(Relational value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitEquality(Equality value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitBand(Band value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitXor(Xor value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitBor(Bor value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitAnd(And value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitOr(Or value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitConditional(Conditional value) throws IOException {
		f.expression(value.getCondition());
		f.whiteSpace();
		f.questionMark();
		f.whiteSpace();
		f.indent().expression(value.getIfTrue()).unindent();
		f.whiteSpace();
		f.colon();
		f.whiteSpace();
		f.indent().expression(value.getIfFalse()).unindent();
		return f;
	}

	@Override
	public CodeWriter visitAssignment(Assignment value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public CodeWriter visitComma(Comma value) throws IOException {
		f.expression(value.getLeft());
		f.comma().whiteSpace();
		f.indent().expression(value.getRight()).unindent();
		return f;
	}

	private CodeWriter visitBinaryExpression(JSBinaryExpression value)
			throws IOException {
		f.expression(value.getLeft());
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

}
