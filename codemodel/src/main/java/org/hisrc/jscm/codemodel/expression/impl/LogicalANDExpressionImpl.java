package org.hisrc.jscm.codemodel.expression.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSBitwiseORExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSLogicalANDExpression;
import org.hisrc.jscm.codemodel.literal.impl.BinaryOperatorImpl;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public abstract class LogicalANDExpressionImpl extends LogicalORExpressionImpl
		implements JSLogicalANDExpression {

	public LogicalANDExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public And and(JSBitwiseORExpression value) {
		return new AndImpl(getCodeModel(), this, value);
	}

	public static class AndImpl extends LogicalANDExpressionImpl implements And {
		private final JSLogicalANDExpression left;

		private final JSBinaryOperator operator = new BinaryOperatorImpl("&&");

		private final JSBitwiseORExpression right;

		public AndImpl(JSCodeModel codeModel, JSLogicalANDExpression left,
				JSBitwiseORExpression right) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			this.left = left;
			this.right = right;

		}

		public JSLogicalANDExpression getLeft() {
			return left;
		}

		public JSBinaryOperator getOperator() {
			return operator;
		}

		public JSBitwiseORExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitAnd(this);
		}

	}
}
