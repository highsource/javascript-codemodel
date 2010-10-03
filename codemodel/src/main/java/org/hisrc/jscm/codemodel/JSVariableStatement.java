package org.hisrc.jscm.codemodel;


public interface JSVariableStatement extends JSStatement {

	public JSVariableStatement comma(String identifier);

	public JSVariableStatement comma(String identifier,
			JSAssignmentExpression expression);

	// TODO
	// variable declaration

}
