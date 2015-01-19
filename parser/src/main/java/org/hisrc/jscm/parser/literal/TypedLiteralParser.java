package org.hisrc.jscm.parser.literal;

import org.hisrc.jscm.parser.ParseException;

public interface TypedLiteralParser<T> {

	public T parse(String value) throws ParseException;
}
