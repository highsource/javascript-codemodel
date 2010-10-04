package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSExpression;


public interface JSExpressionStatement extends JSStatement {
	
	public JSExpression getExpression();

}
