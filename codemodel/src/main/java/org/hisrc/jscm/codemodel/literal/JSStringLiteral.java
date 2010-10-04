package org.hisrc.jscm.codemodel.literal;

import org.hisrc.jscm.codemodel.JSPropertyName;

public interface JSStringLiteral extends JSLiteral, JSPropertyName {

	public String asString();
}
