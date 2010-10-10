package org.hisrc.jscm.codemodel.literal.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class StringLiteralImpl extends LiteralImpl implements JSStringLiteral {

	private final String value;

	public StringLiteralImpl(JSCodeModel codeModel, String value) {
		super(codeModel);
		Validate.notNull(value);
		this.value = value;
	}

	@Override
	public String asString() {
		return value;
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		return visitor.visit(this);
	}

	@Override
	public <V, E extends Exception> V acceptPropertyNameVisitor(
			JSPropertyNameVisitor<V, E> visitor) throws E {
		return visitor.visitStringLiteral(this);
	}

}
