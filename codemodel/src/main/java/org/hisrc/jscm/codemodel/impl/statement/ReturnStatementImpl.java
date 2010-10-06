package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.statement.JSReturnStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class ReturnStatementImpl extends StatementImpl implements
		JSReturnStatement {

	private final JSExpression expression;

	public ReturnStatementImpl(JSCodeModel codeModel) {
		super(codeModel);
		this.expression = null;
	}

	public ReturnStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		super(codeModel);
		Validate.notNull(expression);
		this.expression = expression;
	}

	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitReturn(this);
	}

	public JSExpression getReturn() {
		return expression;
	}

}
