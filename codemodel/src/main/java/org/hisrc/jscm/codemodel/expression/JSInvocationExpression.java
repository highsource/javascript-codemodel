package org.hisrc.jscm.codemodel.expression;

import java.util.List;

public interface JSInvocationExpression {
	public JSExpression getBase();

	public List<JSAssignmentExpression> getArgs();

}
