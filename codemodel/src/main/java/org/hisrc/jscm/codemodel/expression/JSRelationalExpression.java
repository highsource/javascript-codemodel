package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;

public interface JSRelationalExpression extends JSEqualityExpression {

	public JSRelationalExpression.Relational lt(JSShiftExpression expression);

	public JSRelationalExpression.Relational gt(JSShiftExpression expression);

	public JSRelationalExpression.Relational le(JSShiftExpression expression);

	public JSRelationalExpression.Relational ge(JSShiftExpression expression);

	public JSRelationalExpression.Relational _instanceof(
			JSShiftExpression expression);

	public JSRelationalExpression.Relational in(JSShiftExpression expression);

	public interface Relational extends JSRelationalExpression {
		public JSRelationalExpression getLeft();

		public JSRelationalExpression.RelationalOperator getOperator();

		public JSShiftExpression getRight();
	}

	public static enum RelationalOperator {
		LT("<"), GT(">"), LE("<="), GE(">="), INSTANCEOF("instanceof"), IN("in");

		private final String operatorAsString;

		RelationalOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}
	}

}
