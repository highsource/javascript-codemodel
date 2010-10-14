package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSBitwiseANDExpression;
import org.hisrc.jscm.codemodel.expression.JSEqualityExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.impl.BinaryOperatorImpl;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public abstract class BitwiseANDExpressionImpl extends BitwiseXORExpressionImpl
		implements JSBitwiseANDExpression {

	public BitwiseANDExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public Band band(JSEqualityExpression value) {
		return new BandImpl(getCodeModel(), this, value);
	}

	public static class BandImpl extends BitwiseANDExpressionImpl implements
			Band {
		private final JSBitwiseANDExpression left;
		private final JSBinaryOperator operator = new BinaryOperatorImpl("&");
		private final JSEqualityExpression right;

		public BandImpl(JSCodeModel codeModel, JSBitwiseANDExpression left,
				JSEqualityExpression right) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			this.left = left;
			this.right = right;

		}

		@Override
		public JSBitwiseANDExpression getLeft() {
			return left;
		}

		@Override
		public JSBinaryOperator getOperator() {
			return operator;
		}

		@Override
		public JSEqualityExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitBand(this);
		}

	}
}
