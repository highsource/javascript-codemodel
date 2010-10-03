package org.hisrc.jscm.codemodel;

public interface JSBitwiseANDExpression extends JSBitwiseORExpression {

	public JSBitwiseANDExpression.Band band(JSEqualityExpression expression);

	public interface Band extends JSBitwiseANDExpression {
		public JSBitwiseANDExpression getLeft();

		public JSEqualityExpression getRight();
	}

}
