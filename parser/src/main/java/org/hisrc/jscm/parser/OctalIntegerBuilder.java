package org.hisrc.jscm.parser;

import java.util.HashMap;
import java.util.Map;

public class OctalIntegerBuilder extends AbstractIntegerBuilder {

	private static final Map<String, Byte> DIGIT_MAP = new HashMap<String, Byte>();
	{
		DIGIT_MAP.put("0", (byte) 0);
		DIGIT_MAP.put("1", (byte) 1);
		DIGIT_MAP.put("2", (byte) 2);
		DIGIT_MAP.put("3", (byte) 3);
		DIGIT_MAP.put("4", (byte) 4);
		DIGIT_MAP.put("5", (byte) 5);
		DIGIT_MAP.put("6", (byte) 6);
		DIGIT_MAP.put("7", (byte) 7);
	}

	public OctalIntegerBuilder() {
		super((byte) 8);
	}

	@Override
	public Map<String, Byte> getDigitMap() {
		return DIGIT_MAP;
	}

	@Override
	public OctalIntegerBuilder append(String digit) {
		super.append(digit);
		return this;
	}

}
