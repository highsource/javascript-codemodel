package org.hisrc.jscm.parser.literal;

import java.math.BigInteger;

public interface IntegerBuilder {

	public IntegerBuilder append(String digit);
	public BigInteger value();

}
