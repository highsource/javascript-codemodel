package org.hisrc.jscm.codemodel;

public interface JSBitwiseORExpression extends JSLogicalANDExpression {

	public JSBitwiseORExpression.Bor bor(JSBitwiseXORExpression value);
	
	public interface Bor extends JSBitwiseORExpression
	{
		public JSBitwiseORExpression getLeft();
		
		public JSBitwiseXORExpression getRight();
	}
}
