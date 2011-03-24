package org.hisrc.jscm.codemodel.writer.test;

import java.io.IOException;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.junit.Test;

public class CodeWriterTest {
	@Test
	public void allExpressions() throws IOException {

		final CodeWriter out = new CodeWriter(System.out);
		JSCodeModel codeModel = new CodeModelImpl();

		JSProgram program = codeModel.program();
		
		program._if(codeModel._boolean(true))._then().block()._return();
		program._if(codeModel._boolean(false))._then().block()._return();
		
		out.program(program);
	}

}
