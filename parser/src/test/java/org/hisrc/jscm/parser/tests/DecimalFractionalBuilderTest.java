package org.hisrc.jscm.parser.tests;

import java.math.BigDecimal;

import org.hisrc.jscm.parser.DecimalFractionalBuilder;
import org.junit.Assert;
import org.junit.Test;

public class DecimalFractionalBuilderTest {

	@Test
	public void check() {

		Assert.assertEquals(
				new BigDecimal("0.0121"),
				new DecimalFractionalBuilder().append("0").append("1")
						.append("2").append("1").append("0").value());

	}
}
