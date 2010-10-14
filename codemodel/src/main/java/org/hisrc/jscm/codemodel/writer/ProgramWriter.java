package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.JSProgramVisitor;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.lang.Validate;

public class ProgramWriter implements JSProgramVisitor<CodeWriter, IOException> {

	private final CodeWriter f;

	public ProgramWriter(CodeWriter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public CodeWriter visitProgram(JSProgram value) throws IOException {
		Validate.notNull(value);
		for (JSSourceElement sourceElement : value.getSourceElements()) {
			f.sourceElement(sourceElement);

		}
		return f;
	}

}
