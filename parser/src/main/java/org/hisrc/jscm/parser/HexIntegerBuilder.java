package org.hisrc.jscm.parser;

import java.util.HashMap;
import java.util.Map;

public class HexIntegerBuilder extends AbstractIntegerBuilder {

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
		DIGIT_MAP.put("8", (byte) 8);
		DIGIT_MAP.put("9", (byte) 9);
		DIGIT_MAP.put("A", (byte) 10);
		DIGIT_MAP.put("B", (byte) 11);
		DIGIT_MAP.put("C", (byte) 12);
		DIGIT_MAP.put("D", (byte) 13);
		DIGIT_MAP.put("E", (byte) 14);
		DIGIT_MAP.put("F", (byte) 15);
		DIGIT_MAP.put("a", (byte) 10);
		DIGIT_MAP.put("b", (byte) 11);
		DIGIT_MAP.put("c", (byte) 12);
		DIGIT_MAP.put("d", (byte) 13);
		DIGIT_MAP.put("e", (byte) 14);
		DIGIT_MAP.put("f", (byte) 15);
	}

	public HexIntegerBuilder() {
		super((byte) 16);
	}

	@Override
	public Map<String, Byte> getDigitMap() {
		return DIGIT_MAP;
	}

	@Override
	public HexIntegerBuilder append(String digit) {
		super.append(digit);
		return this;
	}

}
