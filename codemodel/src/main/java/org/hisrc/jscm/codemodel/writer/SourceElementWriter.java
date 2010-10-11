package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.JSSourceElementVisitor;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.statement.JSStatement;

public class SourceElementWriter implements
		JSSourceElementVisitor<CodeWriter, IOException> {

	private final CodeWriter f;

	public SourceElementWriter(CodeWriter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public CodeWriter visitFunctionDeclaration(JSFunctionDeclaration value)
			throws IOException {
		f.keyword("function").whiteSpace();

		f.identifier(value.getName());

		f.openRoundBracket();
		for (int index = 0; index < value.getParameters().size(); index++) {
			final JSVariable parameter = value.getParameters().get(index);
			if (index > 0) {
				f.comma().whiteSpace();
			}
			f.identifier(parameter.getName());
		}
		f.closeRoundBracket();
		f.whiteSpace();
		f.openCurlyBracket().lineTerminator();

		final CodeWriter fi = f.indented();
		final List<JSSourceElement> sourceElements = value.getBody()
				.getSourceElements();

		for (int index = 0; index < sourceElements.size(); index++) {
			final JSSourceElement sourceElement = sourceElements.get(index);
			fi.sourceElement(sourceElement);
			fi.lineTerminator();
		}

		f.closeCurlyBracket();
		return f;
	}

	@Override
	public CodeWriter visitStatement(JSStatement value) throws IOException {
		return f.statement(value);
	}

}
