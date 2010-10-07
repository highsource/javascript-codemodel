package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;
import org.hisrc.jscm.codemodel.operator.JSPostfixOperator;

public interface JSPostfixExpression extends JSUnaryExpression {

	public interface Postfix extends JSPostfixExpression {
		public JSLeftHandSideExpression getBase();

		public JSPostfixOperator getOperator();
	}

	public static enum PostfixOperator implements JSPostfixOperator {
		POST_INCR("++"), POST_DECR("--");

		private final String operatorAsString;

		PostfixOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

		public <V, E extends Exception> V acceptOperatorVisitor(
				JSOperatorVisitor<V, E> visitor) throws E {
			return visitor.visitPostfixOperator(this);
		}
	}

}
