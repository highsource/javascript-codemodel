package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSArrayElementVisitor;
import org.hisrc.jscm.codemodel.expression.JSElision;
import org.hisrc.jscm.codemodel.lang.Validate;

public class ElisionImpl implements JSElision {

	private final JSCodeModel codeModel;

	public ElisionImpl(JSCodeModel codeModel) {
		Validate.notNull(codeModel);
		this.codeModel = codeModel;
	}

	public JSCodeModel getCodeModel() {
		return codeModel;
	}

	@Override
	public <V, E extends Exception> V acceptArrayElementVisitor(
			JSArrayElementVisitor<V, E> visitor) throws E {
		return visitor.visitElision(this);
	}
}
