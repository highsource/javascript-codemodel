package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.JSPostfixExpression;

public abstract class PostfixExpressionImpl extends UnaryExpressionImpl
		implements JSPostfixExpression {

	public PostfixExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public static class PostfixImpl extends PostfixExpressionImpl implements
			Postfix {

		private final JSLeftHandSideExpression base;
		private final PostfixOperator operator;

		public PostfixImpl(JSCodeModel codeModel,
				JSLeftHandSideExpression base, PostfixOperator operator) {
			super(codeModel);
			Validate.notNull(base);
			Validate.notNull(operator);
			this.base = base;
			this.operator = operator;

		}

		public JSLeftHandSideExpression getBase() {
			return base;
		}

		public PostfixOperator getOperator() {
			return operator;
		}

		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitPostfix(this);
		}

	}

}
