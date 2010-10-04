package org.hisrc.jscm.codemodel;

import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.statement.JSStatement;


public interface JSSwitchStatement extends JSStatement {

	public JSExpression getExpression();
}
