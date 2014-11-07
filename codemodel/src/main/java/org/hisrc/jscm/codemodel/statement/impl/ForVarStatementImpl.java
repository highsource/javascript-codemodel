package org.hisrc.jscm.codemodel.statement.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSForVarStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclarationList;

public class ForVarStatementImpl extends IterationStatementImpl implements
		JSForVarStatement {

	private final JSVariableDeclaration firstVariableDeclaration;

	private final List<JSVariableDeclaration> variableDeclarations = new ArrayList<JSVariableDeclaration>();
	private final List<JSVariableDeclaration> unmodifiableVariableDeclarations = Collections
			.unmodifiableList(variableDeclarations);

	private JSExpression test;

	private JSExpression update;

	public ForVarStatementImpl(JSCodeModel codeModel, String name) {
		this(codeModel,
				new JSVariableDeclaration[] { new VariableDeclarationImpl(
						codeModel, name) }, null, null, new EmptyStatementImpl(
						codeModel));
	}

	public ForVarStatementImpl(JSCodeModel codeModel, String name,
			JSAssignmentExpression expression) {
		this(codeModel,
				new JSVariableDeclaration[] { new VariableDeclarationImpl(
						codeModel, name, expression) }, null, null,
				new EmptyStatementImpl(codeModel));
	}

	public ForVarStatementImpl(JSCodeModel codeModel,
	/** TODO VariableDeclarationListNoIn */
	JSVariableDeclaration[] variableDeclarations, JSExpression test,
			JSExpression update, JSStatement statement) {
		super(codeModel, statement);
		Validate.noNullElements(variableDeclarations);
		if (variableDeclarations.length < 1) {
			throw new IllegalArgumentException(
					"Variable declaration list for the for-var statement must not be empty.");
		}
		this.firstVariableDeclaration = variableDeclarations[0];
		this.variableDeclarations.addAll(Arrays.asList(variableDeclarations));
		this.test = test;
		this.update = update;
	}

	@Override
	public List<JSVariableDeclaration> getVariableDeclarations() {
		return unmodifiableVariableDeclarations;
	}

	public JSVariableDeclaration getFirstVariableDeclaration() {
		return this.firstVariableDeclaration;
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
	public JSVariableDeclarationList comma(String identifier) {
		final JSVariableDeclaration variableDeclaration = new VariableDeclarationImpl(
				getCodeModel(), identifier);
		this.variableDeclarations.add(variableDeclaration);
		return this;
	}

	@Override
	public JSVariableDeclarationList comma(String identifier,
			JSAssignmentExpression expression) {
		final JSVariableDeclaration variableDeclaration = new VariableDeclarationImpl(
				getCodeModel(), identifier, expression);
		this.variableDeclarations.add(variableDeclaration);
		return this;
	}

	public JSForVarStatement test(JSExpression expression) {
		// Can be null
		this.test = expression;
		return this;
	}

	public JSForVarStatement update(JSExpression expression) {
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
		return visitor.visitForVar(this);
	}

}
