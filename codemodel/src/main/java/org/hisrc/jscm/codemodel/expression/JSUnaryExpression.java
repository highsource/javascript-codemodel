package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSUnaryOperator;

public interface JSUnaryExpression extends JSMultiplicativeExpression {
	public JSUnaryExpression.Unary delete();

	public JSUnaryExpression.Unary _void();

	public JSUnaryExpression.Unary typeof();

	public JSUnaryExpression.Unary preIncr();

	public JSUnaryExpression.Unary preDecr();

	public JSUnaryExpression.Unary positive();

	public JSUnaryExpression.Unary negative();

	public JSUnaryExpression.Unary complement();

	public JSUnaryExpression.Unary not();

	public interface Unary extends JSUnaryExpression {
		public JSUnaryExpression getBase();

		public JSUnaryOperator getOperator();
	}

}
