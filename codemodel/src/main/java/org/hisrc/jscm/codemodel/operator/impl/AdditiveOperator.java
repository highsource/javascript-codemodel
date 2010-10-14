package org.hisrc.jscm.codemodel.operator.impl;

import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public enum AdditiveOperator implements JSBinaryOperator {
	PLUS("+"), MINUS("-");
	private final String operatorAsString;

	AdditiveOperator(String operatorAsString) {
		Validate.notNull(operatorAsString);
		this.operatorAsString = operatorAsString;
	}

	@Override
	public String asString() {
		return operatorAsString;
	}

	@Override
	public <V, E extends Exception> V acceptOperatorVisitor(
			JSOperatorVisitor<V, E> visitor) throws E {
		return visitor.visitBinaryOperator(this);
	}
}