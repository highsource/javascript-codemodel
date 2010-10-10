package org.hisrc.jscm.codemodel.statement.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;
import org.hisrc.jscm.codemodel.statement.JSVariableStatement;

public class VariableStatementImpl extends StatementImpl implements
		JSVariableStatement {

	private final JSVariable firstVariable;
	private final JSAssignmentExpression firstExpression;

	private final List<JSVariableDeclaration> variableDeclarations = new ArrayList<JSVariableDeclaration>();
	private final List<JSVariableDeclaration> unmodifiableVariableDeclarations = Collections
			.unmodifiableList(variableDeclarations);

	public VariableStatementImpl(JSCodeModel codeModel, String name) {
		super(codeModel);
		Validate.notNull(name);
		final JSVariableDeclaration firstVariableDeclaration = new VariableDeclarationImpl(
				codeModel, this, name);
		this.variableDeclarations.add(firstVariableDeclaration);
		this.firstVariable = firstVariableDeclaration.getVariable();
		this.firstExpression = null;
	}

	public VariableStatementImpl(JSCodeModel codeModel, String name,
			JSAssignmentExpression expression) {
		super(codeModel);
		Validate.notNull(name);
		Validate.notNull(expression);
		final JSVariableDeclaration firstVariableDeclaration = new VariableDeclarationImpl(
				codeModel, this, name, expression);
		this.variableDeclarations.add(firstVariableDeclaration);
		this.firstVariable = firstVariableDeclaration.getVariable();
		this.firstExpression = firstVariableDeclaration.getExpression();
	}

	@Override
	public List<JSVariableDeclaration> getVariableDeclarations() {
		return unmodifiableVariableDeclarations;
	}

	@Override
	public JSVariable getVariable() {
		return firstVariable;
	}

	@Override
	public JSAssignmentExpression getExpression() {
		return firstExpression;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitVariable(this);
	}

	public JSVariableDeclaration comma(String name) {
		final JSVariableDeclaration variableDeclaration = new VariableDeclarationImpl(
				getCodeModel(), this, name);
		this.variableDeclarations.add(variableDeclaration);
		return variableDeclaration;
	}

	public JSVariableDeclaration comma(String name,
			JSAssignmentExpression expression) {
		final JSVariableDeclaration variableDeclaration = new VariableDeclarationImpl(
				getCodeModel(), this, name, expression);
		this.variableDeclarations.add(variableDeclaration);
		return variableDeclaration;
	}
}
