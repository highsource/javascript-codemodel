package org.hisrc.jscm.codemodel.operator.impl;

import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public class BinaryOperatorImpl implements JSBinaryOperator {

	private final String operatorAsString;

	public BinaryOperatorImpl(String operatorAsString) {
		Validate.notNull(operatorAsString);
		this.operatorAsString = operatorAsString;
	}

	@Override
	public String asString() {
		return operatorAsString;
	}

	@Override
	public String toString() {
		return asString();
	}

	@Override
	public <V, E extends Exception> V acceptOperatorVisitor(
			JSOperatorVisitor<V, E> visitor) throws E {
		return visitor.visitBinaryOperator(this);
	}

}
