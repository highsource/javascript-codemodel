package org.hisrc.jscm.parser.testing.lexical.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.hisrc.jscm.parser.testing.lexical.EcmaScriptParserTestConstants;
import org.hisrc.jscm.parser.testing.lexical.LTokenCase;
import org.hisrc.jscm.parser.testing.lexical.LTokenCasesParser;
import org.hisrc.jscm.parser.testing.lexical.ParseException;
import org.junit.Test;

public class LTokenCasesParserTest {

	@Test
	public void parsesResource() throws IOException {
		String resource = "Test01.lexical.md";
		List<LTokenCase> tokenCases = parseTokenCasesFromResource(resource);
		Assert.assertEquals(2, tokenCases.size());
	}

	private List<LTokenCase> parseTokenCasesFromResource(String resource)
			throws IOException {
		InputStream is = null;
		Reader reader = null;
		try {
			is = getClass().getResourceAsStream(resource);
			reader = new InputStreamReader(is, "UTF-8");
			LTokenCasesParser parser = new LTokenCasesParser(reader);
			parser.setTokenFactory(EcmaScriptParserTestConstants.ECMASCRIPT_PARSER_TOKEN_FACTORY);
			parser.enable_tracing();
			return parser.LTokenCases();

		} catch (UnsupportedEncodingException ueex) {
			throw new UnsupportedOperationException(ueex);
		} catch (ParseException pex) {
			throw new IOException(pex);
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(is);

		}
	}

}
