package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.JSOperator;

public interface JSBitwiseANDExpression extends JSBitwiseXORExpression {

	public JSBitwiseANDExpression.Band band(JSEqualityExpression expression);

	public interface Band extends JSBitwiseANDExpression {
		public JSBitwiseANDExpression getLeft();

		public JSOperator getOperator();

		public JSEqualityExpression getRight();
	}

}
