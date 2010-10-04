package org.hisrc.jscm.codemodel;



public interface JSStatements {

	public JSBlock add(JSStatement statement);

	public JSBlock block();

	public JSVariableStatement variable(String identifier);

	public JSVariableStatement variable(String identifier,
			JSAssignmentExpression expression);

	public JSEmptyStatement empty();

	public JSExpressionStatement expression(JSExpression expression);

	// TODO
	public JSIfThenElseStatement _ifThenElse(JSExpression expression);

	// TODO IterationStatement

	// public JSDoWhile doWhile(JSExpression expression);
	//
	// public JSWhile _while(JSExpression expression);
	//
	// public JSWhile _for(JSExpression);

	public JSContinueStatement _continue();

	// public JSContinueStatement _continue(JSLabel label);

	public JSBreakStatement _break();

	// public JSBreakStatement _break(JSLabel label);

	public JSReturnStatement _return();

	public JSReturnStatement _return(JSExpression expression);

	public JSWithStatement with(JSExpression expression);

	// TODO Switch statement
	// public JSSwitchStatement _switch(JExpression expression);

	// TODO Labelled statement
	// public JSLabelledStatement

	public JSThrowStatement _throw(JSExpression expression);

	public JSTryStatement _try();

	public JSDebuggerStatement debugger();

}
