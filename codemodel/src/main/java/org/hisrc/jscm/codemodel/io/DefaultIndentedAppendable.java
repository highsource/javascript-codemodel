package org.hisrc.jscm.codemodel.io;

import org.hisrc.jscm.codemodel.lang.Validate;

public class DefaultIndentedAppendable extends AbstractIndentedAppendable {

	private final String indentation;

	public DefaultIndentedAppendable(Appendable appendable) {
		this(appendable, "");

	}

	public DefaultIndentedAppendable(Appendable appendable, String indentation) {
		super(appendable);
		Validate.notNull(indentation);
		this.indentation = indentation;
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
}
