package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.JSLiteralVisitor;
import org.hisrc.jscm.codemodel.JSNullLiteral;
import org.hisrc.jscm.codemodel.JSStringLiteral;

public class LiteralWriterVisitor<A extends Appendable> implements
		JSLiteralVisitor<A, IOException> {

	private final A writer;

	public LiteralWriterVisitor(A writer) {
		Validate.notNull(writer);
		this.writer = writer;
	}

	@Override
	public A visit(JSStringLiteral value) throws IOException {

		// TODO Very naive
		writer.append('"').append(value.asString()).append('"');
		return writer;
	}

	@Override
	public A visit(JSNullLiteral value) throws IOException {
		writer.append("null");
		return writer;
	}

	@Override
	public A visit(JSBooleanLiteral value) throws IOException {
		writer.append(Boolean.toString(value.asBoolean()));
		return writer;
	}

	@Override
	public A visit(JSDecimalIntegerLiteral value) throws IOException {
		writer.append(Long.toString(value.asLong()));
		return writer;
	}

	@Override
	public A visit(JSDecimalNonIntegerLiteral value) throws IOException {
		writer.append(Double.toString(value.asDouble()));
		return writer;
	}

}
