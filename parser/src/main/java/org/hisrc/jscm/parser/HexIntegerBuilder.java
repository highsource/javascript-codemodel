package org.hisrc.jscm.parser;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class HexIntegerBuilder {

	private static final BigInteger SIXTEEN = BigInteger.valueOf(16);

	private static final Map<String, Byte> HEX_DIGIT_MAP = new HashMap<String, Byte>();
	{
		HEX_DIGIT_MAP.put("0", (byte) 0);
		HEX_DIGIT_MAP.put("1", (byte) 1);
		HEX_DIGIT_MAP.put("2", (byte) 2);
		HEX_DIGIT_MAP.put("3", (byte) 3);
		HEX_DIGIT_MAP.put("4", (byte) 4);
		HEX_DIGIT_MAP.put("5", (byte) 5);
		HEX_DIGIT_MAP.put("6", (byte) 6);
		HEX_DIGIT_MAP.put("7", (byte) 7);
		HEX_DIGIT_MAP.put("8", (byte) 8);
		HEX_DIGIT_MAP.put("9", (byte) 9);
		HEX_DIGIT_MAP.put("A", (byte) 10);
		HEX_DIGIT_MAP.put("B", (byte) 11);
		HEX_DIGIT_MAP.put("C", (byte) 12);
		HEX_DIGIT_MAP.put("D", (byte) 13);
		HEX_DIGIT_MAP.put("E", (byte) 14);
		HEX_DIGIT_MAP.put("F", (byte) 15);
		HEX_DIGIT_MAP.put("a", (byte) 10);
		HEX_DIGIT_MAP.put("b", (byte) 11);
		HEX_DIGIT_MAP.put("c", (byte) 12);
		HEX_DIGIT_MAP.put("d", (byte) 13);
		HEX_DIGIT_MAP.put("e", (byte) 14);
		HEX_DIGIT_MAP.put("f", (byte) 15);
	}

	private BigInteger value = null;

	public HexIntegerBuilder append(String hexDigit) {
		final Byte hexDigitByteValue = HEX_DIGIT_MAP.get(hexDigit);
		if (hexDigitByteValue == null) {
			throw new IllegalArgumentException(MessageFormat.format(
					"The string [{0}] does not represent a hexadecimal digit.",
					hexDigit));
		}

		final BigInteger hexDigitValue = BigInteger.valueOf(hexDigitByteValue
				.longValue());
		if (value == null) {
			value = hexDigitValue;
		} else {
			value = value.multiply(SIXTEEN).add(hexDigitValue);
		}
		return this;
	}

	public BigInteger bigIntegerValue() {
		if (value == null) {
			throw new IllegalStateException("No digits were added yet.");
		} else {
			return value;
		}
	}

}
