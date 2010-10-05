package org.hisrc.jscm.codemodel.impl.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement;

public class SwitchStatementImpl extends StatementImpl implements
		JSSwitchStatement {

	private final JSExpression expression;

	private final List<JSCaseClause> firstCaseClauses = new ArrayList<JSCaseClause>();

	private final List<JSCaseClause> unmodifiableFirstCaseClauses = Collections
			.unmodifiableList(firstCaseClauses);

	private JSDefaultClause defaultClause;

	private final List<JSCaseClause> secondCaseClauses = new ArrayList<JSCaseClause>();

	private final List<JSCaseClause> unmodifiableSecondCaseClauses = Collections
			.unmodifiableList(secondCaseClauses);

	public SwitchStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		super(codeModel);
		Validate.notNull(expression);
		this.expression = expression;
	}

	@Override
	public JSCaseClause _case(JSExpression expression) {
		final JSCaseClause caseClause = new CaseClauseImpl(getCodeModel(),
				expression);
		if (this.defaultClause == null) {
			this.firstCaseClauses.add(caseClause);
		} else {
			this.firstCaseClauses.add(caseClause);
		}
		return caseClause;
	}

	@Override
	public JSDefaultClause _default() {
		if (this.defaultClause == null) {
			this.defaultClause = new DefaultClauseImpl(getCodeModel());
		}
		return null;
	}

	@Override
	public JSExpression getExpression() {
		return expression;
	}

	public List<JSCaseClause> getFirstCaseClauses() {
		return unmodifiableFirstCaseClauses;
	}

	public JSDefaultClause getDefaultClause() {
		return defaultClause;
	}

	public List<JSCaseClause> getSecondCaseClauses() {
		return unmodifiableSecondCaseClauses;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitSwitch(this);
	}

	public static class DefaultClauseImpl extends StatementGeneratorImpl
			implements JSDefaultClause {

		private final List<JSStatement> statements = new ArrayList<JSStatement>();

		private final List<JSStatement> unmodifiableStatements = Collections
				.unmodifiableList(statements);

		public DefaultClauseImpl(JSCodeModel codeModel) {
			super(codeModel);
		}

		public List<JSStatement> getStatements() {
			return unmodifiableStatements;
		}

		protected <S extends JSStatement> S add(S statement) {
			this.statements.add(statement);
			return statement;
		}

	}

	public static class CaseClauseImpl extends StatementGeneratorImpl implements
			JSCaseClause {

		private final JSExpression expression;

		private final List<JSStatement> statements = new ArrayList<JSStatement>();

		private final List<JSStatement> unmodifiableStatements = Collections
				.unmodifiableList(statements);

		public CaseClauseImpl(JSCodeModel codeModel, JSExpression expression) {
			super(codeModel);
			Validate.notNull(expression);
			this.expression = expression;
		}

		public JSExpression getExpression() {
			return expression;
		}

		public List<JSStatement> getStatements() {
			return unmodifiableStatements;
		}

		protected <S extends JSStatement> S add(S statement) {
			this.statements.add(statement);
			return statement;
		}

	}

}
