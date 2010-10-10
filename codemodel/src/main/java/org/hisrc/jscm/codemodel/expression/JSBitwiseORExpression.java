package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public interface JSBitwiseORExpression extends JSLogicalANDExpression {

	public JSBitwiseORExpression.Bor bor(JSBitwiseXORExpression value);

	public interface Bor extends JSBitwiseORExpression, JSBinaryExpression {
		@Override
		public JSBitwiseORExpression getLeft();

		@Override
		public JSBinaryOperator getOperator();

		@Override
		public JSBitwiseXORExpression getRight();
	}
}
