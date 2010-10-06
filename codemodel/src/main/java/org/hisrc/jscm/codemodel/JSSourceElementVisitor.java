package org.hisrc.jscm.codemodel;

import org.hisrc.jscm.codemodel.statement.JSStatement;

public interface JSSourceElementVisitor <V, E extends Exception> {

	public V visitFunctionDeclaration(JSFunctionDeclaration value) throws E;

	public V visitStatement(JSStatement value) throws E;

}
