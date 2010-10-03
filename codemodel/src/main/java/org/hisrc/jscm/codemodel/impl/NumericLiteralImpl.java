package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSNumericLiteral;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;

public abstract class NumericLiteralImpl extends LiteralImpl implements
		JSNumericLiteral {

	private final Number valueAsNumber;

	public NumericLiteralImpl(JSCodeModel codeModel, Number value) {
		super(codeModel);
		Validate.notNull(value);
		this.valueAsNumber = value;
	}

	@Override
	public Number asNumber() {
		return valueAsNumber;
	}

	@Override
	public <V, E extends Exception> V acceptPropertyNameVisitor(
			JSPropertyNameVisitor<V, E> visitor) throws E {
		return visitor.visitNumericLiteral(this);
	}
}
