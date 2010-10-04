package org.hisrc.jscm.codemodel;

import java.util.List;

import org.hisrc.jscm.codemodel.statement.JSStatementGenerator;

public interface JSSourceElements extends JSStatementGenerator, JSFunctionDeclarations {

	public List<JSSourceElement> getSourceElements();
}
