package org.hisrc.jscm.codemodel.operator.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSAssignmentOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public enum AssignmentOperator implements JSAssignmentOperator {

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

	@Override
	public <V, E extends Exception> V acceptOperatorVisitor(
			JSOperatorVisitor<V, E> visitor) throws E {
		return visitor.visitAssignmentOperator(this);
	}

}