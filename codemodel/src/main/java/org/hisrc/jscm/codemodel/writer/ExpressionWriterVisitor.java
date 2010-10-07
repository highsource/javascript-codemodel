package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.expression.JSAdditiveExpression.Additive;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression.Assignment;
import org.hisrc.jscm.codemodel.expression.JSBitwiseANDExpression.Band;
import org.hisrc.jscm.codemodel.expression.JSBitwiseORExpression.Bor;
import org.hisrc.jscm.codemodel.expression.JSBitwiseXORExpression.Xor;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallArgs;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallElement;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallProperty;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.MemberCall;
import org.hisrc.jscm.codemodel.expression.JSConditionalExpression.Conditional;
import org.hisrc.jscm.codemodel.expression.JSEqualityExpression.Equality;
import org.hisrc.jscm.codemodel.expression.JSExpression.Comma;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression.Function;
import org.hisrc.jscm.codemodel.expression.JSLogicalANDExpression.And;
import org.hisrc.jscm.codemodel.expression.JSLogicalORExpression.Or;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberElement;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberNew;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberProperty;
import org.hisrc.jscm.codemodel.expression.JSMultiplicativeExpression.Multiplicative;
import org.hisrc.jscm.codemodel.expression.JSNewExpression.New;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSPropertyAssignment;
import org.hisrc.jscm.codemodel.expression.JSPostfixExpression.Postfix;
import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression.Brackets;
import org.hisrc.jscm.codemodel.expression.JSRelationalExpression.Relational;
import org.hisrc.jscm.codemodel.expression.JSShiftExpression.Shift;
import org.hisrc.jscm.codemodel.expression.JSUnaryExpression.Unary;
import org.hisrc.jscm.codemodel.literal.JSLiteral;

