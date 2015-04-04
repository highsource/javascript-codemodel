package org.hisrc.jscm.codemodel.writer.debug;

import java.io.IOException;

import org.hisrc.jscm.codemodel.statement.JSDebuggerStatement;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.StatementWriter;

public class CommentingStatementWriter extends StatementWriter {

	public CommentingStatementWriter(CodeWriter formatter) {
		super(formatter);
	}
	
	@Override
	public CodeWriter visitTry(JSTryStatement value) throws IOException {
		f.inlineComment("S:Try");
		f.keyword("try").whiteSpace();
		f.statement(value.getBody());
		if (value.getCatch() != null) {
			f.lineTerminator();
			f.inlineComment("S:Try.Catch");
			f.keyword("catch").whiteSpace();
			f.openRoundBracket();
			f.expression(value.getException());
			f.closeRoundBracket().whiteSpace();
			f.statement(value.getCatch());
		}
		if (value.getFinally() != null) {
			f.lineTerminator();
			f.inlineComment("S:Try.Finally");
			f.keyword("finally").whiteSpace();
			f.statement(value.getFinally());
		}
		return f;
	}
	
	@Override
	public CodeWriter visitDebugger(JSDebuggerStatement value)
			throws IOException {
		f.inlineComment("S:Debugger");
		return super.visitDebugger(value);
	}
}
