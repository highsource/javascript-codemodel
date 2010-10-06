package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.JSOperator;

public interface JSBitwiseXORExpression extends JSBitwiseORExpression {

	public JSBitwiseXORExpression.Xor xor(JSBitwiseANDExpression expression);

	public interface Xor extends JSBitwiseXORExpression {
		public JSBitwiseXORExpression getLeft();

		public JSOperator getOperator();

		public JSBitwiseANDExpression getRight();
	}

}
