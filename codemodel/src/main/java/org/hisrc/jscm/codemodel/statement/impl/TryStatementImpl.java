package org.hisrc.jscm.codemodel.statement.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.expression.impl.VariableImpl;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;

public class TryStatementImpl extends StatementImpl implements JSTryStatement {

	private final JSBlock _try;
	private final JSVariable exception;
	private final JSBlock _catch;
	private final JSBlock _finally;

	public TryStatementImpl(JSCodeModel codeModel, String exception,
			boolean _finally) {
		super(codeModel);
		if (!_finally) {
			Validate.notNull(exception);
		}
		this._try = new BlockImpl(codeModel);
		if (exception == null) {
			this.exception = null;
			this._catch = null;
		} else {
			this.exception = new VariableImpl(codeModel, exception);
			this._catch = new BlockImpl(codeModel);
		}
		if (_finally) {
			this._finally = new BlockImpl(codeModel);
		} else {
			this._finally = null;
		}
	}

	public JSBlock getBody() {
		return _try;
	}

	public JSBlock getCatch() {
		return _catch;
	}

	public JSBlock getFinally() {
		return _finally;
	}

	@Override
	public JSVariable getException() {
		return exception;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitTry(this);
	}

}
