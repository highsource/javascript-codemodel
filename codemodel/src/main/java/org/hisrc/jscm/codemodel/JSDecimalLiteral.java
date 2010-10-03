package org.hisrc.jscm.codemodel;

import java.math.BigDecimal;

public interface JSDecimalLiteral extends JSNumericLiteral {

	public BigDecimal asDecimal();
}
