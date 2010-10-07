package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public interface JSBitwiseORExpression extends JSLogicalANDExpression {

	public JSBitwiseORExpression.Bor bor(JSBitwiseXORExpression value);

	public interface Bor extends JSBitwiseORExpression, JSBinaryExpression {
		public JSBitwiseORExpression getLeft();

		public JSBinaryOperator getOperator();

		public JSBitwiseXORExpression getRight();
	}
}
