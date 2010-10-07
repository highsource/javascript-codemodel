package org.hisrc.jscm.codemodel.operator;


public interface JSOperatorVisitor<V, E extends Exception> {

	public V visitAssignmentOperator(JSAssignmentOperator operator) throws E;

	public V visitBinaryOperator(JSBinaryOperator operator) throws E;

	public V visitKeywordBinaryOperator(JSKeywordBinaryOperator operator)
			throws E;

	public V visitUnaryOperator(JSUnaryOperator operator) throws E;

	public V visitPrefixOperator(JSPrefixOperator operator) throws E;

	public V visitKeywordPrefixOperator(JSKeywordPrefixOperator operator)
			throws E;

	public V visitPostfixOperator(JSPostfixOperator operator) throws E;

}
