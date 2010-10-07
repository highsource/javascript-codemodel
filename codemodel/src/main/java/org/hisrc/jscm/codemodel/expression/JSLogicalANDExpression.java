package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public interface JSLogicalANDExpression extends JSLogicalORExpression {

	public JSLogicalANDExpression.And and(JSBitwiseORExpression value);

	public interface And extends JSLogicalANDExpression, JSBinaryExpression {
		public JSLogicalANDExpression getLeft();

		public JSBinaryOperator getOperator();

		public JSBitwiseORExpression getRight();
	}

}
