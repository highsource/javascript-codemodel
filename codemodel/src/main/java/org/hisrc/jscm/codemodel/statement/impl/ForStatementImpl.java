package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSForStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class ForStatementImpl extends IterationStatementImpl implements
		JSForStatement {

	private JSExpression expression;

	private JSExpression test;

	private JSExpression update;

	public ForStatementImpl(JSCodeModel codeModel) {
		this(codeModel, null, null, null, new EmptyStatementImpl(codeModel));
	}

	public ForStatementImpl(JSCodeModel codeModel, JSExpression expression) {
		this(codeModel, expression, null, null, new EmptyStatementImpl(codeModel));
	}

	public ForStatementImpl(JSCodeModel codeModel, JSExpression expression,
			JSExpression test, JSExpression update, JSStatement statement) {
		super(codeModel, statement);
		this.expression = expression;
		this.test = test;
		this.update = update;
	}
	
	public JSForStatement expr(JSExpression expression) {
		// Can be null
		this.expression = expression; 
		return this;
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

	public JSExpression getExpression() {
		return expression;
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
