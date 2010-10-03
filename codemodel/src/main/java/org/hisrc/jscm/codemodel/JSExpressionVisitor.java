package org.hisrc.jscm.codemodel;

public interface JSExpressionVisitor<V, E extends Exception> {

	// 11.1
	public V visitThis(JSThis value) throws E;

	// TODO Identifier

	public V visitLiteral(JSLiteral value) throws E;

	public V visitArrayLiteral(JSArrayLiteral value) throws E;

	public V visitObjectLiteral(JSObjectLiteral value) throws E;

	public V visitBrackets(JSPrimaryExpression.Brackets value) throws E;

	public V visitMemberElement(JSMemberExpression.MemberElement value)
			throws E;

	public V visitMemberProperty(JSMemberExpression.MemberProperty value)
			throws E;

	public V visitMemberNew(JSMemberExpression.MemberNew value) throws E;

	public V visitMemberCall(JSCallExpression.MemberCall value) throws E;

	public V visitNew(JSNewExpression.New value) throws E;

	public V visitCallArgs(JSCallExpression.CallArgs value) throws E;

	public V visitCallElement(JSCallExpression.CallElement value) throws E;

	public V visitCallProperty(JSCallExpression.CallProperty value) throws E;

	public V visitPostfix(JSPostfixExpression.Postfix value) throws E;

	public V visitUnary(JSUnaryExpression.Unary value) throws E;

	public V visitMultiplicative(JSMultiplicativeExpression.Multiplicative value)
			throws E;

	public V visitAdditive(JSAdditiveExpression.Additive value) throws E;

	public V visitShift(JSShiftExpression.Shift value) throws E;

	public V visitRelational(JSRelationalExpression.Relational value) throws E;

	public V visitEquality(JSEqualityExpression.Equality value) throws E;

	public V visitBand(JSBitwiseANDExpression.Band value) throws E;

	public V visitXor(JSBitwiseXORExpression.Xor value) throws E;

	public V visitBor(JSBitwiseORExpression.Bor value) throws E;

	public V visitAnd(JSLogicalANDExpression.And value) throws E;

	public V visitOr(JSLogicalORExpression.Or value) throws E;

	public V visitConditional(JSConditionalExpression.Conditional value)
			throws E;

	public V visitAssignment(JSAssignmentExpression.Assignment value) throws E;

	public V visitComma(JSExpression.Comma value) throws E;

}
