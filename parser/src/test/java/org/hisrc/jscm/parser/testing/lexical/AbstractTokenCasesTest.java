package org.hisrc.jscm.parser.testing.lexical;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.hisrc.jscm.parser.EcmaScriptParserConstants;
import org.hisrc.jscm.parser.EcmaScriptParserTokenManager;
import org.hisrc.jscm.parser.JavaCharStream;
import org.hisrc.jscm.parser.Token;
import org.hisrc.jscm.parser.tests.EcmaScriptParserTestConstants;
import org.junit.Test;

public abstract class AbstractTokenCasesTest {

	private final LTokenFactory tokenFactory = EcmaScriptParserTestConstants.ECMASCRIPT_PARSER_TOKEN_FACTORY;
	private final String resourceNamePattern = getClass().getSimpleName()
			+ ".{0,number,00}.lexical.md";

	private String getResourceNamePattern() {
		return resourceNamePattern;
	}

	@Test
	public void checksTokenCasesFromResources() {
		boolean resourceExists = true;
		int i = 0;
		String resourceName;
		do {
			resourceName = MessageFormat.format(getResourceNamePattern(), i++);
			resourceExists = checkTokenCasesFromResource(resourceName);
		} while (resourceExists);
		Assert.assertTrue("At least one token case is expected.", i > 0);
	}

	private boolean checkTokenCasesFromResource(String resourceName) {
		InputStream is = null;
		Reader reader = null;
		try {
			is = getClass().getResourceAsStream(resourceName);
			if (is == null) {
				return false;
			}
			reader = new InputStreamReader(is, "UTF-8");
			final List<LTokenCase> tokenCases = parseTokenCasesFromReader(reader);
			for (LTokenCase tokenCase : tokenCases) {
				checkTokenCase(tokenCase);
			}
			return true;
		} catch (UnsupportedEncodingException ueex) {
			throw new UnsupportedOperationException(ueex);
		} catch (ParseException pex) {
			throw new AssertionError(new IOException(MessageFormat.format(
					"Error parsing the resource [{0}].", resourceName), pex));
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(is);
		}
	}

	private List<LTokenCase> parseTokenCasesFromReader(Reader reader)
			throws ParseException {
		final LTokenCasesParser parser = new LTokenCasesParser(reader);
		parser.setTokenFactory(this.tokenFactory);
		parser.enable_tracing();
		return parser.LTokenCases();
	}

	private void checkTokenCase(LTokenCase tokenCase) {
		final String input = tokenCase.getInput();
		final JavaCharStream stream = new JavaCharStream(
				new StringReader(input));
		final EcmaScriptParserTokenManager tokenManager = new EcmaScriptParserTokenManager(
				stream);
		final List<LToken> expectedTokensList = tokenCase.getExpectedTokens();
		final LToken[] expectedTokens = expectedTokensList
				.toArray(new LToken[expectedTokensList.size() + 1]);
		{
			int index = 0;
			LToken expectedToken;
			LToken receivedToken;
			for (Token token = tokenManager.getNextToken(); token != null
					&& token.kind != EcmaScriptParserConstants.EOF; token = tokenManager
					.getNextToken()) {
				receivedToken = this.tokenFactory.createToken(token.kind,
						token.image);
				if (index >= expectedTokens.length) {
					throw new AssertionError(
							MessageFormat
									.format("Test case expects only [{0}] tokens, but token manager returns more than that.\n"
											+ "Expected tokens are:\n{1}"
											+ "\nThe last delivered token is:\n{2}",
											expectedTokens.length,
											expectedTokensList, receivedToken));
				}
				expectedToken = expectedTokens[index++];

				if (!expectedToken.equals(receivedToken)) {
					throw new AssertionError(MessageFormat.format(
							"Encountered a problem at token index [{0}] with input:\n{1}\n"
									+ "Expected:\n{2}\n" + "Received:\n{3}",
							index, input, expectedToken, receivedToken));
				}
			}
			if (index < expectedTokensList.size()) {
				throw new AssertionError(MessageFormat.format(
						"Test case expects [{0}] tokens, but token manager had only [{1}].\n"
								+ "Remaining expected tokens are:\n{2}",
						expectedTokensList.size(),
						index,
						expectedTokensList.subList(index+1,
								expectedTokensList.size() - 1)));
			}

		}
	}
}
