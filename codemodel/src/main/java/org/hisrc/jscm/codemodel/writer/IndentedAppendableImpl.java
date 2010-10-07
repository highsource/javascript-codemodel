package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;

public class IndentedAppendableImpl implements IndentedAppendable {

	private final String indentation;

	private final Appendable writer;

	public IndentedAppendableImpl(Appendable writer) {
		this(writer, "");
	}

	public IndentedAppendableImpl(Appendable writer, String indentation) {
		Validate.notNull(writer);
		Validate.notNull(indentation);
		this.writer = writer;
		this.indentation = indentation;
	}

	private void pre() throws IOException {
		if (lineTerminator) {
			lineTerminator = false;
			whiteSpace = false;
			writer.append('\n');
			writer.append(indentation);
		} else if (whiteSpace) {
			lineTerminator = false;
			whiteSpace = false;
			writer.append(' ');
		}
	}

	public IndentedAppendable append(CharSequence str) throws IOException {
		pre();
		writer.append(str);
		return this;
	}

	public IndentedAppendable append(CharSequence charSequence, int start,
			int end) throws IOException {
		pre();
		writer.append(charSequence, start, end);
		return this;
	}

	public synchronized IndentedAppendable append(char c) throws IOException {
		pre();
		writer.append(c);
		return this;
	}

	public IndentedAppendable indent(CharSequence indentation) {
		if (indentation == null) {
			indentation = "null";
		}
		return new IndentedAppendableImpl(writer, this.indentation
				+ indentation);
	}

	private boolean whiteSpace = false;

	public IndentedAppendable whiteSpace() {
		whiteSpace = true;
		return this;
	}

	public boolean isWhiteSpace() {
		return whiteSpace;
	}

	private boolean lineTerminator = false;

	public boolean isLineTerminator() {
		return lineTerminator;
	}

	public IndentedAppendable lineTerminator() {
		lineTerminator = true;
		return this;
	}
}
