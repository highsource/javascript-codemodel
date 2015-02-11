package org.hisrc.jscm.parser.literal;

import java.math.BigInteger;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.parser.ParseException;

public class HexIntegerParser implements TypedLiteralParser<BigInteger> {

	public static final TypedLiteralParser<BigInteger> INSTANCE = new HexIntegerParser();

	public BigInteger parse(String string) throws ParseException {
		Validate.notNull(string);
		Validate.isTrue(string.length() > 2);
		Validate.isTrue(string.substring(0, 2).equalsIgnoreCase("0x"));
		return new BigInteger(string.substring(2), 16);
	}
}
