package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;

public interface JSStatementGenerator {

	public JSBlock block();

	public JSVariableStatement var(String name);

	public JSVariableStatement var(String name,
			JSAssignmentExpression expression);

	public JSEmptyStatement empty();

	public JSExpressionStatement expression(JSExpression expression);

	public JSIfStatement _if(JSExpression expression);

	public JSForStatement _for();

	public JSForStatement _for(JSExpression expression);

	public JSForInStatement forIn(JSLeftHandSideExpression expression,
			JSExpression _in);

	public JSForVarStatement forVar(String name);

	public JSForVarStatement forVar(String name,
			JSAssignmentExpression expression);

	public JSForVarInStatement forVarIn(String name, JSExpression _in);

	public JSDoWhileStatement doWhile(JSExpression expression);

	public JSWhileStatement _while(JSExpression expression);

	public JSContinueStatement _continue();

	public JSContinueStatement _continue(JSLabel label);

	public JSBreakStatement _break();

	public JSBreakStatement _break(JSLabel label);

	public JSReturnStatement _return();

	public JSReturnStatement _return(JSExpression expression);

	public JSWithStatement with(JSExpression expression);

	public JSSwitchStatement _switch(JSExpression expression);

	public JSLabelledStatement label(String name);

	public JSThrowStatement _throw(JSExpression expression);

	public JSTryStatement tryCatch(String exception);

	public JSTryStatement tryFinally();

	public JSTryStatement tryCatchFinally(String exception);

	public JSDebuggerStatement debugger();

}
