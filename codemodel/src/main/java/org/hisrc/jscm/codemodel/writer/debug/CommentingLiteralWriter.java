package org.hisrc.jscm.codemodel.writer.debug;

import java.io.IOException;

import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSHexIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSOctalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSRegularExpressionLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.LiteralWriter;

public class CommentingLiteralWriter extends LiteralWriter {

	public CommentingLiteralWriter(CodeWriter formatter) {
		super(formatter);
	}
	
	@Override
	public CodeWriter visit(JSBooleanLiteral value) throws IOException {
		f.inlineComment("L:BooleanLiteral");
		return super.visit(value);
	}
	
	@Override
	public CodeWriter visit(JSDecimalIntegerLiteral value) throws IOException {
		f.inlineComment("L:DecimalIntegerLiteral");
		return super.visit(value);
	}
	
	@Override
	public CodeWriter visit(JSDecimalNonIntegerLiteral value)
			throws IOException {
		f.inlineComment("L:DecimalNonIntegerLiteral");
		return super.visit(value);
	}
	
	@Override
	public CodeWriter visit(JSHexIntegerLiteral value) throws IOException {
		f.inlineComment("L:HexIntegerLiteral");
		return super.visit(value);
	}
	
	@Override
	public CodeWriter visit(JSNullLiteral value) throws IOException {
		f.inlineComment("L:NullLiteral");
		return super.visit(value);
	}
	
	@Override
	public CodeWriter visit(JSOctalIntegerLiteral value) throws IOException {
		f.inlineComment("L:OctalIntegerLiteral");
		return super.visit(value);
	}
	
	@Override
	public CodeWriter visit(JSStringLiteral value) throws IOException {
		f.inlineComment("L:StringLiteral");
		return super.visit(value);
	}
	
	@Override
	public CodeWriter visit(JSRegularExpressionLiteral value)
			throws IOException {
		f.inlineComment("L:RegularExpressionLiteral");
		return super.visit(value);
	}

}
