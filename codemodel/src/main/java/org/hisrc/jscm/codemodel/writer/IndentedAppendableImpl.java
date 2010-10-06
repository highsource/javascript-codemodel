package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;

public class IndentedAppendableImpl implements IndentedAppendable {

	private boolean newLine = true;

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

	@Override
	public Appendable append(CharSequence charSequence) throws IOException {
		if (charSequence == null) {
			charSequence = "null";
		}
		for (int index = 0; index < charSequence.length(); index++) {
			append(charSequence.charAt(index));
		}
		return this;
	}

	@Override
	public Appendable append(CharSequence charSequence, int start, int end)
			throws IOException {
		if (charSequence == null) {
			charSequence = "null";
		}
		for (int index = start; index < end; index++) {
			append(charSequence.charAt(index));
		}
		return this;
	}

	@Override
	public synchronized Appendable append(char c) throws IOException {
		if (newLine) {
			writer.append(indentation);
			newLine = false;
		}
		writer.append(c);
		if (c == '\n') {
			newLine = true;
		}
		return this;
	}

	public IndentedAppendable indent(CharSequence indentation) {
		if (indentation == null) {
			indentation = "null";
		}
		return new IndentedAppendableImpl(writer, this.indentation
				+ indentation);
	}

}
