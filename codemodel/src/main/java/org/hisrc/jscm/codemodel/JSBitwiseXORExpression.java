package org.hisrc.jscm.codemodel;

public interface JSBitwiseXORExpression extends JSBitwiseORExpression {

	public JSBitwiseXORExpression.Xor xor(JSBitwiseANDExpression expression);

	public interface Xor extends JSBitwiseXORExpression {
		public JSBitwiseXORExpression getLeft();

		public JSBitwiseANDExpression getRight();
	}

}
