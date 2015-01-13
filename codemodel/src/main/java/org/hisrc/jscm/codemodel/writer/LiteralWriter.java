package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSHexIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSOctalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class LiteralWriter implements
		JSLiteralVisitor<CodeWriter, IOException> {

	protected final CodeWriter f;

	public LiteralWriter(CodeWriter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public CodeWriter visit(JSStringLiteral value) throws IOException {

		return f.string(value.asString());
	}

	@Override
	public CodeWriter visit(JSNullLiteral value) throws IOException {
		return f._null();
	}

	@Override
	public CodeWriter visit(JSBooleanLiteral value) throws IOException {
		return f._boolean(value.asBoolean());
	}

	@Override
	public CodeWriter visit(JSDecimalIntegerLiteral value) throws IOException {
		return f.decimal(value.asDecimal());
	}

	@Override
	public CodeWriter visit(JSDecimalNonIntegerLiteral value)
			throws IOException {
		return f.decimal(value.asDecimal());
	}
	
	@Override
	public CodeWriter visit(JSHexIntegerLiteral value) throws IOException {
		return f.hexInteger(value.asNumber());
	}

	@Override
	public CodeWriter visit(JSOctalIntegerLiteral value) throws IOException {
		return f.octalInteger(value.asNumber());
	}
}
