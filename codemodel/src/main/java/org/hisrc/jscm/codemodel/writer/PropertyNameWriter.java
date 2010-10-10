package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;
import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class PropertyNameWriter implements
		JSPropertyNameVisitor<JSFormatter, IOException> {

	private final JSFormatter f;

	public PropertyNameWriter(JSFormatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	public JSFormatter visitNumericLiteral(JSNumericLiteral value)
			throws IOException {
		return f.literal(value);
	}

	public JSFormatter visitStringLiteral(JSStringLiteral value)
			throws IOException {
		return f.literal(value);
	}

	@Override
	public JSFormatter visitIdentifierName(JSIdentifierName value)
			throws IOException {
		return f.identifier(value.getName());
	}

}
