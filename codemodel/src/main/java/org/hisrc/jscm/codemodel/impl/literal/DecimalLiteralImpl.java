package org.hisrc.jscm.codemodel.impl.literal;

import java.math.BigDecimal;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.literal.JSDecimalLiteral;

public abstract class DecimalLiteralImpl extends NumericLiteralImpl implements
		JSDecimalLiteral {

	private final BigDecimal valueAsDecimal;

	public DecimalLiteralImpl(JSCodeModel codeModel, BigDecimal value) {
		super(codeModel, value);
		Validate.notNull(value);
		this.valueAsDecimal = value;
	}

	@Override
	public BigDecimal asDecimal() {
		return valueAsDecimal;
	}

}
