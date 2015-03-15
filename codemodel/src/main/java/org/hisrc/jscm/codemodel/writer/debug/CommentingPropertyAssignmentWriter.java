package org.hisrc.jscm.codemodel.writer.debug;

import java.io.IOException;

import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSGetter;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSProperty;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSSetter;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.PropertyAssignmentWriter;

public class CommentingPropertyAssignmentWriter extends
		PropertyAssignmentWriter {

	public CommentingPropertyAssignmentWriter(CodeWriter formatter) {
		super(formatter);
	}

	@Override
	public CodeWriter visitProperty(JSProperty property) throws IOException {
		f.inlineComment("PA:Property");
		return super.visitProperty(property);
	}

	@Override
	public CodeWriter visitGetter(JSGetter getter) throws IOException {
		f.inlineComment("PA:Getter");
		return super.visitGetter(getter);
	}

	@Override
	public CodeWriter visitSetter(JSSetter setter) throws IOException {
		f.inlineComment("PA:Setter");
		return super.visitSetter(setter);
	}
}
