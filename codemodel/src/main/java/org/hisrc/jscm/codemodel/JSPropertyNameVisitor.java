package org.hisrc.jscm.codemodel;

import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public interface JSPropertyNameVisitor<V, E extends Exception> {

	public V visitNumericLiteral(JSNumericLiteral literal) throws E;

	public V visitStringLiteral(JSStringLiteral literal) throws E;

}
