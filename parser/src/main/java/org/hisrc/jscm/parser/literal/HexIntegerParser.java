package org.hisrc.jscm.parser.literal;

import java.math.BigInteger;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.parser.ParseException;

public class HexIntegerParser implements TypedLiteralParser<BigInteger> {

	public static final TypedLiteralParser<BigInteger> INSTANCE = new HexIntegerParser();

	public BigInteger parse(String string) throws ParseException {
		Validate.notNull(string);
		return new BigInteger(string, 16);
//		final HexIntegerLiteralParser parser = new HexIntegerLiteralParser(
//				new StringReader(string));
//		final BigInteger hexInteger = parser.HexIntegerLiteral();
//		return hexInteger;
	}
}
