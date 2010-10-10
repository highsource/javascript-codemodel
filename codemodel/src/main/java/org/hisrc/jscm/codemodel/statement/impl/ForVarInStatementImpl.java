package org.hisrc.jscm.codemodel.statement.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.expression.impl.VariableImpl;
import org.hisrc.jscm.codemodel.statement.JSForVarInStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class ForVarInStatementImpl extends IterationStatementImpl implements
		JSForVarInStatement {

	private final JSVariable variable;
	private final JSExpression _in;

	public ForVarInStatementImpl(JSCodeModel codeModel, String name,
			JSExpression _in) {
		super(codeModel);
		Validate.notNull(name);
		this.variable = new VariableImpl(codeModel, name);
		this._in = _in;
	}

	@Override
	public JSVariable getVariable() {
		return variable;
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
