package org.hisrc.jscm.codemodel.writer.debug;

import java.io.IOException;

import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.PropertyNameWriter;

public class CommentingPropertyNameWriter extends PropertyNameWriter {

	public CommentingPropertyNameWriter(CodeWriter formatter) {
		super(formatter);
	}

	@Override
	public CodeWriter visitIdentifierName(JSIdentifierName value)
			throws IOException {
		f.inlineComment("PN:IdentifierName");
		return super.visitIdentifierName(value);
	}

	@Override
	public CodeWriter visitNumericLiteral(JSNumericLiteral value)
			throws IOException {
		f.inlineComment("PN:NumericLiteral");
		return super.visitNumericLiteral(value);
	}

	@Override
	public CodeWriter visitStringLiteral(JSStringLiteral value)
			throws IOException {
		f.inlineComment("PN:StringLiteral");
		return super.visitStringLiteral(value);
	}

}