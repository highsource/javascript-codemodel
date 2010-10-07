package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public interface JSShiftExpression extends JSRelationalExpression {

	public JSShiftExpression.Shift shl(JSAdditiveExpression expression);

	public JSShiftExpression.Shift shr(JSAdditiveExpression expression);

	public JSShiftExpression.Shift shrz(JSAdditiveExpression expression);

	public interface Shift extends JSShiftExpression, JSBinaryExpression {
		public JSShiftExpression getLeft();

		public JSShiftExpression.ShiftOperator getOperator();

		public JSAdditiveExpression getRight();
	}

	public static enum ShiftOperator implements JSBinaryOperator {
		SHL("<<"), SHR(">>"), SHRZ(">>>");
		private final String operatorAsString;

		ShiftOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

		public <V, E extends Exception> V acceptOperatorVisitor(
				JSOperatorVisitor<V, E> visitor) throws E {
			return visitor.visitBinaryOperator(this);
		}
	}

}
