package org.hisrc.jscm.parser.tests;

import org.hisrc.jscm.parser.HexIntegerBuilder;
import org.junit.Assert;
import org.junit.Test;

public class HexIntegerBuilderTest {

	@Test
	public void check() {
		Assert.assertEquals(0xA0,
				new HexIntegerBuilder().append("A").append("0")
						.value().longValue());

	}
}
