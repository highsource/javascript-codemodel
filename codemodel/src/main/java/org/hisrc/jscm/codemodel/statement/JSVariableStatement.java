package org.hisrc.jscm.codemodel.statement;

import java.util.List;

// TODO API added
public interface JSVariableStatement extends JSStatement,
		JSVariableDeclaration, JSVariableDeclarationList {

	public List<JSVariableDeclaration> getVariableDeclarations();

}
