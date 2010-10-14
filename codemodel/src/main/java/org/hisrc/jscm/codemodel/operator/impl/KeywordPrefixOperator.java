package org.hisrc.jscm.codemodel.operator.impl;

import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSKeywordPrefixOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public enum KeywordPrefixOperator implements JSKeywordPrefixOperator {
	DELETE("delete"), VOID("void"), TYPEOF("typeof");

	private final String operatorAsString;

	KeywordPrefixOperator(String operatorAsString) {
		Validate.notNull(operatorAsString);
		this.operatorAsString = operatorAsString;
	}

	public String asString() {
		return operatorAsString;
	}

	public <V, E extends Exception> V acceptOperatorVisitor(
			JSOperatorVisitor<V, E> visitor) throws E {
		return visitor.visitKeywordPrefixOperator(this);
	}
}