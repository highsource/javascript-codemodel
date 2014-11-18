package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
import org.hisrc.jscm.codemodel.lang.Validate;

public class GlobalVariableImpl extends PrimaryExpressionImpl implements
		JSGlobalVariable {

	private final String name;

	public GlobalVariableImpl(JSCodeModel codeModel, String name) {
		super(codeModel);
		this.name = name;
		Validate.notNull(name);
	}

	public String getName() {
		return name;
	}

	@Override
	public <V, E extends Exception> V acceptExpressionVisitor(
			JSExpressionVisitor<V, E> visitor) throws E {
		return visitor.visitIdentifierReference(this);
	}

}
