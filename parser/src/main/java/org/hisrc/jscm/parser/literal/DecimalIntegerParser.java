package org.hisrc.jscm.parser.literal;

import java.math.BigInteger;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.parser.ParseException;

public class DecimalIntegerParser implements TypedLiteralParser<BigInteger> {

	public static final TypedLiteralParser<BigInteger> INSTANCE = new DecimalIntegerParser();

	public BigInteger parse(String string) throws ParseException {
		Validate.notNull(string);
		return new BigInteger(string);
	}
}
