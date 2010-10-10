package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.statement.JSEmptyStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class EmptyStatementImpl extends StatementImpl implements
		JSEmptyStatement {

	public EmptyStatementImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitEmpty(this);
	}

}
