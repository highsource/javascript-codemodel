package org.hisrc.jscm.parser.literal;

import java.util.Map;

public class DecimalIntegerBuilder extends AbstractIntegerBuilder {


	public DecimalIntegerBuilder() {
		super((byte) 10);
	}

	@Override
	public Map<String, Byte> getDigitMap() {
		return DecimalConstants.DECIMAL_DIGIT_MAP;
	}

	@Override
	public DecimalIntegerBuilder append(String digit) {
		super.append(digit);
		return this;
	}

}
