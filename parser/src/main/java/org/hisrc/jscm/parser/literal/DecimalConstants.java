package org.hisrc.jscm.parser.literal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class DecimalConstants {

	// TODO make unmodifiable
	public static final Map<String, Byte> DECIMAL_DIGIT_MAP;
	static {
		DECIMAL_DIGIT_MAP = new HashMap<String, Byte>();
		DECIMAL_DIGIT_MAP.put("0", (byte) 0);
		DECIMAL_DIGIT_MAP.put("1", (byte) 1);
		DECIMAL_DIGIT_MAP.put("2", (byte) 2);
		DECIMAL_DIGIT_MAP.put("3", (byte) 3);
		DECIMAL_DIGIT_MAP.put("4", (byte) 4);
		DECIMAL_DIGIT_MAP.put("5", (byte) 5);
		DECIMAL_DIGIT_MAP.put("6", (byte) 6);
		DECIMAL_DIGIT_MAP.put("7", (byte) 7);
		DECIMAL_DIGIT_MAP.put("8", (byte) 8);
		DECIMAL_DIGIT_MAP.put("9", (byte) 9);
	}

	public static final BigInteger INTEGER_TEN = BigInteger.TEN;

	public static final BigDecimal DECIMAL_TEN = BigDecimal.TEN;

	private DecimalConstants() {
	}

}
