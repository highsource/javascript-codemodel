package org.hisrc.jscm.parser;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Map;

public abstract class AbstractIntegerBuilder implements IntegerBuilder {

	private final BigInteger base;

	public AbstractIntegerBuilder(byte base) {
		this.base = BigInteger.valueOf((long) base);
	}

	public BigInteger getBase() {
		return base;
	}

	public abstract Map<String, Byte> getDigitMap();

	private BigInteger value = null;

	public AbstractIntegerBuilder append(String digit) {
		final Byte digitByteValue = getDigitMap().get(digit);
		if (digitByteValue == null) {
			throw new IllegalArgumentException(MessageFormat.format(
					"The string [{0}] does not represent a digit.", digit));
		}

		final BigInteger digitValue = BigInteger.valueOf(digitByteValue
				.longValue());
		if (value == null) {
			value = digitValue;
		} else {
			value = value.multiply(getBase()).add(digitValue);
		}
		return this;
	}

	public BigInteger value() {
		if (value == null) {
			throw new IllegalStateException("No digits were added yet.");
		} else {
			return value;
		}
	}
}
