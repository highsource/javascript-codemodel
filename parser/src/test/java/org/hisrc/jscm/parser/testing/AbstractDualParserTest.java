package org.hisrc.jscm.parser.testing;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.debug.CommentingCodeWriter;
import org.hisrc.jscm.parser.EcmaScriptParser;
import org.hisrc.jscm.parser.ParseException;
import org.hisrc.jscm.parser.testing.util.AbstractDualResourceBasedTest;
import org.junit.Assert;

public abstract class AbstractDualParserTest extends
		AbstractDualResourceBasedTest {

	private EcmaScriptParser parse(String str) {
		final Reader reader = new StringReader(str);
		EcmaScriptParser parser = new EcmaScriptParser(reader);
		parser.enable_tracing();
		return parser;
	}

	@Override
	protected void check(String sourceResourceName, String targetResourceName) {
		final String source = getResourceAsString(sourceResourceName);
		final String target = getResourceAsString(targetResourceName);
		final List<String> targetLines = Arrays.asList(StringUtils.split(
				target, "\n\r"));
		JSProgram program = null;
		try {
			program = parse(source).Program();
			final StringBuffer sb = new StringBuffer();
			final CodeWriter codeWriter = new CommentingCodeWriter(sb);
			codeWriter.program(program);
			new CodeWriter(System.out).program(program);
			new CommentingCodeWriter(System.out).program(program);
			final String result = sb.toString();
			final List<String> resultLines = Arrays.asList(StringUtils.split(
					result, "\n\r"));
			compareLines(target, targetLines, result, resultLines);
		} catch (ParseException pex) {
			throw new AssertionError(pex);
		} catch (IOException ioex) {
			throw new AssertionError(ioex);
		}
	}

	private void compareLines(String source, List<String> sourceLines,
			String result, List<String> resultLines) {
		Validate.noNullElements(sourceLines);
		Validate.noNullElements(resultLines);
		final Iterator<String> sourceLinesIterator = sourceLines.iterator();
		final Iterator<String> resultLinesIterator = resultLines.iterator();
		int lineNumber = 0;
		while (sourceLinesIterator.hasNext() && resultLinesIterator.hasNext()) {
			lineNumber++;
			final String sourceLine = sourceLinesIterator.next();
			final String resultLine = resultLinesIterator.next();
			Assert.assertEquals(
					"Result code differs from the expected code at line ["
							+ lineNumber + "].\n"
							//
							+ "Source line:\n" + sourceLine + "\n"
							//
							+ "Result line:\n" + resultLine + "\n"
							//
							+ "Source:\n" + source + "\n"
							//
							+ "Result:\n" + result + "\n",
					//
					sourceLine, resultLine);
		}
		if (sourceLinesIterator.hasNext() || resultLinesIterator.hasNext()) {
			Assert.assertEquals(
					"Number of lines in source and result differs.\n"
					//
							+ "Source:\n" + source + "\n"
							//
							+ "Result:\n" + result + "\n",
					//
					sourceLines.size(), resultLines.size());

		}
	}
}
