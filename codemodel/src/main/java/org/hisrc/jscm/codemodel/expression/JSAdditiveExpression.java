package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.impl.AdditiveOperator;

// 11.6
public interface JSAdditiveExpression extends JSShiftExpression {

	JSAdditiveExpression.Additive plus(JSMultiplicativeExpression expression);

	JSAdditiveExpression.Additive minus(JSMultiplicativeExpression expression);

	public interface Additive extends JSAdditiveExpression, JSBinaryExpression {
		@Override
		public JSAdditiveExpression getLeft();

		@Override
		public AdditiveOperator getOperator();

		@Override
		public JSMultiplicativeExpression getRight();
	}

}
