package org.hisrc.jscm.codemodel.writer.debug;

import java.io.IOException;

import org.hisrc.jscm.codemodel.statement.JSDebuggerStatement;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.StatementWriter;

public class CommentingStatementWriter extends StatementWriter {

	public CommentingStatementWriter(CodeWriter formatter) {
		super(formatter);
	}
	
	@Override
	public CodeWriter visitDebugger(JSDebuggerStatement value)
			throws IOException {
		f.inlineComment("S:Debugger");
		return super.visitDebugger(value);
	}
}
