package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSForStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class ForStatementImpl extends IterationStatementImpl implements
		JSForStatement {

	private final JSExpression expression;

	private JSExpression test;

	private JSExpression update;

	public ForStatementImpl(JSCodeModel codeModel) {
		super(codeModel);
		this.expression = null;
	}

	public ForStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		super(codeModel);
		Validate.notNull(expression);
		this.expression = expression;
	}

	@Override
	public JSExpression getExpression() {
		return expression;
	}

	public JSForStatement test(JSExpression expression) {
		// Can be null
		this.test = expression;
		return this;
	}

	public JSForStatement update(JSExpression expression) {
		// Can be null
		this.update = expression;
		return this;
	}

	public JSExpression getTest() {
		return test;
	}

	public JSExpression getUpdate() {
		return update;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitFor(this);
	}

}
