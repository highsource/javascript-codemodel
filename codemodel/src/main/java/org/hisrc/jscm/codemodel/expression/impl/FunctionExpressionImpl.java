package org.hisrc.jscm.codemodel.expression.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.FunctionBodyImpl;
import org.hisrc.jscm.codemodel.lang.Validate;

public abstract class FunctionExpressionImpl extends MemberExpressionImpl
		implements JSFunctionExpression {

	public FunctionExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public static class FunctionImpl extends FunctionExpressionImpl implements
			Function {

		private final String name;

		private final List<JSVariable> parameters = new ArrayList<JSVariable>();
		private final List<JSVariable> unmodifiableParameters = Collections
				.unmodifiableList(parameters);

		private final JSFunctionBody body;

		public FunctionImpl(JSCodeModel codeModel) {
			this(codeModel, null, new String[0], new JSSourceElement[0]);
		}

		public FunctionImpl(JSCodeModel codeModel, String name) {
			this(codeModel, name, new String[0], new JSSourceElement[0]);
		}

		public FunctionImpl(JSCodeModel codeModel,
				String[] formalParameterList, JSSourceElement[] sourceElements) {
			this(codeModel, null, formalParameterList, sourceElements);
		}

		public FunctionImpl(JSCodeModel codeModel, String name,
				String[] formalParameterList, JSSourceElement[] sourceElements) {
			super(codeModel);
			Validate.noNullElements(formalParameterList);
			Validate.noNullElements(sourceElements);
			this.name = name;
			for (String parameterName : formalParameterList) {
				this.parameter(parameterName);
			}
			this.body = new FunctionBodyImpl(codeModel);
			for (JSSourceElement sourceElement : sourceElements) {
				this.body.addSourceElement(sourceElement);
			}
		}

		public String getName() {
			return name;
		}

		public List<JSVariable> getParameters() {
			return unmodifiableParameters;
		}

		public JSFunctionBody getBody() {
			return body;
		}

		public JSVariable parameter(String name) {
			Validate.notNull(name);
			JSVariable parameter = new VariableImpl(getCodeModel(), name);
			this.parameters.add(parameter);
			return parameter;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitFunction(this);
		}
	}

}
