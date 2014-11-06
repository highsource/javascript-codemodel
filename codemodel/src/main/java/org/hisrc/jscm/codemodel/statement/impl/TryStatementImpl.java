package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.expression.impl.VariableImpl;
import org.hisrc.jscm.codemodel.lang.Validate;
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

	@Override
	public JSBlock getBody() {
		return _try;
	}

	@Override
	public JSBlock getCatch() {
		return _catch;
	}

	@Override
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
	
	/** TODO INVALID, does not add the try to the block. */

	public static class TryImpl implements Try {

		private final JSCodeModel codeModel;
		private final JSBlock body;

		public TryImpl(JSCodeModel codeModel) {
			Validate.notNull(codeModel);
			this.codeModel = codeModel;
			this.body = new BlockImpl(codeModel);
		}

		public JSCodeModel getCodeModel() {
			return codeModel;
		}

		@Override
		public JSBlock getBody() {
			return body;
		}

		@Override
		public TryCatch _catch(String identifier) {
			Validate.notNull(identifier);
			return new TryCatchImpl(getCodeModel(), identifier);
		}

		@Override
		public TryFinally _finally() {
			return new TryFinallyImpl(getCodeModel());
		}
	}

	public static class TryCatchImpl extends TryStatementImpl implements
			TryCatch {

		public TryCatchImpl(JSCodeModel codeModel, String exception) {
			super(codeModel, exception, false);
		}

		@Override
		public TryCatchFinally _finally() {
			return new TryCatchFinallyImpl(getCodeModel(), getException()
					.getName());
		}
	}

	public static class TryCatchFinallyImpl extends TryStatementImpl implements
			TryCatchFinally {

		public TryCatchFinallyImpl(JSCodeModel codeModel, String exception) {
			super(codeModel, exception, true);
		}
	}

	public static class TryFinallyImpl extends TryStatementImpl implements
			TryFinally {

		public TryFinallyImpl(JSCodeModel codeModel) {
			super(codeModel, null, true);
		}
	}

}
