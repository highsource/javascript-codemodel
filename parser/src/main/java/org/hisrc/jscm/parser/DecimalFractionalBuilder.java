package org.hisrc.jscm.parser;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class DecimalFractionalBuilder {

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
	}

	public Map<String, Byte> getDigitMap() {
		return DIGIT_MAP;
	}

	public BigDecimal getBase() {
		return base;
	}

	private BigDecimal value = null;
	private BigDecimal base = BigDecimal.TEN;
	private BigDecimal divisor = BigDecimal.TEN;

	public DecimalFractionalBuilder append(String digit) {
		final Byte digitByteValue = getDigitMap().get(digit);
		if (digitByteValue == null) {
			throw new IllegalArgumentException(MessageFormat.format(
					"The string [{0}] does not represent a digit.", digit));
		}

		final BigDecimal digitValue = BigDecimal.valueOf(digitByteValue
				.longValue());

		if (value == null) {
			this.value = BigDecimal.ZERO;
		}

		this.value = this.value.add(digitValue.divide(divisor));
		this.divisor = this.divisor.multiply(getBase());
		return this;
	}

	public BigDecimal value() {
		if (value == null) {
			throw new IllegalStateException("No digits were added yet.");
		} else {
			return value;
		}
	}

}
