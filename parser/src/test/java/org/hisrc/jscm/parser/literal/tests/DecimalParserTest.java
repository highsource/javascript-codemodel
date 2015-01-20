package org.hisrc.jscm.parser.literal.tests;

import java.math.BigDecimal;

import org.hisrc.jscm.parser.ParseException;
import org.hisrc.jscm.parser.literal.DecimalParser;
import org.junit.Assert;
import org.junit.Test;

public class DecimalParserTest {

	@Test
	public void check() throws ParseException {
		Assert.assertEquals(new BigDecimal("1"),
				DecimalParser.INSTANCE.parse("1"));

		Assert.assertEquals(new BigDecimal("1"),
				DecimalParser.INSTANCE.parse("1."));

		Assert.assertEquals(new BigDecimal("1"),
				DecimalParser.INSTANCE.parse("1.0"));

		Assert.assertEquals(new BigDecimal("1.2"),
				DecimalParser.INSTANCE.parse("1.2"));

		Assert.assertEquals(new BigDecimal("1E+2"),
				DecimalParser.INSTANCE.parse("1.E2"));

		Assert.assertEquals(new BigDecimal("1E+2"),
				DecimalParser.INSTANCE.parse("1.E+2"));

		Assert.assertEquals(new BigDecimal("0.01"),
				DecimalParser.INSTANCE.parse("1.E-2"));

		Assert.assertEquals(new BigDecimal("123"),
				DecimalParser.INSTANCE.parse("1.23E2"));

		Assert.assertEquals(new BigDecimal("123"),
				DecimalParser.INSTANCE.parse("1.23E+2"));

		Assert.assertEquals(new BigDecimal("0.0123"),
				DecimalParser.INSTANCE.parse("1.23E-2"));

		Assert.assertEquals(new BigDecimal("1.2"),
				DecimalParser.INSTANCE.parse("1.2"));

		Assert.assertEquals(new BigDecimal("12.3"),
				DecimalParser.INSTANCE.parse(".123E2"));

		Assert.assertEquals(new BigDecimal("12.3"),
				DecimalParser.INSTANCE.parse(".123E+2"));

		Assert.assertEquals(new BigDecimal("0.00123"),
				DecimalParser.INSTANCE.parse(".123E-2"));

		Assert.assertEquals(new BigDecimal(".12"),
				DecimalParser.INSTANCE.parse(".12"));

	}
}
