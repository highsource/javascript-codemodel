package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSNewExpression;

public abstract class NewExpressionImpl extends LeftHandSideExpressionImpl
		implements JSNewExpression {

	public NewExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public New _new() {
		return new NewImpl(getCodeModel(), this);
	}

	public static class NewImpl extends NewExpressionImpl implements New {

		private final JSNewExpression base;

		public NewImpl(JSCodeModel codeModel, JSNewExpression base) {
			super(codeModel);
			Validate.notNull(base);
			this.base = base;

		}

		public JSNewExpression getBase() {
			return base;
		}

		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitNew(this);
		}

	}

}
