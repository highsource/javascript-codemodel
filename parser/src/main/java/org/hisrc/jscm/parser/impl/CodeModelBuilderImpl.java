package org.hisrc.jscm.parser.impl;

import java.math.BigInteger;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSArrayElement;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSElision;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression;
import org.hisrc.jscm.codemodel.expression.JSIdentifierReference;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSPropertyAssignment;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.expression.impl.ArrayLiteralImpl;
import org.hisrc.jscm.codemodel.expression.impl.ElisionImpl;
import org.hisrc.jscm.codemodel.expression.impl.FunctionExpressionImpl;
import org.hisrc.jscm.codemodel.expression.impl.ObjectLiteralImpl;
import org.hisrc.jscm.codemodel.expression.impl.ObjectLiteralImpl.PropertyAssignmentImpl;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.impl.FunctionDeclarationImpl;
import org.hisrc.jscm.codemodel.impl.IdentifierNameImpl;
import org.hisrc.jscm.codemodel.impl.ProgramImpl;
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
import org.hisrc.jscm.codemodel.statement.JSLabelReference;
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
import org.hisrc.jscm.codemodel.statement.impl.BlockImpl;
import org.hisrc.jscm.codemodel.statement.impl.BreakStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.ContinueStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.DebuggerStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.DoWhileStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.EmptyStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.ExpressionStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.ForInStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.ForStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.ForVarInStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.ForVarStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.IfStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.LabelReferenceImpl;
import org.hisrc.jscm.codemodel.statement.impl.LabelledStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.ReturnStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.SwitchStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.SwitchStatementImpl.CaseClauseImpl;
import org.hisrc.jscm.codemodel.statement.impl.SwitchStatementImpl.DefaultClauseImpl;
import org.hisrc.jscm.codemodel.statement.impl.ThrowStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.TryStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.VariableDeclarationImpl;
import org.hisrc.jscm.codemodel.statement.impl.VariableStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.WhileStatementImpl;
import org.hisrc.jscm.codemodel.statement.impl.WithStatementImpl;
import org.hisrc.jscm.parser.JSCodeModelBuilder;
import org.hisrc.jscm.parser.ParseException;
import org.hisrc.jscm.parser.Token;

public class CodeModelBuilderImpl implements JSCodeModelBuilder {

	private final JSCodeModel codeModel;

	public CodeModelBuilderImpl(JSCodeModel codeModel) {
		this.codeModel = codeModel;
	}

	public CodeModelBuilderImpl() {
		this(new CodeModelImpl());
	}

	private JSCodeModel getCodeModel() {
		return codeModel;
	}

	@Override
	public JSThis _this() {
		return getCodeModel()._this();
	}
	
	@Override
	public JSIdentifierReference identifierReference(String name) {
		return getCodeModel().identifierReference(name);
	}

	@Override
	public JSArrayLiteral arrayLiteral(JSArrayElement[] elementList) {
		return new ArrayLiteralImpl(getCodeModel(), elementList);
	}

	@Override
	public JSElision elision() {
		return new ElisionImpl(getCodeModel());
	}

	@Override
	public JSObjectLiteral objectLiteral(
			JSPropertyAssignment[] propertyAssignments) {
		return new ObjectLiteralImpl(getCodeModel(), propertyAssignments);
	}

	@Override
	public JSPropertyAssignment propertyAssignment(JSPropertyName key,
			JSAssignmentExpression value) {
		return new PropertyAssignmentImpl(key, value);
	}

	@Override
	public JSNullLiteral nullLiteral(Token token) throws ParseException {
		return getCodeModel()._null();
	}

	@Override
	public JSBooleanLiteral booleanLiteral(Token token) throws ParseException {
		return getCodeModel()._boolean(Boolean.valueOf(token.image));
	}

	@Override
	public JSDecimalLiteral decimalLiteral(Token token) throws ParseException {
		return null;
	}

