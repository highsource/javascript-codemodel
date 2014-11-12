package org.hisrc.jscm.parser;

import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSElision;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression;
import org.hisrc.jscm.codemodel.expression.JSIdentifierReference;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.expression.JSVariable;
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
import org.hisrc.jscm.codemodel.statement.JSThrowStatement;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;
import org.hisrc.jscm.codemodel.statement.JSVariableStatement;
import org.hisrc.jscm.codemodel.statement.JSWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSWithStatement;

public interface JSCodeModelBuilder {

	public JSThis _this();
	
	public JSIdentifierReference identifierReference(String name);
	
	public JSArrayLiteral arrayLiteral();
	
	public JSElision elision();

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

	public JSVariableStatement variableStatement(
			JSVariableDeclaration[] variableDeclarationList);

	public JSVariableDeclaration variableDeclaration(String variableName);

	public JSVariableDeclaration variableDeclaration(String variableName,
			JSAssignmentExpression initializer);

	/* JSVariableDeclarationNoIn */

	public JSEmptyStatement emptyStatement();

	public JSExpressionStatement expressionStatement(JSExpression expression);

	public JSIfStatement ifThenElseStatement(JSExpression condition, JSStatement thenStatement,
			JSStatement elseStatement);

	public JSIfStatement ifThenStatement(JSExpression condition, JSStatement thenStatement);

	public JSForStatement forStatement(
	/* TODO JSExpressionNoIn */
	JSExpression expression, JSExpression test, JSExpression update,
			JSStatement statement);

	public JSForVarStatement forVarStatement(
	/* TODO JSVariableDeclarationNoIn */
	JSVariableDeclaration[] variableDeclarations, JSExpression test,
			JSExpression update, JSStatement statement);

	public JSForInStatement forInStatement(
			JSLeftHandSideExpression leftHandSideExpression,
			JSExpression expression, JSStatement statement);

	public JSForVarInStatement forVarInStatement(
	/* TODO JSVariableDeclarationNoIn */
	JSVariableDeclaration variableDeclaration, JSExpression expression,
			JSStatement statement);

	public JSDoWhileStatement doWhileStatement(JSStatement statement,
			JSExpression expression);

	public JSWhileStatement whileStatement(JSExpression expression,
			JSStatement statement);

	public JSContinueStatement continueStatement();

	public JSContinueStatement continueStatement(String label);
	
	public JSBreakStatement breakStatement();

	public JSBreakStatement breakStatement(String label);

	public JSReturnStatement returnStatement();

	public JSReturnStatement returnStatement(JSExpression expression);

	public JSWithStatement withStatement(JSExpression expression, JSStatement statement);

	public JSSwitchStatement switchStatement(JSExpression expression);

	public JSLabelledStatement labelledStatement(String label, JSStatement statement);

	public JSThrowStatement throwStatement(JSExpression expression);

	public JSTryStatement tryCatchFinallyStatement(JSBlock _try, String error,
			JSBlock _catch, JSBlock _finally);

	public JSTryStatement tryFinallyStatement(JSBlock _try, JSBlock _finally);

	public JSTryStatement tryCatchStatement(JSBlock _try, String error, JSBlock _catch);

	public JSDebuggerStatement debuggerStatement();

	public JSFunctionDeclaration functionDeclaration(String functionName,
			String[] formalParameterList, JSSourceElement[] functionBody);

	public JSFunctionExpression functionExpression(String functionName,
			String[] formalParameterList, JSSourceElement[] functionBody);

	public JSFunctionExpression functionExpression(
			String[] formalParameterList, JSSourceElement[] functionBody);

	public JSProgram program(JSSourceElement... sourceElements);
}
