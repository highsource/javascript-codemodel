package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;

public interface JSVariableDeclaration {
	public JSVariable getVariable();

	public JSAssignmentExpression getExpression();

	// TODO Incompatibel API change
	// TODO API removed
	/* TODO remove these */
	/*public JSVariableDeclaration comma(String identifier);

	public JSVariableDeclaration comma(String identifier,
			JSAssignmentExpression expression); */
			
}