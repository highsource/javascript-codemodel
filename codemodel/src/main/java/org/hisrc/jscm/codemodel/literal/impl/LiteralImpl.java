package org.hisrc.jscm.codemodel.literal.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.impl.PrimaryExpressionImpl;
import org.hisrc.jscm.codemodel.literal.JSLiteral;

public abstract class LiteralImpl extends PrimaryExpressionImpl implements
		JSLiteral {

	public LiteralImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public <V, E extends Exception> V acceptExpressionVisitor(JSExpressionVisitor<V, E> visitor)
			throws E {
		return visitor.visitLiteral(this);
	}

}
