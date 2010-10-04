package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSOperator;

// 11.14
public interface JSAssignmentExpression extends JSExpression {

	public interface Assignment extends JSAssignmentExpression {

		public JSLeftHandSideExpression getLeft();

		public JSAssignmentExpression.AssignmentOperator getOperator();

		public JSAssignmentExpression getRight();
	}

	public static enum AssignmentOperator implements JSOperator {

		ASSIGN("="), MUL_ASSIGN("*="), DIV_ASSIGN("/="), MOD_ASSIGN("%="), PLUS_ASSIGN(
				"+="), MINUS_ASSIGN("-="), SHL_ASSIGN("<<="), SHR_ASSIGN(">>="), SHRZ_ASSIGN(
				">>>="), BAND_ASSIGN("&="), BOR_ASSIGN("|="), XOR_ASSIGN("^=");

		private final String operatorAsString;

		AssignmentOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

	}
}
