package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.expression.VariableImpl;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;

public class VariableDeclarationImpl implements
		JSVariableDeclaration {

	private final JSCodeModel codeModel;
	private final JSVariable variable;
	private final JSAssignmentExpression expression;
	private final JSVariableDeclaration parentVariableDeclaration;

	public VariableDeclarationImpl(JSCodeModel codeModel,
			JSVariableDeclaration parentVariableDeclaration, String name) {
		Validate.notNull(codeModel);
		Validate.notNull(parentVariableDeclaration);
		Validate.notNull(name);
		this.codeModel = codeModel;
		this.parentVariableDeclaration = parentVariableDeclaration;
		this.variable = new VariableImpl(codeModel, name);
		this.expression = null;
	}

	public VariableDeclarationImpl(JSCodeModel codeModel,
			JSVariableDeclaration parentVariableDeclaration, String name,
			JSAssignmentExpression expression) {
		Validate.notNull(codeModel);
		Validate.notNull(parentVariableDeclaration);
		Validate.notNull(name);
		Validate.notNull(expression);
		this.codeModel = codeModel;
		this.parentVariableDeclaration = parentVariableDeclaration;
		this.variable = new VariableImpl(codeModel, name);
		this.expression = expression;
	}

	public JSCodeModel getCodeModel() {
		return codeModel;
	}

	public JSVariableDeclaration getParentVariableDeclaration() {
		return parentVariableDeclaration;
	}

	public JSVariableDeclaration comma(String name) {
		return getParentVariableDeclaration().comma(name);
	}

	@Override
	public JSVariableDeclaration comma(String name,
			JSAssignmentExpression expression) {
		return getParentVariableDeclaration().comma(name, expression);
	}

	public JSVariable getVariable() {
		return variable;
	}

	public JSAssignmentExpression getExpression() {
		return expression;
	}
}