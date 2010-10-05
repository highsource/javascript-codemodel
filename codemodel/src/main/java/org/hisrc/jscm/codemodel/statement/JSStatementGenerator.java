package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;

public interface JSStatementGenerator {

	public JSBlock block();

	public JSVariableStatement variable(String name);

	public JSVariableStatement variable(String name,
			JSAssignmentExpression expression);

	public JSEmptyStatement empty();

	public JSExpressionStatement expression(JSExpression expression);

	public JSIfStatement _if(JSExpression expression);

	public JSForStatement _for();

	public JSForStatement _for(JSExpression expression);

	public JSForInStatement _forIn(JSLeftHandSideExpression expression,
			JSExpression _in);

	public JSForVarStatement _forVar(String name);

	public JSForVarStatement _forVar(String name,
			JSAssignmentExpression expression);

	public JSForVarInStatement _forVarIn(String name, JSAssignmentExpression _in);

	// TODO IterationStatement

	public JSDoWhileStatement doWhile(JSExpression expression);

	public JSWhileStatement _while(JSExpression expression);

	//
	// public JSWhile _for(JSExpression);

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

	public JSTryStatement _tryCatch(String exception);

	public JSTryStatement _tryFinally();

	public JSTryStatement _tryCatchFinally(String exception);

	public JSDebuggerStatement debugger();

}
