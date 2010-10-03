package org.hisrc.jscm.codemodel;

import java.util.List;

public interface JSSourceElements extends JSStatements, JSFunctionDeclarations {

	public List<JSSourceElement> getSourceElements();
}
