package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;


public interface JSVariableStatement extends JSStatement {

	public JSVariableStatement comma(String identifier);

	public JSVariableStatement comma(String identifier,
			JSAssignmentExpression expression);

	// TODO
	// variable declaration

}
