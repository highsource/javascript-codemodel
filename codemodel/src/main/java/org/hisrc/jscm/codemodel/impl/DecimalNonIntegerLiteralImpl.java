package org.hisrc.jscm.codemodel.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.JSLiteralVisitor;

public class DecimalNonIntegerLiteralImpl extends DecimalLiteralImpl implements
		JSDecimalNonIntegerLiteral {

	private final double valueAsDouble;

	public DecimalNonIntegerLiteralImpl(JSCodeModel codeModel, double value) {
		super(codeModel, new BigDecimal(value));
		Validate.notNull(value);
		this.valueAsDouble = value;
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		return visitor.visit(this);
	}

	@Override
	public double asDouble() {
		return valueAsDouble;
	}

}
