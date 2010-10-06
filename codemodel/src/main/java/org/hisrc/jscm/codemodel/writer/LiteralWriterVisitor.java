package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class LiteralWriterVisitor implements
		JSLiteralVisitor<JSFormatter, IOException> {

	private final JSFormatter f;

	public LiteralWriterVisitor(JSFormatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public JSFormatter visit(JSStringLiteral value) throws IOException {

		return f.string(value.asString());
	}

	@Override
	public JSFormatter visit(JSNullLiteral value) throws IOException {
		return f._null();
	}

	@Override
	public JSFormatter visit(JSBooleanLiteral value) throws IOException {
		return f._boolean(value.asBoolean());
	}

	@Override
	public JSFormatter visit(JSDecimalIntegerLiteral value) throws IOException {
		return f.decimal(value.asDecimal());
	}

	@Override
	public JSFormatter visit(JSDecimalNonIntegerLiteral value)
			throws IOException {
		return f.decimal(value.asDecimal());
	}

}
