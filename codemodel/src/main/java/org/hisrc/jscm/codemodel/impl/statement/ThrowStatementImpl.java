package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSThrowStatement;

public class ThrowStatementImpl extends StatementImpl implements
		JSThrowStatement {

	private final JSExpression expression;

	public ThrowStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		super(codeModel);
		Validate.notNull(expression);
		this.expression = expression;
	}

	@Override
	public JSExpression getExpression() {
		return expression;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitThrow(this);
	}

}
