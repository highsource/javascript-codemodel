package org.hisrc.jscm.parser.literal;

import java.io.StringReader;
import java.math.BigInteger;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.parser.OctalIntegerLiteralParser;
import org.hisrc.jscm.parser.ParseException;

public class OctalIntegerParser implements TypedLiteralParser<BigInteger> {

	public static final TypedLiteralParser<BigInteger> INSTANCE = new OctalIntegerParser();

	public BigInteger parse(String string) throws ParseException {
		Validate.notNull(string);
		final OctalIntegerLiteralParser parser = new OctalIntegerLiteralParser(
				new StringReader(string));
		final BigInteger value = parser.OctalIntegerLiteral();
		return value;

	}
}
