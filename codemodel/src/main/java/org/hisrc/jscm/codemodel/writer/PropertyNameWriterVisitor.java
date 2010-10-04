package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;
import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public class PropertyNameWriterVisitor<A extends Appendable> implements
		JSPropertyNameVisitor<A, IOException> {

	private final A writer;

	public PropertyNameWriterVisitor(A writer) {
		Validate.notNull(writer);
		this.writer = writer;
	}

	@Override
	public A visitNumericLiteral(JSNumericLiteral literal) throws IOException {
		literal.acceptLiteralVisitor(new LiteralWriterVisitor<A>(writer));
		return writer;
	}

	@Override
	public A visitStringLiteral(JSStringLiteral literal) throws IOException {
		literal.acceptLiteralVisitor(new LiteralWriterVisitor<A>(writer));
		return writer;
	}

}
