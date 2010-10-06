package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.JSOperator;

public interface JSLogicalANDExpression extends JSLogicalORExpression {

	public JSLogicalANDExpression.And and(JSBitwiseORExpression value);

	public interface And extends JSLogicalANDExpression {
		public JSLogicalANDExpression getLeft();
		
		public JSOperator getOperator();

		public JSBitwiseORExpression getRight();
	}

}
