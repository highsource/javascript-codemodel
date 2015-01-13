package org.hisrc.jscm.codemodel.writer.debug;

import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.ExpressionWriter;
import org.hisrc.jscm.codemodel.writer.LiteralWriter;

public class CommentingCodeWriter extends CodeWriter {

	public CommentingCodeWriter(Appendable writer) {
		super(writer);
	}

	@Override
	protected ExpressionWriter createExpressionWriter() {
		return new CommentingExpressionWriter(this);
	}

	@Override
	protected LiteralWriter createLiteralWriter() {
		return new CommentingLiteralWriter(this);
	}

}
