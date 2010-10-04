package org.hisrc.jscm.codemodel.impl.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSBitwiseORExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSLogicalANDExpression;

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

		public JSBitwiseORExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitAnd(this);
		}

	}
}
