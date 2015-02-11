package org.hisrc.jscm.parser.literal;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.parser.ParseException;

public class BooleanParser implements TypedLiteralParser<Boolean> {

	public static final TypedLiteralParser<Boolean> INSTANCE = new BooleanParser();

	public Boolean parse(String string) throws ParseException {
		Validate.notNull(string);
		return Boolean.valueOf(string);
	}
}
