package org.hisrc.jscm.parser.literal.tests;

import java.math.BigDecimal;

import org.hisrc.jscm.parser.literal.DecimalFractionBuilder;
import org.junit.Assert;
import org.junit.Test;

public class DecimalFractionBuilderTest {

	@Test
	public void check() {

		Assert.assertEquals(
				new BigDecimal("0.0121"),
				new DecimalFractionBuilder().append("0").append("1")
						.append("2").append("1").append("0").value());

	}
}
