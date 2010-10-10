package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public interface JSBitwiseXORExpression extends JSBitwiseORExpression {

	public JSBitwiseXORExpression.Xor xor(JSBitwiseANDExpression expression);

	public interface Xor extends JSBitwiseXORExpression, JSBinaryExpression {
		@Override
		public JSBitwiseXORExpression getLeft();

		@Override
		public JSBinaryOperator getOperator();

		@Override
		public JSBitwiseANDExpression getRight();
	}

}
