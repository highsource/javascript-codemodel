package org.hisrc.jscm.codemodel;

import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public interface JSPropertyNameVisitor<V, E extends Exception> {

	public V visitNumericLiteral(JSNumericLiteral value) throws E;

	public V visitStringLiteral(JSStringLiteral value) throws E;

	public V visitIdentifierName(JSIdentifierName value) throws E;

}
