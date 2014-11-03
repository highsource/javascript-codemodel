package org.hisrc.jscm.codemodel.literal.impl;

import java.math.BigDecimal;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;

public class DecimalNonIntegerLiteralImpl extends DecimalLiteralImpl implements
		JSDecimalNonIntegerLiteral {

	private final BigDecimal value;

	public DecimalNonIntegerLiteralImpl(JSCodeModel codeModel, BigDecimal value) {
		super(codeModel, value);
		Validate.notNull(value);
		this.value = value;
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		return visitor.visit(this);
	}

	@Override
	public double asDouble() {
		return value.doubleValue();
	}

	@Override
	public BigDecimal asNumber() {
		return value;
	}
}
