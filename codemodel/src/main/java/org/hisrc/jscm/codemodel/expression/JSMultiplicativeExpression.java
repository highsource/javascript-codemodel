package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public interface JSMultiplicativeExpression extends JSAdditiveExpression {

	public JSMultiplicativeExpression.Multiplicative mul(
			JSUnaryExpression expression);

	public JSMultiplicativeExpression.Multiplicative div(
			JSUnaryExpression expression);

	public JSMultiplicativeExpression.Multiplicative mod(
			JSUnaryExpression expression);

	public interface Multiplicative extends JSMultiplicativeExpression,
			JSBinaryExpression {
		public JSMultiplicativeExpression getLeft();

		public JSMultiplicativeExpression.MultiplicativeOperator getOperator();

		public JSUnaryExpression getRight();
	}

	public static enum MultiplicativeOperator implements JSBinaryOperator {
		MUL("*"), DIV("/"), MOD("%");
		private final String operatorAsString;

		MultiplicativeOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

		public <V, E extends Exception> V acceptOperatorVisitor(
				JSOperatorVisitor<V, E> visitor) throws E {
			return visitor.visitBinaryOperator(this);
		}

	}
}
