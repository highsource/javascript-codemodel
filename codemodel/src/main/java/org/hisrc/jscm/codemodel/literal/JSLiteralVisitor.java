package org.hisrc.jscm.codemodel.literal;


public interface JSLiteralVisitor<V, E extends Exception> {

	public V visit(JSStringLiteral value) throws E;

	public V visit(JSNullLiteral value) throws E;

	public V visit(JSBooleanLiteral value) throws E;

	public V visit(JSDecimalIntegerLiteral value) throws E;

	public V visit(JSDecimalNonIntegerLiteral value) throws E;
	
	public V visit(JSHexIntegerLiteral value) throws E;

	public V visit(JSOctalIntegerLiteral value) throws E;

	public V visit(JSRegularExpressionLiteral value) throws E;
}
