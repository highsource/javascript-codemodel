package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSSourceElementVisitor;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSWithStatement;

public class WithStatementImpl extends StatementGeneratorImpl implements
		JSWithStatement {

	private final JSExpression expression;

	private JSStatement statement;

	public WithStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		this(codeModel, expression, new EmptyStatementImpl(codeModel));
	}
	
	public WithStatementImpl(JSCodeModel codeModel, JSExpression expression, JSStatement statement) {
		super(codeModel);
		Validate.notNull(expression);
		Validate.notNull(statement);
		this.expression = expression;
		this.statement = statement;
	}

	@Override
	public JSExpression getWith() {
		return expression;
	}

	@Override
	public JSStatement getStatement() {
		return statement;
	}

	@Override
	protected <S extends JSStatement> S add(S statement) {
		Validate.notNull(statement);
		this.statement = statement;
		return statement;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitWith(this);
	}

	@Override
	public <V, E extends Exception> V acceptSourceElementVisitor(
			JSSourceElementVisitor<V, E> visitor) throws E {
		return visitor.visitStatement(this);
	}

}
