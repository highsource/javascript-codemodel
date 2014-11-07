package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.expression.impl.VariableImpl;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;

public class VariableDeclarationImpl implements JSVariableDeclaration {

	private final JSVariable variable;
	private final JSAssignmentExpression expression;

	public VariableDeclarationImpl(JSCodeModel codeModel, String name) {
		Validate.notNull(codeModel);
		Validate.notNull(name);
		this.variable = new VariableImpl(codeModel, name);
		this.expression = null;
	}

	public VariableDeclarationImpl(JSCodeModel codeModel, String name,
			JSAssignmentExpression expression) {
		Validate.notNull(name);
		Validate.notNull(expression);
		this.variable = new VariableImpl(codeModel, name);
		this.expression = expression;
	}

	/* TODO
	public JSVariableDeclaration getParentVariableDeclaration() {
		return parentVariableDeclaration;
	}
	*/

	/* TODO
	 * @Override public JSVariableDeclaration comma(String name) { return
	 * getParentVariableDeclaration().comma(name); }
	 * 
	 * @Override public JSVariableDeclaration comma(String name,
	 * JSAssignmentExpression expression) { return
	 * getParentVariableDeclaration().comma(name, expression); }
	 */

	@Override
	public JSVariable getVariable() {
		return variable;
	}

	@Override
	public JSAssignmentExpression getExpression() {
		return expression;
	}
}