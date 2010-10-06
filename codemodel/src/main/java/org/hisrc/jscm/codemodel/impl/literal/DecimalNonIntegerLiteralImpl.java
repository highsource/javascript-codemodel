package org.hisrc.jscm.codemodel.impl.literal;

import java.math.BigDecimal;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;

public class DecimalNonIntegerLiteralImpl extends DecimalLiteralImpl implements
		JSDecimalNonIntegerLiteral {

	private final double valueAsDouble;

	public DecimalNonIntegerLiteralImpl(JSCodeModel codeModel, double value) {
		super(codeModel, new BigDecimal(value));
		Validate.notNull(value);
		this.valueAsDouble = value;
	}

	public DecimalNonIntegerLiteralImpl(JSCodeModel codeModel, BigDecimal value) {
		super(codeModel, value);
		Validate.notNull(value);
		this.valueAsDouble = value.doubleValue();
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
