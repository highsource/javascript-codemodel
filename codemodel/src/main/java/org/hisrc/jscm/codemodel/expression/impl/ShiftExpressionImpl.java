package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAdditiveExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSShiftExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.impl.ShiftOperator;

public abstract class ShiftExpressionImpl extends RelationalExpressionImpl
		implements JSShiftExpression {

	public ShiftExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public Shift shl(JSAdditiveExpression value) {
		return new ShiftImpl(getCodeModel(), this, value, ShiftOperator.SHL);
	}

	@Override
	public Shift shr(JSAdditiveExpression value) {
		return new ShiftImpl(getCodeModel(), this, value, ShiftOperator.SHR);
	}

	@Override
	public Shift shrz(JSAdditiveExpression value) {
		return new ShiftImpl(getCodeModel(), this, value, ShiftOperator.SHRZ);
	}

	public static class ShiftImpl extends ShiftExpressionImpl implements Shift {

		private final JSShiftExpression left;
		private final JSAdditiveExpression right;
		private final ShiftOperator operator;

		public ShiftImpl(JSCodeModel codeModel, JSShiftExpression left,
				JSAdditiveExpression right, ShiftOperator operator) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			Validate.notNull(operator);
			this.left = left;
			this.right = right;
			this.operator = operator;
		}

		@Override
		public JSShiftExpression getLeft() {
			return left;
		}

		@Override
		public JSAdditiveExpression getRight() {
			return right;
		}

		@Override
		public ShiftOperator getOperator() {
			return operator;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitShift(this);
		}
	}
}
