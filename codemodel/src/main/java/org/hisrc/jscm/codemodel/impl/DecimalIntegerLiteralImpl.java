package org.hisrc.jscm.codemodel.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.JSLiteralVisitor;

public class DecimalIntegerLiteralImpl extends DecimalLiteralImpl implements
		JSDecimalIntegerLiteral {

	private final long valueAsLong;

	public DecimalIntegerLiteralImpl(JSCodeModel codeModel, long value) {
		super(codeModel, new BigDecimal(value));
		Validate.notNull(value);
		this.valueAsLong = value;
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long asLong() {
		return valueAsLong;
	}

}
