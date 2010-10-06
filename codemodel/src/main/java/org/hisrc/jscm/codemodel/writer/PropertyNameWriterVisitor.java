package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;
import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class PropertyNameWriterVisitor implements
		JSPropertyNameVisitor<JSFormatter, IOException> {

	private final JSFormatter f;

	public PropertyNameWriterVisitor(JSFormatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	public JSFormatter visitNumericLiteral(JSNumericLiteral value)
			throws IOException {
		value.acceptLiteralVisitor(new LiteralWriterVisitor(f));
		return f;
	}

	public JSFormatter visitStringLiteral(JSStringLiteral value)
			throws IOException {
		value.acceptLiteralVisitor(new LiteralWriterVisitor(f));
		return f;
	}

	@Override
	public JSFormatter visitIdentifierName(JSIdentifierName value)
			throws IOException {
		return f.identifier(value.getName());
	}

}
