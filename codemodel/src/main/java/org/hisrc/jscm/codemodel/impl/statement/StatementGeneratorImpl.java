package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSDebuggerStatement;
import org.hisrc.jscm.codemodel.statement.JSEmptyStatement;
import org.hisrc.jscm.codemodel.statement.JSReturnStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementGenerator;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
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
	public JSEmptyStatement empty() {
		return add(new EmptyStatementImpl(getCodeModel()));
	}

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

	public JSTryStatement _tryCatch(String expression) {
		return add(new TryStatementImpl(getCodeModel(), expression, null));
	}

	public JSTryStatement _tryFinally() {
		return add(new TryStatementImpl(getCodeModel(), null, new BlockImpl(
				getCodeModel())));
	}

	public JSTryStatement _tryCatchFinally(String expression) {
		return add(new TryStatementImpl(getCodeModel(), expression,
				new BlockImpl(getCodeModel())));
	}

	@Override
	public JSDebuggerStatement debugger() {
		return add(new DebuggerStatementImpl(getCodeModel()));
	}

}
