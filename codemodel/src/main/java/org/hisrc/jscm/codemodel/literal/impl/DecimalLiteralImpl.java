package org.hisrc.jscm.codemodel.literal.impl;

import java.math.BigDecimal;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSDecimalLiteral;

public abstract class DecimalLiteralImpl extends NumericLiteralImpl implements
		JSDecimalLiteral {

	private final BigDecimal value;

	public DecimalLiteralImpl(JSCodeModel codeModel, BigDecimal value) {
		super(codeModel, value);
		Validate.notNull(value);
		this.value = value;
	}

	@Override
	public BigDecimal asDecimal() {
		return value;
	}

}
