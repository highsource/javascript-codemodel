package org.hisrc.jscm.codemodel.statement.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.expression.impl.VariableImpl;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;

public class VariableDeclarationImpl implements JSVariableDeclaration {

	private final JSVariable variable;
	private final JSAssignmentExpression expression;
	private final JSVariableDeclaration parentVariableDeclaration;

	public VariableDeclarationImpl(JSCodeModel codeModel,
			JSVariableDeclaration parentVariableDeclaration, String name) {
		Validate.notNull(codeModel);
		Validate.notNull(parentVariableDeclaration);
		Validate.notNull(name);
		this.parentVariableDeclaration = parentVariableDeclaration;
		this.variable = new VariableImpl(codeModel, name);
		this.expression = null;
	}

	public VariableDeclarationImpl(JSCodeModel codeModel,
			JSVariableDeclaration parentVariableDeclaration, String name,
			JSAssignmentExpression expression) {
		Validate.notNull(parentVariableDeclaration);
		Validate.notNull(name);
		Validate.notNull(expression);
		this.parentVariableDeclaration = parentVariableDeclaration;
		this.variable = new VariableImpl(codeModel, name);
		this.expression = expression;
	}

	public JSVariableDeclaration getParentVariableDeclaration() {
		return parentVariableDeclaration;
	}

	@Override
	public JSVariableDeclaration comma(String name) {
		return getParentVariableDeclaration().comma(name);
	}

	@Override
	public JSVariableDeclaration comma(String name,
			JSAssignmentExpression expression) {
		return getParentVariableDeclaration().comma(name, expression);
	}

	@Override
	public JSVariable getVariable() {
		return variable;
	}

	@Override
	public JSAssignmentExpression getExpression() {
		return expression;
	}
}