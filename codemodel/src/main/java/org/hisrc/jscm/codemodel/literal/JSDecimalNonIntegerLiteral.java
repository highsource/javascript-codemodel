package org.hisrc.jscm.codemodel.literal;

import java.math.BigDecimal;

public interface JSDecimalNonIntegerLiteral extends JSDecimalLiteral {

	
	@Override
	public BigDecimal asNumber();
	
	public double asDouble();

}
