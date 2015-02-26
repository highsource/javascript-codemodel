package org.hisrc.jscm.codemodel.io;

import org.hisrc.jscm.codemodel.lang.Validate;

public class DefaultIndentedAppendable extends AbstractIndentedAppendable {

	private String indentation = "";

	public DefaultIndentedAppendable(Appendable appendable) {
		super(appendable);
	}

	protected String getIndentation() {
		return indentation;
	}

	private boolean lineTerminator;

	protected boolean isLineTerminator() {
		return lineTerminator;
	}

	protected void setLineTerminator(boolean lineTerminator) {
		this.lineTerminator = lineTerminator;
	}

	private boolean whiteSpace;

	protected boolean isWhiteSpace() {
		return whiteSpace;
	}

	protected void setWhiteSpace(boolean value) {
		this.whiteSpace = value;
	}

	@Override
	public IndentableAppendable indent(String offset) {
		Validate.notNull(offset);
		this.indentation = this.indentation + offset;
		return this;
	}

	@Override
	public IndentableAppendable unindent(String offset) {
		Validate.notNull(offset);
		if (!this.indentation.endsWith(offset)) {
			throw new IllegalArgumentException("Illegal indentation [" + offset
					+ "].");
		}
		this.indentation = this.indentation.substring(0,  this.indentation.length() - offset.length());
		return this;
	}
}
