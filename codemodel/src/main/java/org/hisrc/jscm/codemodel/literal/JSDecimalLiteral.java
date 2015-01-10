package org.hisrc.jscm.codemodel.literal;

import java.math.BigDecimal;

public interface JSDecimalLiteral extends JSNumericLiteral {

	public BigDecimal asDecimal();
}
