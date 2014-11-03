package org.hisrc.jscm.parser.tests;

import java.io.Reader;
import java.io.StringReader;

import org.hisrc.jscm.codemodel.literal.JSLiteral;
import org.hisrc.jscm.parser.EcmaScriptParser;
import org.hisrc.jscm.parser.ParseException;
import org.junit.Test;

public class EcmaScriptParserTest {

	@Test
	public void parses() throws ParseException {
		
		final Reader reader = new StringReader("12.34");
		EcmaScriptParser parser = new EcmaScriptParser(reader);
		parser.enable_tracing();
		final JSLiteral literal = parser.Literal();
		literal.toString();

	}
}
