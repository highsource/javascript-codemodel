package org.hisrc.jscm.codemodel.statement.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
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
	
	public SwitchStatementImpl(JSCodeModel codeModel, JSExpression expression,
			JSCaseClause[] caseClauses) {
		super(codeModel);
		Validate.notNull(expression);
		Validate.noNullElements(caseClauses);
		this.expression = expression;
		this.firstCaseClauses.addAll(Arrays.asList(caseClauses));
	}

	public SwitchStatementImpl(JSCodeModel codeModel, JSExpression expression,
			JSCaseClause[] firstCaseClauses,
			JSDefaultClause defaultClause,
			JSCaseClause[] secondCaseClauses) {
		super(codeModel);
		Validate.notNull(expression);
		Validate.noNullElements(firstCaseClauses);
		Validate.notNull(defaultClause);
		Validate.noNullElements(secondCaseClauses);
		this.expression = expression;
		this.firstCaseClauses.addAll(Arrays.asList(firstCaseClauses));
		this.defaultClause = defaultClause;
		this.secondCaseClauses.addAll(Arrays.asList(secondCaseClauses));
	}

	@Override
	public JSCaseClause _case(JSExpression expression) {
		return _case(expression, new JSStatement[0]);
	}

	@Override
	public JSCaseClause _case(JSExpression expression,
			JSStatement... statements) {
		final JSCaseClause caseClause = new CaseClauseImpl(getCodeModel(),
				expression, statements);
		if (this.defaultClause == null) {
			this.firstCaseClauses.add(caseClause);
		} else {
			this.secondCaseClauses.add(caseClause);
		}
		return caseClause;
	}

	@Override
	public JSDefaultClause _default() {
		if (this.defaultClause == null) {
			this.defaultClause = new DefaultClauseImpl(getCodeModel());
		}
		return defaultClause;
	}

	@Override
	public JSExpression getExpression() {
		return expression;
	}

	@Override
	public List<JSCaseClause> getFirstCaseClauses() {
		return unmodifiableFirstCaseClauses;
	}

	@Override
	public JSDefaultClause getDefaultClause() {
		return defaultClause;
	}

	@Override
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
			this(codeModel, new JSStatement[0]);
		}

		public DefaultClauseImpl(JSCodeModel codeModel, JSStatement[] statements) {
			super(codeModel);
			this.statements.addAll(Arrays.asList(statements));
		}

		@Override
		public List<JSStatement> getStatements() {
			return unmodifiableStatements;
		}

		@Override
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
			this(codeModel, expression, new JSStatement[0]);
		}

		public CaseClauseImpl(JSCodeModel codeModel, JSExpression expression,
				JSStatement[] statements) {
			super(codeModel);
			Validate.notNull(expression);
			Validate.noNullElements(statements);
			this.expression = expression;
			this.statements.addAll(Arrays.asList(statements));
		}

		@Override
		public JSExpression getExpression() {
			return expression;
		}

		@Override
		public List<JSStatement> getStatements() {
			return unmodifiableStatements;
		}

		@Override
		protected <S extends JSStatement> S add(S statement) {
			this.statements.add(statement);
			return statement;
		}
	}
}