public class ExpressionWriterVisitor implements
		JSExpressionVisitor<JSFormatter, IOException> {

	private final JSFormatter f;

	public ExpressionWriterVisitor(JSFormatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public JSFormatter visitThis(JSThis value) throws IOException {
		f.keyword("this");
		return f;
	}

	@Override
	public JSFormatter visitVariable(JSVariable value) throws IOException {
		f.identifier(value.getName());
		return f;
	}

	@Override
	public JSFormatter visitGlobalVariable(JSGlobalVariable value)
			throws IOException {
		f.identifier(value.getName());
		return f;
	}

	@Override
	public JSFormatter visitLiteral(JSLiteral value) throws IOException {
		value.acceptLiteralVisitor(new LiteralWriterVisitor(f));
		return f;
	}

	@Override
	public JSFormatter visitArrayLiteral(JSArrayLiteral value)
			throws IOException {

		f.openSquareBracket();

		for (int index = 0; index < value.getElements().size(); index++) {
			final JSAssignmentExpression element = value.getElements().get(
					index);
			element.acceptExpressionVisitor(indented());
			if (index < value.getElements().size() - 1) {
				f.comma();
			}

		}
		f.closeSquareBracket();

		return f;
	}

	@Override
	public JSFormatter visitObjectLiteral(JSObjectLiteral value)
			throws IOException {

		f.openCurlyBracket();

		final JSFormatter fi = f.indented();
		final ExpressionWriterVisitor vi = indented();

		for (int index = 0; index < value.getPropertyAssignments().size(); index++) {
			if (index > 0) {
				fi.comma();
			}
			// fi.lineBreak();

			final JSPropertyAssignment propertyAssignment = value
					.getPropertyAssignments().get(index);

			final JSPropertyName propertyName = propertyAssignment.getKey();
			final JSAssignmentExpression propertyValue = propertyAssignment
					.getValue();
			propertyName
					.acceptPropertyNameVisitor(new PropertyNameWriterVisitor(f));
			fi.colon();

			propertyValue.acceptExpressionVisitor(vi);
		}
		f.closeCurlyBracket();
		return f;
	}

	@Override
	public JSFormatter visitBrackets(Brackets value) throws IOException {
		f.openRoundBracket();
		value.getBase().acceptExpressionVisitor(indented());
		f.closeRoundBracket();
		return f;
	}

	@Override
	public JSFormatter visitFunction(Function value) throws IOException {

		f.keyword("function");

		if (value.getName() != null) {
			f.identifier(value.getName());
		}

		f.openRoundBracket();
		for (int index = 0; index < value.getParameters().size(); index++) {
			final JSVariable parameter = value.getParameters().get(index);
			if (index > 0) {
				f.comma();
			}
			f.identifier(parameter.getName());
		}
		f.closeRoundBracket();
		f.startBlock();

		for (JSSourceElement sourceElement : value.getBody()
				.getSourceElements()) {
			sourceElement
					.acceptSourceElementVisitor(new SourceElementWriterVisitor(
							f.indented()));
		}

		f.endBlock();
		return f;
	}

	@Override
	public JSFormatter visitMemberElement(MemberElement value)
			throws IOException {

		value.getBase().acceptExpressionVisitor(this);
		f.openSquareBracket();
		value.getIndex().acceptExpressionVisitor(indented());
		f.closeSquareBracket();
		return f;
	}

	@Override
	public JSFormatter visitMemberProperty(MemberProperty value)
			throws IOException {
		value.getBase().acceptExpressionVisitor(this);
		f.dot();
		value.getName().acceptPropertyNameVisitor(
				new PropertyNameWriterVisitor(f));
		return f;
	}

	@Override
	public JSFormatter visitMemberNew(MemberNew value) throws IOException {
		f.keyword("new");
		value.getBase().acceptExpressionVisitor(this);
		f.openRoundBracket();

		for (int index = 0; index < value.getArgs().size(); index++) {
			final JSAssignmentExpression arg = value.getArgs().get(index);

			arg.acceptExpressionVisitor(this);
			if (index < value.getArgs().size() - 1) {
				f.comma();
			}
		}

		f.closeRoundBracket();

		return f;
	}

	@Override
	public JSFormatter visitMemberCall(MemberCall value) throws IOException {
		value.getBase().acceptExpressionVisitor(this);
		f.openRoundBracket();

		for (int index = 0; index < value.getArgs().size(); index++) {
			if (index > 0) {
				f.comma();
			}
			final JSAssignmentExpression arg = value.getArgs().get(index);

			arg.acceptExpressionVisitor(indented());
		}

		f.closeRoundBracket();

		return f;
	}

	@Override
	public JSFormatter visitNew(New value) throws IOException {
		f.keyword("new");
		value.getBase().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitCallArgs(CallArgs value) throws IOException {
		value.getBase().acceptExpressionVisitor(this);
		f.openRoundBracket();

		for (int index = 0; index < value.getArgs().size(); index++) {
			if (index > 0) {
				f.comma();
			}
			final JSAssignmentExpression arg = value.getArgs().get(index);

			arg.acceptExpressionVisitor(indented());
		}
		f.closeRoundBracket();
		return f;
	}

	@Override
	public JSFormatter visitCallElement(CallElement value) throws IOException {
		value.getBase().acceptExpressionVisitor(this);
		f.openSquareBracket();
		value.getIndex().acceptExpressionVisitor(this);
		f.closeSquareBracket();
		return f;
	}

	@Override
	public JSFormatter visitCallProperty(CallProperty value) throws IOException {
		value.getBase().acceptExpressionVisitor(this);
		f.dot();
		value.getName().acceptPropertyNameVisitor(
				new PropertyNameWriterVisitor(f));
		return f;
	}

	@Override
	public JSFormatter visitPostfix(Postfix value) throws IOException {
		value.getBase().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		return f;
	}

	@Override
	public JSFormatter visitUnary(Unary value) throws IOException {
		f.operator(value.getOperator());
		value.getBase().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitMultiplicative(Multiplicative value)
			throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitAdditive(Additive value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitShift(Shift value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitRelational(Relational value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitEquality(Equality value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitBand(Band value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitXor(Xor value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitBor(Bor value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitAnd(And value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitOr(Or value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitConditional(Conditional value) throws IOException {
		value.getCondition().acceptExpressionVisitor(this);
		f.questionMark();
		value.getIfTrue().acceptExpressionVisitor(this);
		f.colon();
		value.getIfFalse().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitAssignment(Assignment value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.operator(value.getOperator());
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitComma(Comma value) throws IOException {
		value.getLeft().acceptExpressionVisitor(this);
		f.comma();
		value.getRight().acceptExpressionVisitor(this);
		return f;
	}

	private ExpressionWriterVisitor indented() {
		return new ExpressionWriterVisitor(f.indented());

	}
}
