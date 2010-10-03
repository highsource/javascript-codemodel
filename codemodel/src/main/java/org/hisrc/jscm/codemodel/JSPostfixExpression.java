package org.hisrc.jscm.codemodel;

import org.apache.commons.lang.Validate;

public interface JSPostfixExpression extends JSUnaryExpression {

	public interface Postfix extends JSPostfixExpression {
		public JSLeftHandSideExpression getBase();

		public JSPostfixExpression.PostfixOperator getOperator();
	}

	public static enum PostfixOperator implements JSOperator {
		POST_INCR("++"), POST_DECR("--");

		private final String operatorAsString;

		PostfixOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}
	}

}
