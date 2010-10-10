package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;

public interface JSForVarInStatement extends JSIterationStatement {

	public JSVariable getVariable();

	public JSExpression getIn();

}
