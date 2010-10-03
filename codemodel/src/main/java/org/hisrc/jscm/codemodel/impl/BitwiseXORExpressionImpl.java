package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSBitwiseANDExpression;
import org.hisrc.jscm.codemodel.JSBitwiseXORExpression;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;

public abstract class BitwiseXORExpressionImpl extends BitwiseORExpressionImpl
		implements JSBitwiseXORExpression {

	public BitwiseXORExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public Xor xor(JSBitwiseANDExpression value) {
		return new XorImpl(getCodeModel(), this, value);
	}

	public static class XorImpl extends BitwiseXORExpressionImpl implements Xor {
		private final JSBitwiseXORExpression left;
		private final JSBitwiseANDExpression right;

		public XorImpl(JSCodeModel codeModel, JSBitwiseXORExpression left,
				JSBitwiseANDExpression right) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			this.left = left;
			this.right = right;

		}

		public JSBitwiseXORExpression getLeft() {
			return left;
		}

		public JSBitwiseANDExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitXor(this);
		}

	}
}
