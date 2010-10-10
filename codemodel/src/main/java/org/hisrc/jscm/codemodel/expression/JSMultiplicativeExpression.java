package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.impl.MultiplicativeOperator;


public interface JSMultiplicativeExpression extends JSAdditiveExpression {

	public JSMultiplicativeExpression.Multiplicative mul(
			JSUnaryExpression expression);

	public JSMultiplicativeExpression.Multiplicative div(
			JSUnaryExpression expression);

	public JSMultiplicativeExpression.Multiplicative mod(
			JSUnaryExpression expression);

	public interface Multiplicative extends JSMultiplicativeExpression,
			JSBinaryExpression {
		public JSMultiplicativeExpression getLeft();

		public MultiplicativeOperator getOperator();

		public JSUnaryExpression getRight();
	}
}
