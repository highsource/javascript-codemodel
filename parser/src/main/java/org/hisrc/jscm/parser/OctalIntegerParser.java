package org.hisrc.jscm.parser;

import java.io.StringReader;
import java.math.BigInteger;

import org.apache.commons.lang3.Validate;

public class OctalIntegerParser implements Parser<BigInteger> {

	public static final Parser<BigInteger> INSTANCE = new OctalIntegerParser();

	public BigInteger parse(String string) throws ParseException {
		Validate.notNull(string);
		final OctalIntegerLiteralParser parser = new OctalIntegerLiteralParser(
				new StringReader(string));
		final BigInteger value = parser.OctalIntegerLiteral();
		return value;

	}
}
