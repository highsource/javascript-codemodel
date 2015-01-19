package org.hisrc.jscm.parser.literal;

import java.util.HashMap;
import java.util.Map;

public class HexConstants {

	static final Map<String, Byte> HEX_DIGIT_MAP = new HashMap<String, Byte>();
	static {
		HexConstants.HEX_DIGIT_MAP.put("0", (byte) 0);
		HexConstants.HEX_DIGIT_MAP.put("1", (byte) 1);
		HexConstants.HEX_DIGIT_MAP.put("2", (byte) 2);
		HexConstants.HEX_DIGIT_MAP.put("3", (byte) 3);
		HexConstants.HEX_DIGIT_MAP.put("4", (byte) 4);
		HexConstants.HEX_DIGIT_MAP.put("5", (byte) 5);
		HexConstants.HEX_DIGIT_MAP.put("6", (byte) 6);
		HexConstants.HEX_DIGIT_MAP.put("7", (byte) 7);
		HexConstants.HEX_DIGIT_MAP.put("8", (byte) 8);
		HexConstants.HEX_DIGIT_MAP.put("9", (byte) 9);
		HexConstants.HEX_DIGIT_MAP.put("A", (byte) 10);
		HexConstants.HEX_DIGIT_MAP.put("B", (byte) 11);
		HexConstants.HEX_DIGIT_MAP.put("C", (byte) 12);
		HexConstants.HEX_DIGIT_MAP.put("D", (byte) 13);
		HexConstants.HEX_DIGIT_MAP.put("E", (byte) 14);
		HexConstants.HEX_DIGIT_MAP.put("F", (byte) 15);
		HexConstants.HEX_DIGIT_MAP.put("a", (byte) 10);
		HexConstants.HEX_DIGIT_MAP.put("b", (byte) 11);
		HexConstants.HEX_DIGIT_MAP.put("c", (byte) 12);
		HexConstants.HEX_DIGIT_MAP.put("d", (byte) 13);
		HexConstants.HEX_DIGIT_MAP.put("e", (byte) 14);
		HexConstants.HEX_DIGIT_MAP.put("f", (byte) 15);
	}

	private HexConstants() {
	}
}
