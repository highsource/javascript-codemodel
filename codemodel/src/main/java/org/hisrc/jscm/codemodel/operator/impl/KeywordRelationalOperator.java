package org.hisrc.jscm.codemodel.operator.impl;

import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSKeywordBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public enum KeywordRelationalOperator implements
		JSKeywordBinaryOperator {
	INSTANCEOF("instanceof"), IN("in");

	private final String operatorAsString;

	KeywordRelationalOperator(String operatorAsString) {
		Validate.notNull(operatorAsString);
		this.operatorAsString = operatorAsString;
	}

	public String asString() {
		return operatorAsString;
	}

	public <V, E extends Exception> V acceptOperatorVisitor(
			JSOperatorVisitor<V, E> visitor) throws E {
		return visitor.visitKeywordBinaryOperator(this);
	}

}