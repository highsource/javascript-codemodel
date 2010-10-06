package org.hisrc.jscm.codemodel.expression;

import java.util.List;

import org.hisrc.jscm.codemodel.JSFunctionBody;

public interface JSFunctionExpression extends JSMemberExpression {

	public interface Function extends JSFunctionExpression {

		public JSVariable parameter(String name);

		public String getName();

		public JSFunctionBody getBody();

		public List<JSVariable> getParameters();
	}

}
