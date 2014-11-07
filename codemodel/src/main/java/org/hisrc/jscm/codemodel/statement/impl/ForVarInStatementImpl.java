package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.expression.impl.VariableImpl;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSForVarInStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;

public class ForVarInStatementImpl extends IterationStatementImpl implements
		JSForVarInStatement {

	private final JSVariableDeclaration variableDeclaration;
	private final JSExpression _in;

	public ForVarInStatementImpl(JSCodeModel codeModel, String name,
			JSExpression _in) {
		this(codeModel, new VariableDeclarationImpl(codeModel, name), _in,
				new EmptyStatementImpl(codeModel));
	}

	public ForVarInStatementImpl(JSCodeModel codeModel,
			JSVariableDeclaration variableDeclaration, JSExpression expression,
			JSStatement statement) {
		super(codeModel, statement);
		Validate.notNull(variableDeclaration);
		Validate.notNull(expression);
		this.variableDeclaration = variableDeclaration;
		this._in = expression;
	}

	@Override
	public JSVariableDeclaration getVariableDeclaration() {
		return this.variableDeclaration;
	}

	@Override
	public JSVariable getVariable() {
		return getVariableDeclaration().getVariable();
	}

	@Override
	public JSExpression getIn() {
		return _in;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitForVarIn(this);
	}

}
