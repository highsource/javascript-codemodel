package org.hisrc.jscm.codemodel.impl.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSOperator;

public class OperatorImpl implements JSOperator{
	
	private final String operatorAsString;

	public OperatorImpl(String operatorAsString) {
		Validate.notNull(operatorAsString);
		this.operatorAsString = operatorAsString;
	}

	public String asString() {
		return operatorAsString;
	}
	
	public String toString() {
		return asString();
	}
	
	

}
