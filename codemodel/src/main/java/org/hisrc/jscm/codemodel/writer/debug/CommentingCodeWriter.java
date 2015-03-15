package org.hisrc.jscm.codemodel.writer.debug;

import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.ExpressionWriter;
import org.hisrc.jscm.codemodel.writer.LiteralWriter;
import org.hisrc.jscm.codemodel.writer.PropertyAssignmentWriter;
import org.hisrc.jscm.codemodel.writer.PropertyNameWriter;

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

	@Override
	protected PropertyNameWriter createPropertyNameWriter() {
		return new CommentingPropertyNameWriter(this);
	}

	@Override
	protected PropertyAssignmentWriter createPropertyAssignmentWriter() {
		return new CommentingPropertyAssignmentWriter(this);
	}

}