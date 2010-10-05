package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSWhileStatement;

public class WhileStatementImpl extends IterationStatementImpl implements
		JSWhileStatement {

	private final JSExpression expression;

	public WhileStatementImpl(JSCodeModel codeModel, JSExpression expression) {
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
		return visitor.visitWhile(this);
	}

}
