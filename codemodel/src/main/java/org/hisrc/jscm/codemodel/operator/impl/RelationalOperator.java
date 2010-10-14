package org.hisrc.jscm.codemodel.operator.impl;

import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public enum RelationalOperator implements JSBinaryOperator {
	LT("<"), GT(">"), LE("<="), GE(">=");

	private final String operatorAsString;

	RelationalOperator(String operatorAsString) {
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