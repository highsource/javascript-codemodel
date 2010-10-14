package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class PropertyNameWriter implements
		JSPropertyNameVisitor<CodeWriter, IOException> {

	private final CodeWriter f;

	public PropertyNameWriter(CodeWriter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	public CodeWriter visitNumericLiteral(JSNumericLiteral value)
			throws IOException {
		return f.literal(value);
	}

	public CodeWriter visitStringLiteral(JSStringLiteral value)
			throws IOException {
		return f.literal(value);
	}

	@Override
	public CodeWriter visitIdentifierName(JSIdentifierName value)
			throws IOException {
		return f.identifier(value.getName());
	}

}
