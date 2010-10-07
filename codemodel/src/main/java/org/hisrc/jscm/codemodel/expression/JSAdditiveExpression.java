package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

// 11.6
public interface JSAdditiveExpression extends JSShiftExpression {

	JSAdditiveExpression.Additive plus(JSMultiplicativeExpression expression);

	JSAdditiveExpression.Additive minus(JSMultiplicativeExpression expression);

	public interface Additive extends JSAdditiveExpression, JSBinaryExpression {
		public JSAdditiveExpression getLeft();

		public JSAdditiveExpression.AdditiveOperator getOperator();

		public JSMultiplicativeExpression getRight();
	}

	public static enum AdditiveOperator implements JSBinaryOperator {
		PLUS("+"), MINUS("-");
		private final String operatorAsString;

		AdditiveOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

		public <V, E extends Exception> V acceptOperatorVisitor(
				JSOperatorVisitor<V, E> visitor) throws E {
			return visitor.visitBinaryOperator(this);
		}
	}

}
