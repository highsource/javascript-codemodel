package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSIfStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementGenerator;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class IfStatementImpl extends StatementImpl implements JSIfStatement {

	private final JSExpression _if;

	private JSStatement _then;
	private JSStatement _else;

	private final JSStatementGenerator thenGenerator;
	private final JSStatementGenerator elseGenerator;

	public IfStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		this(codeModel, expression, new EmptyStatementImpl(codeModel));
	}

	public IfStatementImpl(JSCodeModel codeModel, JSExpression expression,
			JSStatement _then) {
		this(codeModel, expression, _then, null);
	}

	public IfStatementImpl(JSCodeModel codeModel, JSExpression expression,
			JSStatement _then, JSStatement _else) {
		super(codeModel);
		Validate.notNull(expression);
		Validate.notNull(_then);
		this._if = expression;
		this._then = _then;
		this._else = _else;
		this.thenGenerator = new StatementGeneratorImpl(codeModel) {

			protected <S extends JSStatement> S add(S statement) {
				Validate.notNull(statement);
				IfStatementImpl.this._then = statement;
				return statement;
			}
		};
		this.elseGenerator = new StatementGeneratorImpl(codeModel) {

			protected <S extends JSStatement> S add(S statement) {
				Validate.notNull(statement);
				IfStatementImpl.this._else = statement;
				return statement;
			}
		};
	}

	public JSExpression getIf() {
		return _if;
	}

	public JSStatement getThen() {
		return _then;
	}

	@Override
	public JSStatement getElse() {
		return _else;
	}

	@Override
	public JSStatementGenerator _then() {
		return thenGenerator;
	}

	@Override
	public JSStatementGenerator _else() {
		return elseGenerator;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitIf(this);
	}

}
