package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

public interface IndentedAppendable extends Appendable {

	public IndentedAppendable append(char c) throws IOException;

	public IndentedAppendable append(CharSequence csq) throws IOException;

	public IndentedAppendable append(CharSequence csq, int start, int end)
			throws IOException;

	public IndentedAppendable indent(CharSequence indentation);

	public IndentedAppendable lineTerminator();

	public IndentedAppendable whiteSpace();

}
