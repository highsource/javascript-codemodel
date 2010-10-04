package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSOperator;

// 11.6
public interface JSAdditiveExpression extends JSShiftExpression {

	JSAdditiveExpression.Additive plus(JSMultiplicativeExpression expression);

	JSAdditiveExpression.Additive minus(JSMultiplicativeExpression expression);

	public interface Additive extends JSAdditiveExpression {
		public JSAdditiveExpression getLeft();

		public JSAdditiveExpression.AdditiveOperator getOperator();

		public JSMultiplicativeExpression getRight();
	}

	public static enum AdditiveOperator implements JSOperator {
		PLUS("+"), MINUS("-");
		private final String operatorAsString;

		AdditiveOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}
	}

}
