package org.hisrc.jscm.codemodel.operator.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;
import org.hisrc.jscm.codemodel.operator.JSPrefixOperator;

public enum PrefixOperator implements JSPrefixOperator {
	PRE_INCR("++"), PRE_DECR("--");

	private final String operatorAsString;

	PrefixOperator(String operatorAsString) {
		Validate.notNull(operatorAsString);
		this.operatorAsString = operatorAsString;
	}

	public String asString() {
		return operatorAsString;
	}

	public <V, E extends Exception> V acceptOperatorVisitor(
			JSOperatorVisitor<V, E> visitor) throws E {
		return visitor.visitPrefixOperator(this);
	}
}