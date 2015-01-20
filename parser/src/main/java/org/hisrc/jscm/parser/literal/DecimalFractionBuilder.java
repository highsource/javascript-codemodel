package org.hisrc.jscm.parser.literal;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Map;

public class DecimalFractionBuilder {

	public Map<String, Byte> getDigitMap() {
		return DecimalConstants.DECIMAL_DIGIT_MAP;
	}

	public BigDecimal getBase() {
		return base;
	}

	private BigDecimal value = BigDecimal.ZERO;
	private BigDecimal base = DecimalConstants.DECIMAL_TEN;
	private BigDecimal divisor = DecimalConstants.DECIMAL_TEN;

	public DecimalFractionBuilder append(String digit) {
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
		return value;
	}

}
