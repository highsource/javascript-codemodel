package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSBreakStatement;
import org.hisrc.jscm.codemodel.statement.JSContinueStatement;
import org.hisrc.jscm.codemodel.statement.JSDebuggerStatement;
import org.hisrc.jscm.codemodel.statement.JSEmptyStatement;
import org.hisrc.jscm.codemodel.statement.JSExpressionStatement;
import org.hisrc.jscm.codemodel.statement.JSIfStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement;
import org.hisrc.jscm.codemodel.statement.JSReturnStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementGenerator;
import org.hisrc.jscm.codemodel.statement.JSThrowStatement;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.statement.JSWithStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;

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

	public JSContinueStatement _continue() {
		return add(new ContinueStatementImpl(getCodeModel()));
	}

	public JSContinueStatement _continue(JSLabel label) {
		return add(new ContinueStatementImpl(getCodeModel(), label));
	}

	public JSBreakStatement _break() {
		return add(new BreakStatementImpl(getCodeModel()));
	}

	public JSBreakStatement _break(JSLabel label) {
		return add(new BreakStatementImpl(getCodeModel(), label));
	}

	public JSReturnStatement _return() {
		return add(new ReturnStatementImpl(getCodeModel()));
	}

	@Override
	public JSReturnStatement _return(JSExpression expression) {
		return add(new ReturnStatementImpl(getCodeModel(), expression));
	}

	public JSWithStatement with(JSExpression expression) {
		return add(new WithStatementImpl(getCodeModel(), expression));
	}

	@Override
	public JSLabelledStatement label(String name) {
		return add(new LabelledStatementImpl(getCodeModel(), name));
	}
	
	@Override
	public JSThrowStatement _throw(JSExpression expression) {
		return add(new ThrowStatementImpl(getCodeModel(), expression));
	}

	public JSTryStatement _tryCatch(String expression) {
		return add(new TryStatementImpl(getCodeModel(), expression, false));
	}

	public JSTryStatement _tryFinally() {
		return add(new TryStatementImpl(getCodeModel(), null, true));
	}

	public JSTryStatement _tryCatchFinally(String expression) {
		return add(new TryStatementImpl(getCodeModel(), expression, true));
	}

	@Override
	public JSDebuggerStatement debugger() {
		return add(new DebuggerStatementImpl(getCodeModel()));
	}

}
