package org.hisrc.jscm.parser.literal.tests;

import org.hisrc.jscm.parser.ParseException;
import org.hisrc.jscm.parser.literal.StringParser;
import org.junit.Assert;
import org.junit.Test;

public class StringParserTest {

	@Test
	public void checkSingle() throws ParseException {
		// ''
		Assert.assertEquals("", StringParser.INSTANCE.parse("''"));
		// '"'
		Assert.assertEquals("\"", StringParser.INSTANCE.parse("'\"'"));
		// '\"'
		Assert.assertEquals("\"", StringParser.INSTANCE.parse("'\\\"'"));
		// 'abcd'
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("'abcd'"));
	}

	@Test
	public void checkDouble() throws ParseException {
		// ""
		Assert.assertEquals("", StringParser.INSTANCE.parse("\"\""));
		// "'" 
		Assert.assertEquals("'", StringParser.INSTANCE.parse("\"'\""));
		// "\'"
		Assert.assertEquals("'", StringParser.INSTANCE.parse("\"\\'\""));
		// "\'"
		Assert.assertEquals("'", StringParser.INSTANCE.parse("\"\\\'\""));
		// "abcd"
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("\"abcd\""));
	}
}
