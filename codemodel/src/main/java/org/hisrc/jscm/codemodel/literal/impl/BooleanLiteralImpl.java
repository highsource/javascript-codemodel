package org.hisrc.jscm.codemodel.literal.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;

public class BooleanLiteralImpl extends LiteralImpl implements JSBooleanLiteral {

	private final boolean value;

	public BooleanLiteralImpl(JSCodeModel codeModel, boolean value) {
		super(codeModel);
		this.value = value;
	}

	@Override
	public boolean asBoolean() {

		return value;
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		return visitor.visit(this);
	}

}
