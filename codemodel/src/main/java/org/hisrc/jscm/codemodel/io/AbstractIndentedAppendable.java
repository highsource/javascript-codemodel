package org.hisrc.jscm.codemodel.io;

import java.io.IOException;

import org.hisrc.jscm.codemodel.lang.Validate;

public abstract class AbstractIndentedAppendable implements IndentedAppendable {

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
	public IndentedAppendable append(CharSequence str) throws IOException {
		pre();
		appendable.append(str);
		return this;
	}

	@Override
	public IndentedAppendable append(CharSequence charSequence, int start,
			int end) throws IOException {
		pre();
		appendable.append(charSequence, start, end);
		return this;
	}

	@Override
	public synchronized IndentedAppendable append(char c) throws IOException {
		pre();
		appendable.append(c);
		return this;
	}

	@Override
	public IndentedAppendable indent(final CharSequence indentation) {
		Validate.notNull(indentation);
		final CharSequence _indentation = indentation;
		return new AbstractIndentedAppendable(this.appendable) {

			private final String indentation = AbstractIndentedAppendable.this
					.getIndentation() + _indentation;

			@Override
			protected void setWhiteSpace(boolean value) {
				AbstractIndentedAppendable.this.setWhiteSpace(value);
			}

			@Override
			protected void setLineTerminator(boolean value) {
				AbstractIndentedAppendable.this.setLineTerminator(value);
			}

			@Override
			protected boolean isWhiteSpace() {
				return AbstractIndentedAppendable.this.isWhiteSpace();
			}

			@Override
			protected boolean isLineTerminator() {
				return AbstractIndentedAppendable.this.isLineTerminator();
			}

			@Override
			protected String getIndentation() {
				return indentation;
			}
		};
	}

	@Override
	public IndentedAppendable whiteSpace() {
		setWhiteSpace(true);
		return this;
	}

	@Override
	public IndentedAppendable lineTerminator() {
		setLineTerminator(true);
		return this;
	}
}
