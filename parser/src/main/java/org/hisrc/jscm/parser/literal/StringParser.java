package org.hisrc.jscm.parser.literal;

import java.io.StringReader;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.parser.ParseException;
import org.hisrc.jscm.parser.StringLiteralParser;

public class StringParser implements TypedLiteralParser<String> {

	public static final TypedLiteralParser<String> INSTANCE = new StringParser();

	public String parse(String string) throws ParseException {
		Validate.notNull(string);
		final StringLiteralParser parser = new StringLiteralParser(
				new StringReader(string));
		final String value = parser.StringLiteral();
		return value;
	}
}
