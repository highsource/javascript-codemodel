package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSDoWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class DoWhileStatementImpl extends IterationStatementImpl implements
		JSDoWhileStatement {

	private final JSExpression expression;

	public DoWhileStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		this(codeModel, new EmptyStatementImpl(codeModel), expression);
	}

	public DoWhileStatementImpl(JSCodeModel codeModel, JSStatement statement,
			JSExpression expression) {
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
		return visitor.visitDoWhile(this);
	}

}
