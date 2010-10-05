package org.hisrc.jscm.codemodel;

import java.util.List;

import org.hisrc.jscm.codemodel.statement.JSStatementGenerator;

public interface JSSourceElements extends JSStatementGenerator {

	public JSFunctionDeclaration functionDeclaration(String name);

	public List<JSSourceElement> getSourceElements();
}
