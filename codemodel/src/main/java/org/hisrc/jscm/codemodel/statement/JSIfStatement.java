package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSExpression;

public interface JSIfStatement extends JSStatement {

	public JSExpression getIf();

	public JSStatement getThen();

	public JSStatement getElse();

	public JSStatementGenerator _else();

	public JSStatementGenerator _then();

}
