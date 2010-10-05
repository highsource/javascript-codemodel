package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;

public interface JSVariableDeclaration {
	public JSVariable getVariable();

	public JSAssignmentExpression getExpression();

	public JSVariableDeclaration comma(String identifier);

	public JSVariableDeclaration comma(String identifier,
			JSAssignmentExpression expression);
}