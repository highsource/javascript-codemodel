package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.JSOperator;

public interface JSBitwiseORExpression extends JSLogicalANDExpression {

	public JSBitwiseORExpression.Bor bor(JSBitwiseXORExpression value);

	public interface Bor extends JSBitwiseORExpression {
		public JSBitwiseORExpression getLeft();

		public JSOperator getOperator();

		public JSBitwiseXORExpression getRight();
	}
}
