package org.hisrc.jscm.codemodel.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.expression.VariableImpl;
import org.hisrc.jscm.codemodel.impl.statement.StatementGeneratorImpl;
import org.hisrc.jscm.codemodel.statement.JSStatement;

public class FunctionDeclarationImpl extends StatementGeneratorImpl implements
		JSFunctionDeclaration {

	private final String name;

	private final List<JSVariable> parameters = new ArrayList<JSVariable>();
	private final List<JSVariable> unmodifiableParameters = Collections
			.unmodifiableList(parameters);

	private final List<JSSourceElement> sourceElements = new ArrayList<JSSourceElement>();
	private final List<JSSourceElement> unmodifiableSourceElements = Collections
			.unmodifiableList(sourceElements);

	public FunctionDeclarationImpl(JSCodeModel codeModel, String name) {
		super(codeModel);
		Validate.notNull(name);
		this.name = name;
	}

	protected <S extends JSStatement> S add(S statement) {
		Validate.notNull(statement);
		this.sourceElements.add(statement);
		return statement;
	}

	public String getName() {
		return name;
	}

	@Override
	public List<JSVariable> getParameters() {
		return unmodifiableParameters;
	}

	@Override
	public JSVariable parameter(String name) {
		Validate.notNull(name);
		JSVariable parameter = new VariableImpl(getCodeModel(), name);
		this.parameters.add(parameter);
		return parameter;
	}

	public List<JSSourceElement> getSourceElements() {
		return unmodifiableSourceElements;
	}

	@Override
	public JSFunctionDeclaration functionDeclaration(String name) {
		Validate.notNull(name);
		final JSFunctionDeclaration functionDeclaration = new FunctionDeclarationImpl(
				getCodeModel(), name);
		sourceElements.add(functionDeclaration);
		return functionDeclaration;
	}

}
