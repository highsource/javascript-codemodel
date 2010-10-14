package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSBitwiseANDExpression;
import org.hisrc.jscm.codemodel.expression.JSBitwiseXORExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.impl.BinaryOperatorImpl;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

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

		private final JSBinaryOperator operator = new BinaryOperatorImpl("^");

		private final JSBitwiseANDExpression right;

		public XorImpl(JSCodeModel codeModel, JSBitwiseXORExpression left,
				JSBitwiseANDExpression right) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			this.left = left;
			this.right = right;

		}

		@Override
		public JSBitwiseXORExpression getLeft() {
			return left;
		}

		@Override
		public JSBinaryOperator getOperator() {
			return operator;
		}

		@Override
		public JSBitwiseANDExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitXor(this);
		}

	}
}
