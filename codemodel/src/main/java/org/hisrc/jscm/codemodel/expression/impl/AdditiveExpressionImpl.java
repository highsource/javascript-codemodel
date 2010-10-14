package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAdditiveExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSMultiplicativeExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.impl.AdditiveOperator;

public abstract class AdditiveExpressionImpl extends ShiftExpressionImpl
		implements JSAdditiveExpression {

	public AdditiveExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public Additive plus(JSMultiplicativeExpression value) {
		return new AdditiveImpl(getCodeModel(), this, value,
				AdditiveOperator.PLUS);
	}

	@Override
	public Additive minus(JSMultiplicativeExpression value) {
		return new AdditiveImpl(getCodeModel(), this, value,
				AdditiveOperator.MINUS);
	}

	public static class AdditiveImpl extends AdditiveExpressionImpl implements
			Additive {

		private final JSAdditiveExpression left;
		private final JSMultiplicativeExpression right;
		private final AdditiveOperator operator;

		public AdditiveImpl(JSCodeModel codeModel, JSAdditiveExpression left,
				JSMultiplicativeExpression right, AdditiveOperator operator) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			Validate.notNull(operator);
			this.left = left;
			this.right = right;
			this.operator = operator;
		}

		@Override
		public JSAdditiveExpression getLeft() {
			return left;
		}

		@Override
		public JSMultiplicativeExpression getRight() {
			return right;
		}

		@Override
		public AdditiveOperator getOperator() {
			return operator;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitAdditive(this);
		}
	}
}
