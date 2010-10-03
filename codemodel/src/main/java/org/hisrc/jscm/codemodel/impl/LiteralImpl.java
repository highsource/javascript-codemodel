package org.hisrc.jscm.codemodel.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSLiteral;

public abstract class LiteralImpl extends PrimaryExpressionImpl implements
		JSLiteral {

	public LiteralImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public <V, E extends Exception> V accept(JSExpressionVisitor<V, E> visitor)
			throws E {
		return visitor.visitLiteral(this);
	}

}
