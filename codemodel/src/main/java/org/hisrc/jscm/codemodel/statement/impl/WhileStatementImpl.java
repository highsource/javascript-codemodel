package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSWhileStatement;

public class WhileStatementImpl extends IterationStatementImpl implements
		JSWhileStatement {

	private final JSExpression expression;

	public WhileStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		this(codeModel, expression, new EmptyStatementImpl(codeModel));
	}

	public WhileStatementImpl(JSCodeModel codeModel, JSExpression expression,
			JSStatement statement) {
		super(codeModel, statement);
		Validate.notNull(expression);
		this.expression = expression;
	}

	public JSExpression getExpression() {
		return expression;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitWhile(this);
	}

}
