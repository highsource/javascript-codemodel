package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSExpression;


public interface JSThrowStatement extends JSStatement {

	public JSExpression getExpression();
}
