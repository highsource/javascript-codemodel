package org.hisrc.jscm.parser.tests;

import org.hisrc.jscm.parser.OctalIntegerParser;
import org.hisrc.jscm.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class OctalIntegerParserTest {

	@Test
	public void check() throws ParseException {
		Assert.assertEquals(075, OctalIntegerParser.INSTANCE.parse("075")
				.longValue());
		Assert.assertEquals(01234567,
				OctalIntegerParser.INSTANCE.parse("01234567").longValue());
	}
}
