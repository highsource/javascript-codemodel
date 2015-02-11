package org.hisrc.jscm.parser.literal;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.parser.ParseException;

public class DecimalParser implements TypedLiteralParser<BigDecimal> {

	public static final TypedLiteralParser<BigDecimal> INSTANCE = new DecimalParser();

	public BigDecimal parse(String string) throws ParseException {
		Validate.notNull(string);
		return new BigDecimal(string);
	}
}
