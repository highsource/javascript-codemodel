package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpression;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSPrimaryExpression;

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

		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitBrackets(this);
		}
	}

}
