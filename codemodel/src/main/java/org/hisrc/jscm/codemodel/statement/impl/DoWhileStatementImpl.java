package org.hisrc.jscm.codemodel.statement.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.statement.JSDoWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class DoWhileStatementImpl extends IterationStatementImpl implements
		JSDoWhileStatement {

	private final JSExpression expression;

	public DoWhileStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		super(codeModel);
		this.expression = expression;
		Validate.notNull(expression);
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
