package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;

// TODO API added
public interface JSVariableDeclarationList {

	public JSVariableDeclarationList comma(String identifier);

	public JSVariableDeclarationList comma(String identifier,
			JSAssignmentExpression expression);
}
