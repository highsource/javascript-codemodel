package org.hisrc.jscm.codemodel.expression.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAdditiveExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSShiftExpression;
import org.hisrc.jscm.codemodel.operator.impl.ShiftOperator;

public abstract class ShiftExpressionImpl extends RelationalExpressionImpl
		implements JSShiftExpression {

	public ShiftExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public Shift shl(JSAdditiveExpression value) {
		return new ShiftImpl(getCodeModel(), this, value, ShiftOperator.SHL);
	}

	public Shift shr(JSAdditiveExpression value) {
		return new ShiftImpl(getCodeModel(), this, value, ShiftOperator.SHR);
	}

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

		public JSShiftExpression getLeft() {
			return left;
		}

		public JSAdditiveExpression getRight() {
			return right;
		}

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
