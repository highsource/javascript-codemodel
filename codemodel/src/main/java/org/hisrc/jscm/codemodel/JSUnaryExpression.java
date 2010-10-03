package org.hisrc.jscm.codemodel;

import org.apache.commons.lang.Validate;

public interface JSUnaryExpression extends JSMultiplicativeExpression {
	public JSUnaryExpression.Unary delete();

	public JSUnaryExpression.Unary _void();

	public JSUnaryExpression.Unary typeof();

	public JSUnaryExpression.Unary preIncr();

	public JSUnaryExpression.Unary preDecr();

	public JSUnaryExpression.Unary positive();

	public JSUnaryExpression.Unary negative();

	public JSUnaryExpression.Unary complement();

	public JSUnaryExpression.Unary not();

	public interface Unary extends JSUnaryExpression {
		public JSUnaryExpression getBase();

		public JSUnaryExpression.UnaryOperator getOperator();
	}

	public static enum UnaryOperator implements JSOperator {
		DELETE("delete"), VOID("void"), TYPEOF("typeof"), PRE_INCR("++"), PRE_DECR(
				"--"), POSITIVE("+"), NEGATIVE("-"), COMPLEMENT("~"), NOT("!");

		private final String operatorAsString;

		UnaryOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}
	}

}
