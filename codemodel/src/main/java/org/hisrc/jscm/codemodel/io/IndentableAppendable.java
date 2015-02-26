package org.hisrc.jscm.codemodel.io;

import java.io.IOException;

public interface IndentableAppendable extends Appendable {

	public IndentableAppendable indent(String indentation);
	
	public IndentableAppendable unindent(String indentation);

	public IndentableAppendable append(char c) throws IOException;

	public IndentableAppendable append(CharSequence csq) throws IOException;

	public IndentableAppendable append(CharSequence csq, int start, int end)
			throws IOException;

	public IndentableAppendable lineTerminator();

	public IndentableAppendable whiteSpace();

}
