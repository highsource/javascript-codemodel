package org.hisrc.jscm.codemodel.expression.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSBitwiseORExpression;
import org.hisrc.jscm.codemodel.expression.JSBitwiseXORExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.literal.impl.BinaryOperatorImpl;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

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
		private final JSBinaryOperator operator = new BinaryOperatorImpl("|");

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

		public JSBinaryOperator getOperator() {
			return operator;
		}

		public JSBitwiseXORExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitBor(this);
		}

	}
}
