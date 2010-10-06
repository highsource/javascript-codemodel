package org.hisrc.jscm.codemodel.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSSourceElementVisitor;
import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.expression.VariableImpl;

public class FunctionDeclarationImpl implements JSFunctionDeclaration {

	private final JSCodeModel codeModel;
	private final String name;
	private final JSPrimaryExpression functionExpression;

	private final List<JSVariable> parameters = new ArrayList<JSVariable>();
	private final List<JSVariable> unmodifiableParameters = Collections
			.unmodifiableList(parameters);

	private final JSFunctionBody body;

	public FunctionDeclarationImpl(JSCodeModel codeModel, String name) {
		Validate.notNull(codeModel);
		Validate.notNull(name);
		this.codeModel = codeModel;
		this.name = name;
		this.functionExpression = new VariableImpl(codeModel, name);
		this.body = new FunctionBodyImpl(codeModel);
	}

	public JSCodeModel getCodeModel() {
		return codeModel;
	}

	public String getName() {
		return name;
	}
	
	public JSPrimaryExpression getFunctionExpression() {
		return functionExpression;
	}

	public JSFunctionBody getBody() {
		return body;
	}

	public List<JSVariable> getParameters() {
		return unmodifiableParameters;
	}

	public JSVariable parameter(String name) {
		Validate.notNull(name);
		JSVariable parameter = new VariableImpl(getCodeModel(), name);
		this.parameters.add(parameter);
		return parameter;
	}

	public <V, E extends Exception> V acceptSourceElementVisitor(
			JSSourceElementVisitor<V, E> visitor) throws E {
		return visitor.visitFunctionDeclaration(this);
	}
	
}
