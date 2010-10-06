package org.hisrc.jscm.codemodel;

import java.util.List;

import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;

public interface JSFunctionDeclaration extends JSSourceElement {

	public JSPrimaryExpression getFunctionExpression();

	public JSVariable parameter(String name);

	public String getName();

	public JSFunctionBody getBody();

	public List<JSVariable> getParameters();

}
