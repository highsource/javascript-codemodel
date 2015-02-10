package org.hisrc.jscm.parser.testing;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.debug.CommentingCodeWriter;
import org.hisrc.jscm.parser.EcmaScriptParser;
import org.hisrc.jscm.parser.ParseException;
import org.hisrc.jscm.parser.testing.util.AbstractDualResourceBasedTest;

public abstract class AbstractDualParserTest extends AbstractDualResourceBasedTest {

	private EcmaScriptParser parse(String str) {
		final Reader reader = new StringReader(str);
		EcmaScriptParser parser = new EcmaScriptParser(reader);
		parser.enable_tracing();
		return parser;
	}

	@Override
	protected void check(String sourceResourceName, String targetResourceName) {
		final String source = getResourceAsString(sourceResourceName);
		JSProgram program = null;
		try {
			program = parse(source).Program();
			final CodeWriter codeWriter = new CommentingCodeWriter(System.out);
			codeWriter.program(program);
			System.out.println("");
		} catch (ParseException pex) {
			throw new AssertionError(pex);
		}
		catch(IOException ioex)
		{
			throw new AssertionError(ioex);
		}
	}

}