	@Override
	public JSDecimalIntegerLiteral decimalIntegerLiteral(Token token)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSStringLiteral stringLiteral(Token token) throws ParseException {
		// TODO Incorrect
		return codeModel.string(token.image);
	}

	@Override
	public JSHexIntegerLiteral hexIntegerLiteral(Token token)
			throws ParseException {
		// TODO Incorrect
		return codeModel
				.hexInteger(new BigInteger(token.image.substring(2), 16));
	}

	@Override
	public JSIdentifierName identifierName(Token token) throws ParseException {
		// TODO Incorrect
		return new IdentifierNameImpl(token.image);
	}

	@Override
	public String identifier(Token token) throws ParseException {
		// TODO Incorrect
		return token.image;
	}

	@Override
	public JSBlock block(JSStatement... statements) {
		return new BlockImpl(getCodeModel(), statements);
	}

	public JSVariableStatement variableStatement(
			JSVariableDeclaration[] variableDeclarations) {
		return new VariableStatementImpl(getCodeModel(), variableDeclarations);
	}

	public JSVariableDeclaration variableDeclaration(String identifier) {
		return new VariableDeclarationImpl(getCodeModel(), identifier);
	}

	public JSVariableDeclaration variableDeclaration(String identifier,
			JSAssignmentExpression expression) {
		return new VariableDeclarationImpl(getCodeModel(), identifier,
				expression);
	}

	@Override
	public JSEmptyStatement emptyStatement() {
		return new EmptyStatementImpl(getCodeModel());
	}

	@Override
	public JSExpressionStatement expressionStatement(JSExpression expression) {
		return new ExpressionStatementImpl(getCodeModel(), expression);
	}

	@Override
	public JSIfStatement ifThenElseStatement(JSExpression expression,
			JSStatement _then, JSStatement _else) {
		return new IfStatementImpl(getCodeModel(), expression, _then, _else);
	}

	@Override
	public JSIfStatement ifThenStatement(JSExpression expression,
			JSStatement _then) {
		return new IfStatementImpl(getCodeModel(), expression, _then);
	}

	@Override
	public JSDoWhileStatement doWhileStatement(JSStatement statement,
			JSExpression expression) {
		return new DoWhileStatementImpl(getCodeModel(), statement, expression);
	}

	@Override
	public JSWhileStatement whileStatement(JSExpression expression,
			JSStatement statement) {
		return new WhileStatementImpl(getCodeModel(), expression, statement);
	}

	@Override
	public JSForStatement forStatement(JSExpression expression,
			JSExpression test, JSExpression update, JSStatement statement) {
		return new ForStatementImpl(getCodeModel(), expression, test, update,
				statement);
	}

	@Override
	public JSForVarStatement forVarStatement(
			JSVariableDeclaration[] variableDeclarations, JSExpression test,
			JSExpression update, JSStatement statement) {
		return new ForVarStatementImpl(getCodeModel(), variableDeclarations,
				test, update, statement);
	}

	@Override
	public JSForInStatement forInStatement(
			JSLeftHandSideExpression leftHandSideExpression,
			JSExpression expression, JSStatement statement) {
		return new ForInStatementImpl(getCodeModel(), leftHandSideExpression,
				expression, statement);
	}

	@Override
	public JSForVarInStatement forVarInStatement(
	/* TODO VariableDeclarationNoIn */
	JSVariableDeclaration variableDeclaration, JSExpression expression,
			JSStatement statement) {
		return new ForVarInStatementImpl(getCodeModel(), variableDeclaration,
				expression, statement);
	}

	@Override
	public JSLabelReference labelReference(String label) {
		return new LabelReferenceImpl(getCodeModel(), label);
	}

	@Override
	public JSContinueStatement continueStatement() {
		return new ContinueStatementImpl(getCodeModel());
	}

	@Override
	public JSContinueStatement continueStatement(JSLabelReference label) {
		return new ContinueStatementImpl(getCodeModel(), label);
	}

