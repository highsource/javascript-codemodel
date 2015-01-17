package org.hisrc.jscm.parser;

import java.io.StringReader;
import java.math.BigInteger;

import org.apache.commons.lang3.Validate;

public class HexIntegerParser implements Parser<BigInteger> {

	public static final Parser<BigInteger> INSTANCE = new HexIntegerParser();

	public BigInteger parse(String string) throws ParseException {
		Validate.notNull(string);
		final HexIntegerLiteralParser parser = new HexIntegerLiteralParser(
				new StringReader(string));
		final BigInteger hexInteger = parser.HexIntegerLiteral();
		return hexInteger;

	}
}
