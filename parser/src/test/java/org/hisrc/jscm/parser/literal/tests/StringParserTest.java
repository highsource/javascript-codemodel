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
		// '\''
		Assert.assertEquals("'", StringParser.INSTANCE.parse("'\\\''"));
		// 'abcd'
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("'abcd'"));

		// SingleEscape
		// SingleEscapeCharacter :: one of
		// ' " \ b f n r t v
		Assert.assertEquals("'", StringParser.INSTANCE.parse("'\\\''"));
		Assert.assertEquals("\"", StringParser.INSTANCE.parse("'\\\"'"));
		Assert.assertEquals("\\", StringParser.INSTANCE.parse("'\\\\'"));
		Assert.assertEquals("\b", StringParser.INSTANCE.parse("'\\b'"));
		Assert.assertEquals("\f", StringParser.INSTANCE.parse("'\\f'"));
		Assert.assertEquals("\n", StringParser.INSTANCE.parse("'\\n'"));
		Assert.assertEquals("\r", StringParser.INSTANCE.parse("'\\r'"));
		Assert.assertEquals("\t", StringParser.INSTANCE.parse("'\\t'"));
		Assert.assertEquals("\u000B", StringParser.INSTANCE.parse("'\\v'"));
		// NonEscape
		Assert.assertEquals("a", StringParser.INSTANCE.parse("'\\a'"));
		try {
			// TODO this must fail
			StringParser.INSTANCE.parse("'\\x'");
			Assert.fail();
		} catch (ParseException pex) {

		}
		try {
			Assert.assertEquals("u", StringParser.INSTANCE.parse("'\\u'"));
			Assert.fail();
		} catch (ParseException pex) {

		}
		try {
			Assert.assertEquals("8", StringParser.INSTANCE.parse("'\\8'"));
			Assert.fail();
		} catch (ParseException pex) {

		}
		try {
			Assert.assertEquals("9", StringParser.INSTANCE.parse("'\\9'"));
			Assert.fail();
		} catch (ParseException pex) {

		}
		// HexEscape
		Assert.assertEquals("\u0012", StringParser.INSTANCE.parse("'\\x12'"));
		// UnicodeEscape
		Assert.assertEquals("\u0123", StringParser.INSTANCE.parse("'\\u0123'"));
		// LineContinuation
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("'ab\\\r\ncd'"));
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("'ab\\\ncd'"));
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("'ab\\\rcd'"));
		Assert.assertEquals("abcd",
				StringParser.INSTANCE.parse("'ab\\\u2028cd'"));
		Assert.assertEquals("abcd",
				StringParser.INSTANCE.parse("'ab\\\u2029cd'"));
	}

	@Test
	public void checkDouble() throws ParseException {
		// ""
		Assert.assertEquals("", StringParser.INSTANCE.parse("\"\""));
		// "\""
		Assert.assertEquals("\"", StringParser.INSTANCE.parse("\"\\\"\""));
		// "'"
		Assert.assertEquals("'", StringParser.INSTANCE.parse("\"'\""));
		// "\'"
		Assert.assertEquals("'", StringParser.INSTANCE.parse("\"\\'\""));
		// "abcd"
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("\"abcd\""));

		// SingleEscape
		// SingleEscapeCharacter :: one of
		// ' " \ b f n r t v
		Assert.assertEquals("'", StringParser.INSTANCE.parse("\"\\\'\""));
		Assert.assertEquals("\"", StringParser.INSTANCE.parse("\"\\\"\""));
		Assert.assertEquals("\\", StringParser.INSTANCE.parse("\"\\\\\""));
		Assert.assertEquals("\b", StringParser.INSTANCE.parse("\"\\b\""));
		Assert.assertEquals("\f", StringParser.INSTANCE.parse("\"\\f\""));
		Assert.assertEquals("\n", StringParser.INSTANCE.parse("\"\\n\""));
		Assert.assertEquals("\r", StringParser.INSTANCE.parse("\"\\r\""));
		Assert.assertEquals("\t", StringParser.INSTANCE.parse("\"\\t\""));
		Assert.assertEquals("\u000B", StringParser.INSTANCE.parse("\"\\v\""));
		// NonEscape
		Assert.assertEquals("a", StringParser.INSTANCE.parse("\"\\a\""));
		try {
			// TODO this must fail
			StringParser.INSTANCE.parse("\"\\x\"");
			Assert.fail();
		} catch (ParseException pex) {

		}
		try {
			Assert.assertEquals("u", StringParser.INSTANCE.parse("\"\\u\""));
			Assert.fail();
		} catch (ParseException pex) {

		}
		try {
			Assert.assertEquals("8", StringParser.INSTANCE.parse("\"\\8\""));
			Assert.fail();
		} catch (ParseException pex) {

		}
		try {
			Assert.assertEquals("9", StringParser.INSTANCE.parse("\"\\9\""));
			Assert.fail();
		} catch (ParseException pex) {

		}
		// HexEscape
		Assert.assertEquals("\u0012", StringParser.INSTANCE.parse("\"\\x12\""));
		// UnicodeEscape
		Assert.assertEquals("\u0123", StringParser.INSTANCE.parse("\"\\u0123\""));
		// LineContinuation
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("\"ab\\\r\ncd\""));
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("\"ab\\\ncd\""));
		Assert.assertEquals("abcd", StringParser.INSTANCE.parse("\"ab\\\rcd\""));
		Assert.assertEquals("abcd",
				StringParser.INSTANCE.parse("\"ab\\\u2028cd\""));
		Assert.assertEquals("abcd",
				StringParser.INSTANCE.parse("\"ab\\\u2029cd\""));
	}
}
