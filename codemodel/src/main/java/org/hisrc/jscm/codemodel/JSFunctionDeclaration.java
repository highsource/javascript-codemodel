package org.hisrc.jscm.codemodel;

import java.util.List;

import org.hisrc.jscm.codemodel.expression.JSVariable;

public interface JSFunctionDeclaration extends JSSourceElement,
		JSSourceElements {

	public JSVariable parameter(String name);

	public String getName();

	public List<JSVariable> getParameters();

}
