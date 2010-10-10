package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.impl.ShiftOperator;


public interface JSShiftExpression extends JSRelationalExpression {

	public JSShiftExpression.Shift shl(JSAdditiveExpression expression);

	public JSShiftExpression.Shift shr(JSAdditiveExpression expression);

	public JSShiftExpression.Shift shrz(JSAdditiveExpression expression);

	public interface Shift extends JSShiftExpression, JSBinaryExpression {
		public JSShiftExpression getLeft();

		public ShiftOperator getOperator();

		public JSAdditiveExpression getRight();
	}

}
