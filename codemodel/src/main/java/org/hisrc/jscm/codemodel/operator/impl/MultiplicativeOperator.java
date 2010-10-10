package org.hisrc.jscm.codemodel.operator.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public enum MultiplicativeOperator implements JSBinaryOperator {
	MUL("*"), DIV("/"), MOD("%");
	private final String operatorAsString;

	MultiplicativeOperator(String operatorAsString) {
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