	@Override
	public JSBreakStatement breakStatement() {
		return new BreakStatementImpl(getCodeModel());
	}

	@Override
	public JSBreakStatement breakStatement(JSLabelReference label) {
		return new BreakStatementImpl(getCodeModel(), label);
	}

	@Override
	public JSReturnStatement returnStatement() {
		return new ReturnStatementImpl(getCodeModel());
	}

	@Override
	public JSReturnStatement returnStatement(JSExpression expression) {
		return new ReturnStatementImpl(getCodeModel(), expression);
	}

	@Override
	public JSWithStatement withStatement(JSExpression expression,
			JSStatement statement) {
		return new WithStatementImpl(getCodeModel(), expression, statement);
	}

	@Override
	public JSSwitchStatement switchStatement(JSExpression expression,
			JSCaseClause[] caseClauses) {
		return new SwitchStatementImpl(getCodeModel(), expression, caseClauses);
	}

	@Override
	public JSSwitchStatement switchStatement(JSExpression expression,
			JSCaseClause[] firstCaseClauses, JSDefaultClause defaultClause,
			JSCaseClause[] secondCaseClauses) {
		return new SwitchStatementImpl(getCodeModel(), expression,
				firstCaseClauses, defaultClause, secondCaseClauses);
	}

	@Override
	public JSCaseClause caseClause(JSExpression expression,
			JSStatement[] statementList) {
		return new CaseClauseImpl(getCodeModel(), expression, statementList);
	}

	@Override
	public JSDefaultClause defaultClause(JSStatement[] statementList) {
		return new DefaultClauseImpl(getCodeModel(), statementList);
	}

	@Override
	public JSLabelledStatement labelledStatement(String name,
			JSStatement statement) {
		// TODO Labels
		return new LabelledStatementImpl(getCodeModel(), name, statement);
	}

	@Override
	public JSThrowStatement throwStatement(JSExpression expression) {
		return new ThrowStatementImpl(getCodeModel(), expression);
	}

	@Override
	public JSTryStatement tryCatchStatement(JSBlock tryBlock, String errorName,
			JSBlock catchBlock) {
		return new TryStatementImpl(getCodeModel(), tryBlock, errorName,
				catchBlock, null);
	}

	@Override
	public JSTryStatement tryCatchFinallyStatement(JSBlock tryBlock,
			String errorName, JSBlock catchBlock, JSBlock finallyBlock) {
		return new TryStatementImpl(getCodeModel(), tryBlock, errorName,
				catchBlock, finallyBlock);
	}

	@Override
	public JSTryStatement tryFinallyStatement(JSBlock tryBlock,
			JSBlock finallyBlock) {
		return new TryStatementImpl(getCodeModel(), tryBlock, null, null,
				finallyBlock);
	}

	@Override
	public JSDebuggerStatement debuggerStatement() {
		return new DebuggerStatementImpl(getCodeModel());
	}

	@Override
	public JSFunctionDeclaration functionDeclaration(String functionName,
			String[] formalParameterList, JSSourceElement[] functionBody) {
		return new FunctionDeclarationImpl(getCodeModel(), functionName,
				formalParameterList, functionBody);
	}

	@Override
	public JSFunctionExpression functionExpression(String functionName,
			String[] formalParameterList, JSSourceElement[] functionBody) {
		return new FunctionExpressionImpl.FunctionImpl(getCodeModel(),
				functionName, formalParameterList, functionBody);
	}

	@Override
	public JSFunctionExpression functionExpression(
			String[] formalParameterList, JSSourceElement[] functionBody) {
		return new FunctionExpressionImpl.FunctionImpl(getCodeModel(),
				formalParameterList, functionBody);
	}

	@Override
	public JSProgram program(JSSourceElement... sourceElements) {
		return new ProgramImpl(getCodeModel(), sourceElements);
	}
}
