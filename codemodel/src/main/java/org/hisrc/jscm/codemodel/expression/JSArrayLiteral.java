package org.hisrc.jscm.codemodel.expression;

import java.util.List;

// 11.1.4
public interface JSArrayLiteral extends JSPrimaryExpression {

	public JSArrayLiteral append(JSAssignmentExpression... element);

	// TODO possible API oncompatibility
	public JSArrayLiteral elision();

	// TODO API incompatibility
	public List<JSArrayElement> getElements();

}
