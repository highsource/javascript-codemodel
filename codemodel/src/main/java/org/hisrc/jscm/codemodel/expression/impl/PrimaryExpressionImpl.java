package org.hisrc.jscm.codemodel.expression.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression;

public abstract class PrimaryExpressionImpl extends MemberExpressionImpl
		implements JSPrimaryExpression {

	public PrimaryExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public static class BracketsImpl extends PrimaryExpressionImpl implements
			Brackets {

		private final JSExpression base;

		public BracketsImpl(JSCodeModel codeModel, JSExpression base) {
			super(codeModel);
			Validate.notNull(base);
			this.base = base;
		}

		public JSExpression getBase() {
			return base;
		}

		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitBrackets(this);
		}
	}

}
