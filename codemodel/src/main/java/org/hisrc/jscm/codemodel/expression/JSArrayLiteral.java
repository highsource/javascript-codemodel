package org.hisrc.jscm.codemodel.expression;

import java.util.List;

// 11.1.4
public interface JSArrayLiteral extends JSPrimaryExpression {

	public JSArrayLiteral append(JSAssignmentExpression... element);

	public List<JSAssignmentExpression> getElements();
}
