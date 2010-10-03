package org.hisrc.jscm.codemodel.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSLiteralVisitor;
import org.hisrc.jscm.codemodel.JSNullLiteral;

public class NullLiteralImpl extends LiteralImpl implements JSNullLiteral {

	public NullLiteralImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		return visitor.visit(this);
	}

}
