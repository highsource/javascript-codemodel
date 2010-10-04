package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSOperator;

public interface JSShiftExpression extends JSRelationalExpression {

	public JSShiftExpression.Shift shl(JSAdditiveExpression expression);

	public JSShiftExpression.Shift shr(JSAdditiveExpression expression);

	public JSShiftExpression.Shift shrz(JSAdditiveExpression expression);

	public interface Shift extends JSShiftExpression {
		public JSShiftExpression getLeft();

		public JSShiftExpression.ShiftOperator getOperator();

		public JSAdditiveExpression getRight();
	}

	public static enum ShiftOperator implements JSOperator {
		SHL("<<"), SHR(">>"), SHRZ(">>>=");
		private final String operatorAsString;

		ShiftOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}
	}

}
