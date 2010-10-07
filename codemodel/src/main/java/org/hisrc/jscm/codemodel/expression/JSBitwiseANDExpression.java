package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public interface JSBitwiseANDExpression extends JSBitwiseXORExpression {

	public JSBitwiseANDExpression.Band band(JSEqualityExpression expression);

	public interface Band extends JSBitwiseANDExpression, JSBinaryExpression {
		public JSBitwiseANDExpression getLeft();

		public JSBinaryOperator getOperator();

		public JSEqualityExpression getRight();
	}

}
