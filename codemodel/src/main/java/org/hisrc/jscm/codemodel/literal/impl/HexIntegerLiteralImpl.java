package org.hisrc.jscm.codemodel.literal.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSHexIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;

public class HexIntegerLiteralImpl extends NumericLiteralImpl implements
		JSHexIntegerLiteral {

	private final BigInteger value;

	public HexIntegerLiteralImpl(JSCodeModel codeModel, BigInteger value) {
		super(codeModel, new BigDecimal(value));
		Validate.notNull(value);
		this.value = value;
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		return visitor.visit(this);
	}

	@Override
	public long asLong() {
		return value.longValue();
	}

	@Override
	public BigInteger asNumber() {
		return value;
	}

}
