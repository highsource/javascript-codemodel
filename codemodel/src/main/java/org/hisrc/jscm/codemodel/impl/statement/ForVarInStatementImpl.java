package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.expression.VariableImpl;
import org.hisrc.jscm.codemodel.statement.JSForVarInStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class ForVarInStatementImpl extends IterationStatementImpl implements
		JSForVarInStatement {

	private final JSVariable variable;
	private final JSExpression expression;

	public ForVarInStatementImpl(JSCodeModel codeModel, String name,
			JSExpression expression) {
		super(codeModel);
		Validate.notNull(name);
		Validate.notNull(expression);
		this.variable = new VariableImpl(codeModel, name);
		this.expression = expression;
	}

	public JSVariable getVariable() {
		return variable;
	}

	public JSExpression getExpression() {
		return expression;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitForVarIn(this);
	}

}
