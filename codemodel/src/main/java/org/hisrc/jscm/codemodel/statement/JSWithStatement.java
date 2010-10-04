package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSExpression;

public interface JSWithStatement extends JSStatement, JSStatementGenerator {

	public JSExpression getWith();

	public JSStatement getStatement();
}
