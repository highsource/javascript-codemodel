package org.hisrc.jscm.codemodel.statement;

import java.util.List;

import org.hisrc.jscm.codemodel.expression.JSExpression;

public interface JSForVarStatement extends JSIterationStatement,
		JSVariableDeclaration {

	public List<JSVariableDeclaration> getVariableDeclarations();

	public JSExpression getTest();

	public JSExpression getUpdate();

	public JSForVarStatement test(JSExpression expression);

	public JSForVarStatement update(JSExpression expression);
}
