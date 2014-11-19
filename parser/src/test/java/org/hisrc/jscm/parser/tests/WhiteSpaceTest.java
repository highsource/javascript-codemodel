package org.hisrc.jscm.parser.tests;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import org.hisrc.jscm.parser.EcmaScriptParserConstants;
import org.hisrc.jscm.parser.EcmaScriptParserTokenManager;
import org.hisrc.jscm.parser.JavaCharStream;
import org.junit.Test;

public class WhiteSpaceTest {

	@Test
	public void readsWhiteSpace() {
		String cmd = ".\u0009\u000B\u000b\u000C\u000c \u0020\u00A0\u00a0\u1680\u2000\u200A\uFEFF.";
		JavaCharStream cs = new JavaCharStream(new StringReader(cmd));
		EcmaScriptParserTokenManager ltm = new EcmaScriptParserTokenManager(cs);
		assertEquals(EcmaScriptParserConstants.DOT, ltm.getNextToken().kind);
		assertEquals(EcmaScriptParserConstants.DOT, ltm.getNextToken().kind);
		assertEquals(EcmaScriptParserConstants.EOF, ltm.getNextToken().kind);
	}

}
