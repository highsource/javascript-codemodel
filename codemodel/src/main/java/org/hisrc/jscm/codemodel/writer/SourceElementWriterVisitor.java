package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.JSSourceElementVisitor;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.statement.JSStatement;

public class SourceElementWriterVisitor implements
		JSSourceElementVisitor<JSFormatter, IOException> {

	private final JSFormatter f;

	public SourceElementWriterVisitor(JSFormatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public JSFormatter visitFunctionDeclaration(JSFunctionDeclaration value)
			throws IOException {
		f.keyword("function");

		f.identifier(value.getName());
		
		f.openRoundBracket();
		for (int index = 0; index < value.getParameters().size(); index++) {
			final JSVariable parameter = value.getParameters().get(index);
			if (index > 0) {
				f.comma();
			}
			f.identifier(parameter.getName());
		}
		f.closeRoundBracket();
		f.openCurlyBracket();

		JSFormatter fi = f.indented();

		fi.lineBreak();
		for (JSSourceElement sourceElement : value.getBody()
				.getSourceElements()) {
			sourceElement
					.acceptSourceElementVisitor(new SourceElementWriterVisitor(
							fi));
			fi.lineBreak();
			
		}

		f.closeCurlyBracket();
		return f;
	}

	@Override
	public JSFormatter visitStatement(JSStatement value) throws IOException {
		return value.acceptStatementVisitor(new StatementWriterVisitor(f));
	}

}
