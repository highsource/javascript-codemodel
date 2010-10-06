package org.hisrc.jscm.codemodel.expression;

public interface JSExpression {

	public JSPrimaryExpression.Brackets brackets();

	public JSExpression.Comma comma(JSAssignmentExpression expression);

	public <V, E extends Exception> V acceptExpressionVisitor(JSExpressionVisitor<V, E> visitor)
			throws E;

	public interface Comma extends JSExpression {

		public JSExpression getLeft();

		public JSAssignmentExpression getRight();

	}

}
