package org.hisrc.jscm.codemodel;

import java.util.List;

// 11.1.4
public interface JSArrayLiteral extends JSPrimaryExpression {

	public JSArrayLiteral add(JSAssignmentExpression... element);

	public List<JSAssignmentExpression> getElements();
}
