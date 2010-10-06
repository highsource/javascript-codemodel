package org.hisrc.jscm.codemodel.impl.literal;

import java.math.BigDecimal;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSLiteralVisitor;

public class DecimalIntegerLiteralImpl extends DecimalLiteralImpl implements
		JSDecimalIntegerLiteral {

	private final long valueAsLong;

	public DecimalIntegerLiteralImpl(JSCodeModel codeModel, long value) {
		super(codeModel, new BigDecimal(value));
		Validate.notNull(value);
		this.valueAsLong = value;
	}

	@Override
	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E {
		return visitor.visit(this);
	}

	@Override
	public long asLong() {
		return valueAsLong;
	}

}
