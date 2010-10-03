package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSMultiplicativeExpression;
import org.hisrc.jscm.codemodel.JSUnaryExpression;

public abstract class MultiplicativeExpressionImpl extends
		AdditiveExpressionImpl implements JSMultiplicativeExpression {

	public MultiplicativeExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public Multiplicative mul(JSUnaryExpression value) {
		return new MultiplicativeImpl(getCodeModel(), this, value,
				MultiplicativeOperator.MUL);
	}

	public Multiplicative div(JSUnaryExpression value) {
		return new MultiplicativeImpl(getCodeModel(), this, value,
				MultiplicativeOperator.DIV);
	}

	public Multiplicative mod(JSUnaryExpression value) {
		return new MultiplicativeImpl(getCodeModel(), this, value,
				MultiplicativeOperator.MOD);
	}

	public static class MultiplicativeImpl extends MultiplicativeExpressionImpl
			implements Multiplicative {

		private final JSMultiplicativeExpression left;
		private final JSUnaryExpression right;
		private final MultiplicativeOperator operator;

		public MultiplicativeImpl(JSCodeModel codeModel,
				JSMultiplicativeExpression left, JSUnaryExpression right,
				MultiplicativeOperator operator) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			Validate.notNull(operator);
			this.left = left;
			this.right = right;
			this.operator = operator;
		}

		public JSMultiplicativeExpression getLeft() {
			return left;
		}

		public JSUnaryExpression getRight() {
			return right;
		}

		public MultiplicativeOperator getOperator() {
			return operator;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitMultiplicative(this);
		}
	}
}
