package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSAdditiveExpression.Additive;
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
import org.hisrc.jscm.codemodel.expression.JSEqualityExpression.Equality;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression.Comma;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression.Function;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
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
import org.hisrc.jscm.codemodel.literal.JSLiteral;

public class ExpressionWriter implements
		JSExpressionVisitor<Formatter, IOException> {

	private final Formatter f;

	public ExpressionWriter(Formatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public Formatter visitThis(JSThis value) throws IOException {
		f.keyword("this");
		return f;
	}

	@Override
	public Formatter visitVariable(JSVariable value) throws IOException {
		f.identifier(value.getName());
		return f;
	}

	@Override
	public Formatter visitGlobalVariable(JSGlobalVariable value)
			throws IOException {
		f.identifier(value.getName());
		return f;
	}

	@Override
	public Formatter visitLiteral(JSLiteral value) throws IOException {
		return f.literal(value);
	}

	@Override
	public Formatter visitArrayLiteral(JSArrayLiteral value)
			throws IOException {

		f.openSquareBracket();
		final Formatter fi = f.indented();

		for (int index = 0; index < value.getElements().size(); index++) {
			if (index > 0) {
				fi.comma().whiteSpace();
			}
			final JSAssignmentExpression element = value.getElements().get(
					index);
			fi.expression(element);

		}
		f.closeSquareBracket();

		return f;
	}

	@Override
	public Formatter visitObjectLiteral(JSObjectLiteral value)
			throws IOException {

		f.openCurlyBracket();

		final List<JSPropertyAssignment> propertyAssignments = value
				.getPropertyAssignments();
		if (!propertyAssignments.isEmpty()) {

			f.lineTerminator();

			final Formatter fi = f.indented();

			for (int index = 0; index < propertyAssignments.size(); index++) {
				if (index > 0) {
					fi.comma().lineTerminator();
				}
				final JSPropertyAssignment propertyAssignment = propertyAssignments
						.get(index);

				final JSPropertyName propertyName = propertyAssignment.getKey();
				final JSAssignmentExpression propertyValue = propertyAssignment
						.getValue();
				fi.propertyName(propertyName).colon().whiteSpace()
						.expression(propertyValue);
			}
			f.lineTerminator();
		}
		f.closeCurlyBracket();
		return f;
	}

	@Override
	public Formatter visitBrackets(Brackets value) throws IOException {
		f.openRoundBracket();
		f.indented().expression(value.getBase());
		f.closeRoundBracket();
		return f;
	}

	@Override
	public Formatter visitFunction(Function value) throws IOException {

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

		final Formatter fi = f.indented();
		final List<JSSourceElement> sourceElements = value.getBody()
				.getSourceElements();
		for (int index = 0; index < sourceElements.size(); index++) {
			if (index > 0) {
				fi.lineTerminator();
			}
			final JSSourceElement sourceElement = sourceElements.get(index);
			fi.sourceElement(sourceElement);
		}

		f.closeCurlyBracket();
		return f;
	}

	@Override
	public Formatter visitMemberElement(MemberElement value)
			throws IOException {

		f.expression(value.getBase());
		f.openSquareBracket();
		f.indented().expression(value.getIndex());
		f.closeSquareBracket();
		return f;
	}

	@Override
	public Formatter visitMemberProperty(MemberProperty value)
			throws IOException {
		f.expression(value.getBase());
		f.dot();
		f.indented().propertyName(value.getName());
		return f;
	}

	@Override
	public Formatter visitMemberNew(MemberNew value) throws IOException {
		f.keyword("new").whiteSpace();
		return visitInvocation(value);
	}

	private Formatter visitInvocation(JSInvocationExpression value)
			throws IOException {
		f.expression(value.getBase());

		f.openRoundBracket();

		final Formatter fi = f.indented();
		List<JSAssignmentExpression> args = value.getArgs();

		for (int index = 0; index < args.size(); index++) {
			if (index > 0) {
				fi.comma().whiteSpace();

			}
			JSExpression arg = args.get(index);
			fi.expression(arg);
		}

		f.closeRoundBracket();
		return f;
	}

	@Override
	public Formatter visitMemberCall(MemberCall value) throws IOException {
		return visitInvocation(value);
	}

	@Override
	public Formatter visitNew(New value) throws IOException {
		f.keyword("new").whiteSpace();
		f.expression(value.getBase());
		return f;
	}

	@Override
	public Formatter visitCallArgs(CallArgs value) throws IOException {
		return visitInvocation(value);
	}

	@Override
	public Formatter visitCallElement(CallElement value) throws IOException {
		f.expression(value.getBase());
		f.openSquareBracket();
		f.indented().expression(value.getIndex());
		f.closeSquareBracket();
		return f;
	}

	@Override
	public Formatter visitCallProperty(CallProperty value) throws IOException {
		f.expression(value.getBase());
		f.dot();
		f.indented().propertyName(value.getName());
		return f;
	}

	@Override
	public Formatter visitPostfix(Postfix value) throws IOException {
		f.expression(value.getBase());
		f.operator(value.getOperator());
		return f;
	}

	@Override
	public Formatter visitUnary(Unary value) throws IOException {
		f.operator(value.getOperator());
		f.expression(value.getBase());
		return f;
	}

	@Override
	public Formatter visitMultiplicative(Multiplicative value)
			throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitAdditive(Additive value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitShift(Shift value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitRelational(Relational value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitEquality(Equality value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitBand(Band value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitXor(Xor value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitBor(Bor value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitAnd(And value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitOr(Or value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitConditional(Conditional value) throws IOException {
		f.expression(value.getCondition());
		f.whiteSpace();
		f.questionMark();
		f.whiteSpace();
		f.indented().expression(value.getIfTrue());
		f.whiteSpace();
		f.colon();
		f.whiteSpace();
		f.indented().expression(value.getIfFalse());
		return f;
	}

	@Override
	public Formatter visitAssignment(Assignment value) throws IOException {
		return visitBinaryExpression(value);
	}

	@Override
	public Formatter visitComma(Comma value) throws IOException {
		f.expression(value.getLeft());
		f.comma().whiteSpace();
		f.indented().expression(value.getRight());
		return f;
	}

	private Formatter visitBinaryExpression(JSBinaryExpression value)
			throws IOException {
		f.expression(value.getLeft());
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}
}
