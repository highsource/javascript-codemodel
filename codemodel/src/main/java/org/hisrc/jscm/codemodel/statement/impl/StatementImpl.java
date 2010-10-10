package org.hisrc.jscm.codemodel.statement.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSSourceElementVisitor;
import org.hisrc.jscm.codemodel.statement.JSStatement;

public abstract class StatementImpl implements JSStatement{

	private final JSCodeModel codeModel;

	public StatementImpl(JSCodeModel codeModel) {

		Validate.notNull(codeModel);
		this.codeModel = codeModel;
	}

	public JSCodeModel getCodeModel() {
		return codeModel;
	}
	
	public <V, E extends Exception> V acceptSourceElementVisitor(
			JSSourceElementVisitor<V, E> visitor) throws E {
		return visitor.visitStatement(this);
	}
}
