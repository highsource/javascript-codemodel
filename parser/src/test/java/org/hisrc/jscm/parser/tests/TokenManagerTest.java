package org.hisrc.jscm.parser.tests;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.hisrc.jscm.parser.EcmaScriptParserConstants;
import org.hisrc.jscm.parser.EcmaScriptParserTokenManager;
import org.hisrc.jscm.parser.SimpleCharStream;
import org.hisrc.jscm.parser.Token;
import org.junit.Test;

public class TokenManagerTest {

	@Test
	public void readsWhiteSpace() {
		String cmd = ".\u0009\u000B\u000b\u000C\u000c \u0020\u00A0\u00a0\u1680\u2000\u200A\uFEFF.";
		SimpleCharStream cs = new SimpleCharStream(new StringReader(cmd));
		EcmaScriptParserTokenManager ltm = new EcmaScriptParserTokenManager(cs);
		Token nextToken = ltm.getNextToken();
		assertEquals(EcmaScriptParserConstants.DOT, nextToken.kind);
		nextToken = ltm.getNextToken();
		assertEquals(EcmaScriptParserConstants.DOT, nextToken.kind);
		nextToken = ltm.getNextToken();
		assertEquals(EcmaScriptParserConstants.EOF, nextToken.kind);
	}
	
	@Test
	public void readsIdentifier() {
		String cmd = "foo";
		SimpleCharStream cs = new SimpleCharStream(new StringReader(cmd));
		EcmaScriptParserTokenManager ltm = new EcmaScriptParserTokenManager(cs);
		final Token nextToken = ltm.getNextToken();
		assertEquals(EcmaScriptParserConstants.IDENTIFIER_NAME, nextToken.kind);
	}

}
