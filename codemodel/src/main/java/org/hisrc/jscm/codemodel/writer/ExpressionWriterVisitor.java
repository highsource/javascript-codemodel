package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.JSVariable;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
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

public class ExpressionWriterVisitor implements Appendable,
		JSExpressionVisitor<ExpressionWriterVisitor, IOException> {

	private final Appendable writer;

	public ExpressionWriterVisitor(Appendable writer) {
		Validate.notNull(writer);
		this.writer = writer;
	}

	public ExpressionWriterVisitor append(CharSequence charSequence)
			throws IOException {
		writer.append(charSequence);
		return this;
	}

	public ExpressionWriterVisitor append(char c) throws IOException {
		writer.append(c);
		return this;
	}

	public ExpressionWriterVisitor append(CharSequence csq, int start, int end)
			throws IOException {
		writer.append(csq, start, end);
		return this;
	}

	public ExpressionWriterVisitor space() throws IOException {
		writer.append(" ");
		return this;
	}

	public ExpressionWriterVisitor comma() throws IOException {
		writer.append(", ");
		return this;
	}

	public ExpressionWriterVisitor colon() throws IOException {
		writer.append(": ");
		return this;
	}

	public ExpressionWriterVisitor openSquareBracket() throws IOException {
		writer.append("[ ");
		return this;
	}

	public ExpressionWriterVisitor closeSquareBracket() throws IOException {
		writer.append(" ]");
		return this;
	}

	public ExpressionWriterVisitor openCurlyBracket() throws IOException {
		writer.append("{ ");
		return this;
	}

	public ExpressionWriterVisitor closeCurlyBracket() throws IOException {
		writer.append(" }");
		return this;
	}

	public ExpressionWriterVisitor openRoundBracket() throws IOException {
		writer.append("( ");
		return this;
	}

	public ExpressionWriterVisitor closeRoundBracket() throws IOException {
		writer.append(" )");
		return this;
	}

	public ExpressionWriterVisitor dot() throws IOException {
		writer.append(".");
		return this;
	}

	@Override
	public ExpressionWriterVisitor visitThis(JSThis value) throws IOException {
		return append("this");
	}
	
	@Override
	public ExpressionWriterVisitor visitVariable(JSVariable value)
			throws IOException {
		return append(value.getName());
	}

	@Override
	public ExpressionWriterVisitor visitLiteral(JSLiteral value)
			throws IOException {
		return value
				.acceptLiteralVisitor(new LiteralWriterVisitor<ExpressionWriterVisitor>(
						this));
	}

	@Override
	public ExpressionWriterVisitor visitArrayLiteral(JSArrayLiteral value)
			throws IOException {

		ExpressionWriterVisitor a = openSquareBracket();

		for (int index = 0; index < value.getElements().size(); index++) {
			final JSAssignmentExpression element = value.getElements().get(
					index);
			a = element.accept(a);
			if (index < value.getElements().size() - 1) {
				a = a.comma();
			}

		}
		a = a.closeSquareBracket();

		return a;
	}

	@Override
	public ExpressionWriterVisitor visitObjectLiteral(JSObjectLiteral value)
			throws IOException {

		ExpressionWriterVisitor a = openCurlyBracket();

		for (int index = 0; index < value.getPropertyAssignments().size(); index++) {
			final JSPropertyAssignment propertyAssignment = value
					.getPropertyAssignments().get(index);

			final JSPropertyName propertyName = propertyAssignment.getKey();
			final JSAssignmentExpression propertyValue = propertyAssignment
					.getValue();
			a = propertyName
					.acceptPropertyNameVisitor(new PropertyNameWriterVisitor<ExpressionWriterVisitor>(
							a));
			a = a.colon();

			a = propertyValue.accept(a);
			if (index < value.getPropertyAssignments().size() - 1) {
				a = a.comma();
			}
		}
		a = a.closeCurlyBracket();

		return a;
	}

	@Override
	public ExpressionWriterVisitor visitBrackets(Brackets value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = a.openRoundBracket();
		a = value.getBase().accept(a);
		a = a.closeRoundBracket();
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitMemberElement(MemberElement value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getBase().accept(a);
		a = a.openSquareBracket();
		a = value.getIndex().accept(a);
		a = a.closeSquareBracket();
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitMemberProperty(MemberProperty value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getBase().accept(a);
		a = a.dot();
		a = value.getName().acceptPropertyNameVisitor(
				new PropertyNameWriterVisitor<ExpressionWriterVisitor>(a));

		return a;
	}

	@Override
	public ExpressionWriterVisitor visitMemberNew(MemberNew value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = a.append("new").space();
		a = value.getBase().accept(a);
		a = a.openRoundBracket();

		for (int index = 0; index < value.getArgs().size(); index++) {
			final JSAssignmentExpression arg = value.getArgs().get(index);

			a = arg.accept(a);
			if (index < value.getArgs().size() - 1) {
				a = a.comma();
			}
		}

		a = a.closeRoundBracket();

		return a;
	}

	@Override
	public ExpressionWriterVisitor visitMemberCall(MemberCall value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getBase().accept(a);
		a = a.openRoundBracket();

		for (int index = 0; index < value.getArgs().size(); index++) {
			final JSAssignmentExpression arg = value.getArgs().get(index);

			a = arg.accept(a);
			if (index < value.getArgs().size() - 1) {
				a = a.comma();
			}
		}

		a = a.closeRoundBracket();

		return a;
	}

	@Override
	public ExpressionWriterVisitor visitNew(New value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = a.append("new").space();
		a = value.getBase().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitCallArgs(CallArgs value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getBase().accept(a);
		a = a.openRoundBracket();

		for (int index = 0; index < value.getArgs().size(); index++) {
			final JSAssignmentExpression arg = value.getArgs().get(index);

			a = arg.accept(a);
			if (index < value.getArgs().size() - 1) {
				a = a.comma();
			}
		}
		a = a.closeRoundBracket();
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitCallElement(CallElement value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getBase().accept(a);
		a = a.openSquareBracket();
		a = value.getIndex().accept(a);
		a = a.closeSquareBracket();
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitCallProperty(CallProperty value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getBase().accept(a);
		a = a.dot();
		a = value.getName().acceptPropertyNameVisitor(
				new PropertyNameWriterVisitor<ExpressionWriterVisitor>(a));

		return a;
	}

	@Override
	public ExpressionWriterVisitor visitPostfix(Postfix value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getBase().accept(a).space();
		a = a.append(value.getOperator().asString());
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitUnary(Unary value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = a.append(value.getOperator().asString()).space();
		a = value.getBase().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitMultiplicative(Multiplicative value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append(value.getOperator().asString()).space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitAdditive(Additive value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append(value.getOperator().asString()).space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitShift(Shift value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append(value.getOperator().asString()).space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitRelational(Relational value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append(value.getOperator().asString()).space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitEquality(Equality value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append(value.getOperator().asString()).space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitBand(Band value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append('|').space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitXor(Xor value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append('^').space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitBor(Bor value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append('|').space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitAnd(And value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append("&&").space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitOr(Or value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append("||").space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitConditional(Conditional value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getCondition().accept(a).space();
		a = a.append('?').space();
		a = value.getIfTrue().accept(a).space();
		a = a.append(':').space();
		a = value.getIfFalse().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitAssignment(Assignment value)
			throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.append(value.getOperator().asString()).space();
		a = value.getRight().accept(a);
		return a;
	}

	@Override
	public ExpressionWriterVisitor visitComma(Comma value) throws IOException {
		ExpressionWriterVisitor a = this;
		a = value.getLeft().accept(a).space();
		a = a.comma();
		a = value.getRight().accept(a);
		return a;
	}
}
