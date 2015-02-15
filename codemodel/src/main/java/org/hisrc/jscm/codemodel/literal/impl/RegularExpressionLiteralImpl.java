package org.hisrc.jscm.codemodel.literal.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;
import org.hisrc.jscm.codemodel.literal.JSRegularExpressionLiteral;

public class RegularExpressionLiteralImpl extends LiteralImpl implements
		JSRegularExpressionLiteral {

	private final String body;

	private final String flags;

	public RegularExpressionLiteralImpl(JSCodeModel codeModel, String body) {
		super(codeModel);
		Validate.notNull(body);
		this.body = body;
		this.flags = null;
	}

	public RegularExpressionLiteralImpl(JSCodeModel codeModel, String body,
			String flags) {
		super(codeModel);
		Validate.notNull(body);
		Validate.notNull(flags);
		this.body = body;
		this.flags = flags;
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public String getFlags() {
		return flags;
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		return visitor.visit(this);
	}
}
