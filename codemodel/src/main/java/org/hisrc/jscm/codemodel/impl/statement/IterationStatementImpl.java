package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSSourceElementVisitor;
import org.hisrc.jscm.codemodel.statement.JSIterationStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;

public abstract class IterationStatementImpl extends StatementGeneratorImpl
		implements JSIterationStatement {

	private JSStatement statement;

	public IterationStatementImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	protected <S extends JSStatement> S add(S statement) {
		Validate.notNull(statement);
		this.statement = statement;
		return statement;
	}

	public JSStatement getStatement() {
		return statement;
	}

	public final <V, E extends Exception> V acceptSourceElementVisitor(
			JSSourceElementVisitor<V, E> visitor) throws E {
		return visitor.visitStatement(this);
	}
}
