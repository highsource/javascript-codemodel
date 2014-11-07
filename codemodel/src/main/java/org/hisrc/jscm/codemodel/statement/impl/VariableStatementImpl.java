package org.hisrc.jscm.codemodel.statement.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclarationList;
import org.hisrc.jscm.codemodel.statement.JSVariableStatement;

public class VariableStatementImpl extends StatementImpl implements
		JSVariableStatement {

	private final JSVariableDeclaration firstVariableDeclaration;

	private final List<JSVariableDeclaration> variableDeclarations = new ArrayList<JSVariableDeclaration>();
	private final List<JSVariableDeclaration> unmodifiableVariableDeclarations = Collections
			.unmodifiableList(variableDeclarations);

	public VariableStatementImpl(JSCodeModel codeModel, String name) {
		this(codeModel,
				new JSVariableDeclaration[] { new VariableDeclarationImpl(
						codeModel, name) });
	}

	public VariableStatementImpl(JSCodeModel codeModel, String name,
			JSAssignmentExpression expression) {
		this(codeModel,
				new JSVariableDeclaration[] { new VariableDeclarationImpl(
						codeModel, name, expression) });
	}

	public VariableStatementImpl(JSCodeModel codeModel,
			JSVariableDeclaration[] variableDeclarations) {
		super(codeModel);
		Validate.noNullElements(variableDeclarations);
		if (variableDeclarations.length < 1) {
			throw new IllegalArgumentException(
					"Variable declaration list for the for-var statement must not be empty.");
		}
		this.firstVariableDeclaration = variableDeclarations[0];
		this.variableDeclarations.addAll(Arrays.asList(variableDeclarations));
	}

	@Override
	public List<JSVariableDeclaration> getVariableDeclarations() {
		return unmodifiableVariableDeclarations;
	}

	public JSVariableDeclaration getFirstVariableDeclaration() {
		return firstVariableDeclaration;
	}

	@Override
	public JSVariable getVariable() {
		return getFirstVariableDeclaration().getVariable();
	}

	@Override
	public JSAssignmentExpression getExpression() {
		return getFirstVariableDeclaration().getExpression();
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitVariable(this);
	}

	public JSVariableDeclarationList comma(String name) {
		final JSVariableDeclaration variableDeclaration = new VariableDeclarationImpl(
				getCodeModel(), name);
		this.variableDeclarations.add(variableDeclaration);
		return this;
	}

	public JSVariableDeclarationList comma(String name,
			JSAssignmentExpression expression) {
		final JSVariableDeclaration variableDeclaration = new VariableDeclarationImpl(
				getCodeModel(), name, expression);
		this.variableDeclarations.add(variableDeclaration);
		return this;
	}
}
