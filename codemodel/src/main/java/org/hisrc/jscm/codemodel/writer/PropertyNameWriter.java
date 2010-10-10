package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;
import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class PropertyNameWriter implements
		JSPropertyNameVisitor<Formatter, IOException> {

	private final Formatter f;

	public PropertyNameWriter(Formatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	public Formatter visitNumericLiteral(JSNumericLiteral value)
			throws IOException {
		return f.literal(value);
	}

	public Formatter visitStringLiteral(JSStringLiteral value)
			throws IOException {
		return f.literal(value);
	}

	@Override
	public Formatter visitIdentifierName(JSIdentifierName value)
			throws IOException {
		return f.identifier(value.getName());
	}

}
