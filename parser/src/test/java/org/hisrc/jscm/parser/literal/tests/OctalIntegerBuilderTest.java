package org.hisrc.jscm.parser.literal.tests;

import org.hisrc.jscm.parser.literal.OctalIntegerBuilder;
import org.junit.Assert;
import org.junit.Test;

public class OctalIntegerBuilderTest {

	@Test
	public void check() {
		Assert.assertEquals(8*7 + 5,
				new OctalIntegerBuilder().append("7").append("5")
						.value().longValue());

	}
}
