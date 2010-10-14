package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSContinueStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class ContinueStatementImpl extends StatementImpl implements
		JSContinueStatement {

	private final JSLabel label;

	public ContinueStatementImpl(JSCodeModel codeModel) {
		super(codeModel);
		this.label = null;
	}

	public ContinueStatementImpl(JSCodeModel codeModel, JSLabel label) {
		super(codeModel);
		Validate.notNull(label);
		this.label = label;
	}

	@Override
	public JSLabel getLabel() {
		return label;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitContinue(this);
	}

}
