package org.hisrc.jscm.codemodel.statement;

import java.util.List;

public interface JSVariableStatement extends JSStatement, JSVariableDeclaration {

	public List<JSVariableDeclaration> getVariableDeclarations();

}
