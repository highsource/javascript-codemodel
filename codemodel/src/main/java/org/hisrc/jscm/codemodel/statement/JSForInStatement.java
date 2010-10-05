package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;

public interface JSForInStatement extends JSIterationStatement {

	public JSLeftHandSideExpression getExpression();

	public JSExpression getIn();

}
