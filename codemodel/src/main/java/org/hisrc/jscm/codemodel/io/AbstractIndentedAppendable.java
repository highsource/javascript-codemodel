package org.hisrc.jscm.codemodel.io;

import java.io.IOException;

import org.hisrc.jscm.codemodel.lang.Validate;

public abstract class AbstractIndentedAppendable implements IndentableAppendable {

	private final Appendable appendable;

	public AbstractIndentedAppendable(Appendable appendable) {
		Validate.notNull(appendable);
		this.appendable = appendable;
	}

	protected abstract String getIndentation();

	protected abstract boolean isLineTerminator();

	protected abstract void setLineTerminator(boolean value);

	protected abstract boolean isWhiteSpace();

	protected abstract void setWhiteSpace(boolean value);

	private void pre() throws IOException {
		if (isLineTerminator()) {
			setLineTerminator(false);
			setWhiteSpace(false);
			appendable.append('\n');
			appendable.append(getIndentation());
		} else if (isWhiteSpace()) {
			setLineTerminator(false);
			setWhiteSpace(false);
			appendable.append(' ');
		}
	}

	@Override
	public IndentableAppendable append(CharSequence str) throws IOException {
		pre();
		appendable.append(str);
		return this;
	}

	@Override
	public IndentableAppendable append(CharSequence charSequence, int start,
			int end) throws IOException {
		pre();
		appendable.append(charSequence, start, end);
		return this;
	}

	@Override
	public synchronized IndentableAppendable append(char c) throws IOException {
		pre();
		appendable.append(c);
		return this;
	}

	@Override
	public IndentableAppendable whiteSpace() {
		setWhiteSpace(true);
		return this;
	}

	@Override
	public IndentableAppendable lineTerminator() {
		setLineTerminator(true);
		return this;
	}
}
