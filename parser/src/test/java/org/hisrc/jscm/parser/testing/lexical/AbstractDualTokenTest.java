package org.hisrc.jscm.parser.testing.lexical;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang3.StringEscapeUtils;
import org.hisrc.jscm.parser.BackupCharStream;
import org.hisrc.jscm.parser.CharStream;
import org.hisrc.jscm.parser.EcmaScriptParserConstants;
import org.hisrc.jscm.parser.EcmaScriptParserTokenManager;
import org.hisrc.jscm.parser.Token;
import org.hisrc.jscm.parser.TokenMgrError;
import org.hisrc.jscm.parser.testing.util.AbstractDualResourceBasedTest;
import org.hisrc.jscm.parser.tests.EcmaScriptParserTestConstants;

public abstract class AbstractDualTokenTest extends
		AbstractDualResourceBasedTest {

	private final LTokenFactory tokenFactory = EcmaScriptParserTestConstants.ECMASCRIPT_PARSER_TOKEN_FACTORY;

	protected boolean isInputEscaped() {
		return false;
	}

	private List<LToken> parseTokens(String input) {

		final CharStream stream = isInputEscaped() ? new BackupCharStream(
				new StringReader(StringEscapeUtils.unescapeEcmaScript(input)))
				: new BackupCharStream(new StringReader(input));
		final EcmaScriptParserTokenManager tokenManager = new EcmaScriptParserTokenManager(
				stream);
		final List<LToken> tokens = new LinkedList<LToken>();
		{
			try {
				for (Token t = tokenManager.getNextToken(); t != null
						&& t.kind != EcmaScriptParserConstants.EOF; t = tokenManager
						.getNextToken()) {
					final LToken token = this.tokenFactory.createToken(t.kind,
							t.image);
					tokens.add(token);
				}

			} catch (TokenMgrError tmer) {
				throw tmer;
			}
		}
		return new ArrayList<LToken>(tokens);
	}

	@Override
	protected void check(String sourceResourceName, String targetResourceName) {
		final String input = getResourceAsString(sourceResourceName);
		final List<LToken> tokens = parseTokens(input);
		final List<String> etalons = getResourceAsLines(targetResourceName);
		Assert.assertEquals("Number of tokens and number of etalons mismatch.",
				etalons.size(), tokens.size());
		for (int index = 0; index < tokens.size(); index++) {
			LToken token = tokens.get(index);
			String etalon = StringEscapeUtils.unescapeJava(etalons.get(index));
			Assert.assertEquals("Token and etalon mismatch at position ["
					+ index + "].", etalon, token.getSignature());
		}
	}
}
