package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSBitwiseORExpression;
import org.hisrc.jscm.codemodel.JSBitwiseXORExpression;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;

public abstract class BitwiseORExpressionImpl extends LogicalANDExpressionImpl
		implements JSBitwiseORExpression {

	public BitwiseORExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public Bor bor(JSBitwiseXORExpression value) {
		return new BorImpl(getCodeModel(), this, value);
	}

	public static class BorImpl extends BitwiseORExpressionImpl implements Bor {
		private final JSBitwiseORExpression left;
		private final JSBitwiseXORExpression right;

		public BorImpl(JSCodeModel codeModel, JSBitwiseORExpression left,
				JSBitwiseXORExpression right) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			this.left = left;
			this.right = right;

		}

		public JSBitwiseORExpression getLeft() {
			return left;
		}

		public JSBitwiseXORExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitBor(this);
		}

	}
}
