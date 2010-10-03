package org.hisrc.jscm.codemodel;

import org.apache.commons.lang.Validate;

public interface JSMultiplicativeExpression extends JSAdditiveExpression {

	public JSMultiplicativeExpression.Multiplicative mul(
			JSUnaryExpression expression);

	public JSMultiplicativeExpression.Multiplicative div(
			JSUnaryExpression expression);

	public JSMultiplicativeExpression.Multiplicative mod(
			JSUnaryExpression expression);

	public interface Multiplicative extends JSMultiplicativeExpression {
		public JSMultiplicativeExpression getLeft();

		public JSMultiplicativeExpression.MultiplicativeOperator getOperator();

		public JSUnaryExpression getRight();
	}

	public static enum MultiplicativeOperator implements JSOperator {
		MUL("*"), DIV("/"), MOD("%");
		private final String operatorAsString;

		MultiplicativeOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}
	}
}
