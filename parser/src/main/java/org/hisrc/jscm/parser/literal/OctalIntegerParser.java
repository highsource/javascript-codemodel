package org.hisrc.jscm.parser.literal;

import java.math.BigInteger;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.parser.ParseException;

public class OctalIntegerParser implements TypedLiteralParser<BigInteger> {

	public static final TypedLiteralParser<BigInteger> INSTANCE = new OctalIntegerParser();

	public BigInteger parse(String string) throws ParseException {
		Validate.notNull(string);
		Validate.isTrue(string.length() > 1);
		Validate.isTrue(string.substring(0, 1).equals("0"));
		return new BigInteger(string, 8);
	}
}
