package org.hisrc.jscm.codemodel.statement;

import java.util.List;

import org.hisrc.jscm.codemodel.expression.JSExpression;

public interface JSSwitchStatement extends JSStatement {

	public JSCaseClause _case(JSExpression expression);

	// TODO API added
	public JSCaseClause _case(JSExpression expression,
			JSStatement... statements);

	public JSDefaultClause _default();

	public JSExpression getExpression();

	public List<JSCaseClause> getFirstCaseClauses();

	public JSDefaultClause getDefaultClause();

	public List<JSCaseClause> getSecondCaseClauses();

	public interface JSDefaultClause extends JSStatementGenerator {
		public List<JSStatement> getStatements();
	}

	public interface JSCaseClause extends JSStatementGenerator {
		public JSExpression getExpression();

		public List<JSStatement> getStatements();

	}
}
