package org.hisrc.jscm.codemodel.expression.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.expression.JSPostfixExpression;
import org.hisrc.jscm.codemodel.operator.JSPostfixOperator;

public abstract class PostfixExpressionImpl extends UnaryExpressionImpl
		implements JSPostfixExpression {

	public PostfixExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public static class PostfixImpl extends PostfixExpressionImpl implements
			Postfix {

		private final JSLeftHandSideExpression base;
		private final JSPostfixOperator operator;

		public PostfixImpl(JSCodeModel codeModel,
				JSLeftHandSideExpression base, JSPostfixOperator operator) {
			super(codeModel);
			Validate.notNull(base);
			Validate.notNull(operator);
			this.base = base;
			this.operator = operator;

		}

		public JSLeftHandSideExpression getBase() {
			return base;
		}

		public JSPostfixOperator getOperator() {
			return operator;
		}

		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitPostfix(this);
		}

	}

}
