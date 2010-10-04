package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSOperator;

public interface JSEqualityExpression extends JSBitwiseANDExpression {

	public JSEqualityExpression.Equality eq(JSRelationalExpression value);

	public JSEqualityExpression.Equality ne(JSRelationalExpression value);

	public JSEqualityExpression.Equality eeq(JSRelationalExpression value);

	public JSEqualityExpression.Equality nee(JSRelationalExpression value);

	public interface Equality extends JSEqualityExpression {
		public JSEqualityExpression getLeft();

		public JSEqualityExpression.EqualityOperator getOperator();

		public JSRelationalExpression getRight();
	}

	public static enum EqualityOperator implements JSOperator {
		EQ("=="), NE("!="), EEQ("==="), NEE("!==");

		private final String operatorAsString;

		EqualityOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}
	}

}
