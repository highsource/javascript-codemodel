package org.hisrc.jscm.parser;

import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalLiteral;
import org.hisrc.jscm.codemodel.literal.JSHexIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSBreakStatement;
import org.hisrc.jscm.codemodel.statement.JSContinueStatement;
import org.hisrc.jscm.codemodel.statement.JSDebuggerStatement;
import org.hisrc.jscm.codemodel.statement.JSDoWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSEmptyStatement;
import org.hisrc.jscm.codemodel.statement.JSExpressionStatement;
import org.hisrc.jscm.codemodel.statement.JSForInStatement;
import org.hisrc.jscm.codemodel.statement.JSForStatement;
import org.hisrc.jscm.codemodel.statement.JSForVarInStatement;
import org.hisrc.jscm.codemodel.statement.JSForVarStatement;
import org.hisrc.jscm.codemodel.statement.JSIfStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement;
import org.hisrc.jscm.codemodel.statement.JSReturnStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement.JSCaseClause;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement.JSDefaultClause;
import org.hisrc.jscm.codemodel.statement.JSThrowStatement;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;
import org.hisrc.jscm.codemodel.statement.JSVariableStatement;
import org.hisrc.jscm.codemodel.statement.JSWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSWithStatement;

public interface JSCodeModelBuilder {

	public JSThis _this();

	public JSArrayLiteral arrayLiteral();

	public JSObjectLiteral objectLiteral();

	public JSNullLiteral nullLiteral(Token token) throws ParseException;

	public JSBooleanLiteral booleanLiteral(Token token) throws ParseException;

	public JSDecimalLiteral decimalLiteral(Token token) throws ParseException;

	public JSDecimalIntegerLiteral decimalIntegerLiteral(Token token)
			throws ParseException;

	public JSStringLiteral stringLiteral(Token token) throws ParseException;

	public JSHexIntegerLiteral hexIntegerLiteral(Token token)
			throws ParseException;

	public JSIdentifierName identifierName(Token token) throws ParseException;

	public String identifier(Token token) throws ParseException;

	// Statements

	public JSBlock block(JSStatement... statements);

	public JSVariableStatement variable(
			JSVariableDeclaration[] variableDeclarations);

	public JSVariableDeclaration variableDeclaration(String name);

	public JSVariableDeclaration variableDeclaration(String name,
			JSAssignmentExpression expression);

	/* JSVariableDeclarationNoIn */

	public JSEmptyStatement empty();

	public JSExpressionStatement expression(JSExpression expression);

	public JSIfStatement ifElse(JSExpression expression, JSStatement _then,
			JSStatement _else);

	public JSIfStatement _if(JSExpression expression, JSStatement _then);

	public JSForStatement _for(
	/* TODO JSExpressionNoIn */
	JSExpression expression, JSExpression test, JSExpression update,
			JSStatement statement);

	public JSForVarStatement forVar(
	/* TODO JSVariableDeclarationNoIn */
	JSVariableDeclaration[] variableDeclarations, JSExpression test,
			JSExpression update, JSStatement statement);

	public JSForInStatement forIn(
			JSLeftHandSideExpression leftHandSideExpression,
			JSExpression expression, JSStatement statement);

	public JSForVarInStatement forVarIn(
	/* TODO JSVariableDeclarationNoIn */
	JSVariableDeclaration variableDeclaration, JSExpression expression,
			JSStatement statement);

	public JSDoWhileStatement doWhile(JSStatement statement,
			JSExpression expression);

	public JSWhileStatement _while(JSExpression expression,
			JSStatement statement);

	public JSContinueStatement _continue();

	/*
	 * TODO Labels public JSContinueStatement _continue(String identifier);
	 */

	public JSBreakStatement _break();

	/*
	 * TODO Labels public JSBreakStatement _break(String identifier);
	 */

	public JSReturnStatement _return();

	public JSReturnStatement _return(JSExpression expression);

	public JSWithStatement with(JSExpression expression, JSStatement statement);

	// TODO Switch?

	public JSSwitchStatement _switch(JSExpression expression);

	/* TODO Label? */
	public JSLabelledStatement label(String name, JSStatement statement);

	public JSThrowStatement _throw(JSExpression expression);

	public JSTryStatement tryCatchFinally(JSBlock _try, String error,
			JSBlock _catch, JSBlock _finally);

	public JSTryStatement tryFinally(JSBlock _try, JSBlock _finally);

	public JSTryStatement tryCatch(JSBlock _try, String error, JSBlock _catch);

	public JSDebuggerStatement debugger();
}
