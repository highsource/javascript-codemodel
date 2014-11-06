package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
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
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;
import org.hisrc.jscm.codemodel.statement.JSTryStatement.Try;
import org.hisrc.jscm.codemodel.statement.JSReturnStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementGenerator;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement;
import org.hisrc.jscm.codemodel.statement.JSThrowStatement;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.statement.JSVariableStatement;
import org.hisrc.jscm.codemodel.statement.JSWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSWithStatement;

public abstract class StatementGeneratorImpl implements JSStatementGenerator {
	private final JSCodeModel codeModel;

	public StatementGeneratorImpl(JSCodeModel codeModel) {

		Validate.notNull(codeModel);
		this.codeModel = codeModel;
	}

	public JSCodeModel getCodeModel() {
		return codeModel;
	}

	protected abstract <S extends JSStatement> S add(S statement);

	@Override
	public JSBlock block() {
		return add(new BlockImpl(getCodeModel()));
	}

	@Override
	public JSVariableStatement var(String name) {
		return add(new VariableStatementImpl(getCodeModel(), name));
	}

	@Override
	public JSVariableStatement var(String name,
			JSAssignmentExpression expression) {
		return add(new VariableStatementImpl(getCodeModel(), name, expression));
	}

	@Override
	public JSEmptyStatement empty() {
		return add(new EmptyStatementImpl(getCodeModel()));
	}

	@Override
	public JSExpressionStatement expression(JSExpression expression) {
		return add(new ExpressionStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSIfStatement _if(JSExpression expression) {
		return add(new IfStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSDoWhileStatement doWhile(JSExpression expression) {
		return add(new DoWhileStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSWhileStatement _while(JSExpression expression) {
		return add(new WhileStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSForStatement _for() {
		return add(new ForStatementImpl(getCodeModel()));
	}

	@Override
	public JSForStatement _for(JSExpression expression) {
		return add(new ForStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSForInStatement forIn(JSLeftHandSideExpression expression,
			JSExpression _in) {
		return add(new ForInStatementImpl(getCodeModel(), expression, _in));
	}

	@Override
	public JSForVarStatement forVar(String name) {
		return add(new ForVarStatementImpl(getCodeModel(), name));
	}

	@Override
	public JSForVarStatement forVar(String name,
			JSAssignmentExpression expression) {
		return add(new ForVarStatementImpl(getCodeModel(), name, expression));
	}

	@Override
	public JSForVarInStatement forVarIn(String name, JSExpression _in) {
		return add(new ForVarInStatementImpl(getCodeModel(), name, _in));
	}

	@Override
	public JSContinueStatement _continue() {
		return add(new ContinueStatementImpl(getCodeModel()));
	}

	@Override
	public JSContinueStatement _continue(JSLabel label) {
		return add(new ContinueStatementImpl(getCodeModel(), label));
	}

	@Override
	public JSBreakStatement _break() {
		return add(new BreakStatementImpl(getCodeModel()));
	}

	@Override
	public JSBreakStatement _break(JSLabel label) {
		return add(new BreakStatementImpl(getCodeModel(), label));
	}

	@Override
	public JSReturnStatement _return() {
		return add(new ReturnStatementImpl(getCodeModel()));
	}

	@Override
	public JSReturnStatement _return(JSExpression expression) {
		return add(new ReturnStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSWithStatement with(JSExpression expression) {
		return add(new WithStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSLabelledStatement label(String name) {
		return add(new LabelledStatementImpl(getCodeModel(), name));
	}

	@Override
	public JSSwitchStatement _switch(JSExpression expression) {
		return add(new SwitchStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSThrowStatement _throw(JSExpression expression) {
		return add(new ThrowStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSTryStatement tryCatch(String expression) {
		return add(new TryStatementImpl(getCodeModel(), expression, false));
	}

	@Override
	public JSTryStatement tryFinally() {
		return add(new TryStatementImpl(getCodeModel(), null, true));
	}

	@Override
	public JSTryStatement tryCatchFinally(String expression) {
		return add(new TryStatementImpl(getCodeModel(), expression, true));
	}

	@Override
	public JSDebuggerStatement debugger() {
		return add(new DebuggerStatementImpl(getCodeModel()));
	}

}
