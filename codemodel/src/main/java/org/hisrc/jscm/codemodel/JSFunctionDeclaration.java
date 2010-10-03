package org.hisrc.jscm.codemodel;

import java.util.List;

public interface JSFunctionDeclaration extends JSSourceElement {

	public String getFunctionName();

	public List<JSVariable> getParameters();

	public JSVariable parameter(String name);

	public JSFunctionBody body();

}
