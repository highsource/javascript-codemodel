package org.hisrc.jscm.parser;

import java.io.StringReader;
import java.math.BigInteger;

import org.apache.commons.lang3.Validate;

public class HexIntegerParser implements Parser<BigInteger> {

	public static final Parser<BigInteger> INSTANCE = new HexIntegerParser();

	public BigInteger parse(String hexIntegerLiteral) throws ParseException {
		Validate.notNull(hexIntegerLiteral);
		final HexIntegerLiteralParser hilp = new HexIntegerLiteralParser(
				new StringReader(hexIntegerLiteral));
		final BigInteger hexInteger = hilp.HexIntegerLiteral();
		return hexInteger;

	}
}
