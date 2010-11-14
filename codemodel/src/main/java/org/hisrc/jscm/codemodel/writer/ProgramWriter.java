package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.util.List;

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
		final List<JSSourceElement> sourceElements = value.getSourceElements();
		for (int index = 0, size = sourceElements.size(); index < size; index++) {
			if (index > 0) {
				f.lineTerminator();
			}
			final JSSourceElement sourceElement = sourceElements.get(index);
			f.sourceElement(sourceElement);

		}
		return f;
	}

}
