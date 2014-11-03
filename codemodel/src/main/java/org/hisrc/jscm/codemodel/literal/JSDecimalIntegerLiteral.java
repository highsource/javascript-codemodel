package org.hisrc.jscm.codemodel.literal;

import java.math.BigInteger;

public interface JSDecimalIntegerLiteral extends JSDecimalLiteral {

	public long asLong();

	@Override
	public BigInteger asNumber();
}
