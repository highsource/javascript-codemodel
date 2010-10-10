package org.hisrc.jscm.codemodel.expression.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSVariable;

public class VariableImpl extends PrimaryExpressionImpl implements JSVariable{

	private final String name;

	public VariableImpl(JSCodeModel codeModel, String name) {
		super(codeModel);
		this.name = name;
		Validate.notNull(name);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public <V, E extends Exception> V acceptExpressionVisitor(JSExpressionVisitor<V, E> visitor)
			throws E {
		return visitor.visitVariable(this);
	}

}
