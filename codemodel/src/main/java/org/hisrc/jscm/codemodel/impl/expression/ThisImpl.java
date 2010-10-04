package org.hisrc.jscm.codemodel.impl.expression;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSThis;

public class ThisImpl extends PrimaryExpressionImpl implements JSThis {

	public ThisImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public <V, E extends Exception> V accept(JSExpressionVisitor<V, E> visitor)
			throws E {
		return visitor.visitThis(this);
	}

}
