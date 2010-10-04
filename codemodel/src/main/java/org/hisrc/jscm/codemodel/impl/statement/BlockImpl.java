package org.hisrc.jscm.codemodel.impl.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class BlockImpl extends StatementGeneratorImpl implements JSBlock {

	private final List<JSStatement> statements = new ArrayList<JSStatement>();

	private final List<JSStatement> unmodifiableStatements = Collections
			.unmodifiableList(statements);

	public BlockImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public List<JSStatement> getStatements() {
		return statements;
	}

	protected <S extends JSStatement> S add(S statement) {
		Validate.notNull(statement);
		this.statements.add(statement);
		return statement;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitBlock(this);
	}

}
