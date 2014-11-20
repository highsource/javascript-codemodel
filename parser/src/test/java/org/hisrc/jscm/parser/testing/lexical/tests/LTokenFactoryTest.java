package org.hisrc.jscm.parser.testing.lexical.tests;

import org.hisrc.jscm.parser.EcmaScriptParserConstants;
import org.hisrc.jscm.parser.testing.lexical.LToken;
import org.hisrc.jscm.parser.testing.lexical.LTokenFactory;
import org.hisrc.jscm.parser.tests.EcmaScriptParserTestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LTokenFactoryTest {

	private LTokenFactory lexicalTokenFactory;

	@Before
	public void initializeLexicalTokenFactory() {
		this.lexicalTokenFactory = EcmaScriptParserTestConstants.ECMASCRIPT_PARSER_TOKEN_FACTORY;
	}

	@Test
	public void producesLexicalTokens() {
		Assert.assertNotNull(this.lexicalTokenFactory);

		Assert.assertEquals(new LToken("DOT", EcmaScriptParserConstants.DOT,
				"."), this.lexicalTokenFactory.createToken("DOT", "."));
		Assert.assertEquals(new LToken("DOT", EcmaScriptParserConstants.DOT,
				"."), this.lexicalTokenFactory.createToken(
				EcmaScriptParserConstants.DOT, "."));
		Assert.assertEquals(new LToken("IDENTIFIER_NAME",
				EcmaScriptParserConstants.IDENTIFIER_NAME, "\"foo\""),
				this.lexicalTokenFactory.createToken("IDENTIFIER_NAME",
						"\"foo\""));
	}
}
