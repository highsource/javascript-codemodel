package org.hisrc.jscm.codemodel;

public interface JSPropertyNameVisitor<V, E extends Exception> {

	public V visitNumericLiteral(JSNumericLiteral literal) throws E;

	public V visitStringLiteral(JSStringLiteral literal) throws E;

}
