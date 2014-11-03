package org.hisrc.jscm.codemodel.literal;

import java.math.BigInteger;

public interface JSHexIntegerLiteral extends JSNumericLiteral {

	public char[] asDigits();

	public byte[] asBytes();
	
	public long asLong();
	
	public BigInteger asNumber();
	
	public BigInteger asInteger();
}
