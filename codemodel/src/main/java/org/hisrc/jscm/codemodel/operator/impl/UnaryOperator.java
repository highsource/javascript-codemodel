package org.hisrc.jscm.codemodel.operator.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;
import org.hisrc.jscm.codemodel.operator.JSUnaryOperator;

public enum UnaryOperator implements JSUnaryOperator {
	POSITIVE("+"), NEGATIVE("-"), COMPLEMENT("~"), NOT("!");

	private final String operatorAsString;

	UnaryOperator(String operatorAsString) {
		Validate.notNull(operatorAsString);
		this.operatorAsString = operatorAsString;
	}

	public String asString() {
		return operatorAsString;
	}

	public <V, E extends Exception> V acceptOperatorVisitor(
			JSOperatorVisitor<V, E> visitor) throws E {
		return visitor.visitUnaryOperator(this);
	}
}