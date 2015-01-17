package org.hisrc.jscm.parser;

public interface Parser<T> {

	public T parse(String value) throws ParseException;
}
