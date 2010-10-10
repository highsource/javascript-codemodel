package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class LiteralWriter implements
		JSLiteralVisitor<Formatter, IOException> {

	private final Formatter f;

	public LiteralWriter(Formatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public Formatter visit(JSStringLiteral value) throws IOException {

		return f.string(value.asString());
	}

	@Override
	public Formatter visit(JSNullLiteral value) throws IOException {
		return f._null();
	}

	@Override
	public Formatter visit(JSBooleanLiteral value) throws IOException {
		return f._boolean(value.asBoolean());
	}

	@Override
	public Formatter visit(JSDecimalIntegerLiteral value) throws IOException {
		return f.decimal(value.asDecimal());
	}

	@Override
	public Formatter visit(JSDecimalNonIntegerLiteral value)
			throws IOException {
		return f.decimal(value.asDecimal());
	}

}
