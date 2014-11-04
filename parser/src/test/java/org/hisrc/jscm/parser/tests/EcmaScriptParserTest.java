package org.hisrc.jscm.parser.tests;

import java.io.Reader;
import java.io.StringReader;

import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression;
import org.hisrc.jscm.parser.EcmaScriptParser;
import org.hisrc.jscm.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class EcmaScriptParserTest {

	private EcmaScriptParser parse(String str) {
		final Reader reader = new StringReader(str);
		EcmaScriptParser parser = new EcmaScriptParser(reader);
		parser.enable_tracing();
		return parser;
	}

	@Test
	public void parsesThis() throws ParseException {
		final JSPrimaryExpression _this = parse("this").PrimaryExpression();
		Assert.assertNotNull(_this);
	}
}
