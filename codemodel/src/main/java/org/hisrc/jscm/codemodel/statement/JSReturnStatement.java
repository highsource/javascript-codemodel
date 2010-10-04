package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSExpression;

public interface JSReturnStatement extends JSStatement {

	public JSExpression getReturn();
}
