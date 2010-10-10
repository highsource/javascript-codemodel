package org.hisrc.jscm.codemodel.test;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.junit.Assert;
import org.junit.Test;

public class LiteralsTest {

	private final JSCodeModel codeModel = new CodeModelImpl();

	@Test
	public void decimal() {
		JSDecimalNonIntegerLiteral decimal = codeModel.decimal("1.1");
		Assert.assertEquals(1.1, decimal.asDouble(), 0.00001);
		Assert.assertEquals(1.1, decimal.asDecimal().doubleValue(), 0.00001);
		Assert.assertEquals(1.1, decimal.asNumber().doubleValue(), 0.00001);
		Assert.assertEquals(1, decimal.asNumber().intValue());

	}

	@Test
	public void integer() {
		JSDecimalIntegerLiteral integer = codeModel.integer(1);
		Assert.assertEquals(1, integer.asLong());
		Assert.assertEquals(1, integer.asNumber().longValue());
		Assert.assertEquals(1, integer.asDecimal().longValue());

	}
}
