package org.hisrc.jscm.codemodel.expression;

public interface JSLogicalANDExpression extends JSLogicalORExpression {

	public JSLogicalANDExpression.And and(JSBitwiseORExpression value);

	public interface And extends JSLogicalANDExpression {
		public JSLogicalANDExpression getLeft();

		public JSBitwiseORExpression getRight();
	}

}
