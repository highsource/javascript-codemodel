package org.hisrc.jscm.parser.literal;

import java.util.Map;

public class HexIntegerBuilder extends AbstractIntegerBuilder {

	public HexIntegerBuilder() {
		super((byte) 16);
	}

	@Override
	public Map<String, Byte> getDigitMap() {
		return HexConstants.HEX_DIGIT_MAP;
	}

	@Override
	public HexIntegerBuilder append(String digit) {
		super.append(digit);
		return this;
	}

}
