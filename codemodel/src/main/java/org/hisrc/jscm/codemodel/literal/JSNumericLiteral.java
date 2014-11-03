package org.hisrc.jscm.codemodel.literal;

import org.hisrc.jscm.codemodel.JSPropertyName;

public interface JSNumericLiteral extends JSPropertyName, JSLiteral {

	public Number asNumber();
}
