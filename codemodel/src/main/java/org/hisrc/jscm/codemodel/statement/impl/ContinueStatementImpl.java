package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSContinueStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelReference;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class ContinueStatementImpl extends StatementImpl implements
		JSContinueStatement {

	private final JSLabelReference label;

	public ContinueStatementImpl(JSCodeModel codeModel) {
		super(codeModel);
		this.label = null;
	}

	public ContinueStatementImpl(JSCodeModel codeModel, JSLabelReference label) {
		super(codeModel);
		Validate.notNull(label);
		this.label = label;
	}

	@Override
	public JSLabelReference getLabel() {
		return label;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitContinue(this);
	}

}
