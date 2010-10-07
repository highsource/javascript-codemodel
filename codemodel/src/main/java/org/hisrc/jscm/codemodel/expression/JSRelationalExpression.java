package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSKeywordBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;

public interface JSRelationalExpression extends JSEqualityExpression {

	public JSRelationalExpression.Relational lt(JSShiftExpression expression);

	public JSRelationalExpression.Relational gt(JSShiftExpression expression);

	public JSRelationalExpression.Relational le(JSShiftExpression expression);

	public JSRelationalExpression.Relational ge(JSShiftExpression expression);

	public JSRelationalExpression.Relational _instanceof(
			JSShiftExpression expression);

	public JSRelationalExpression.Relational in(JSShiftExpression expression);

	public interface Relational extends JSRelationalExpression,
			JSBinaryExpression {
		public JSRelationalExpression getLeft();

		public JSBinaryOperator getOperator();

		public JSShiftExpression getRight();
	}

	public static enum RelationalOperator implements JSBinaryOperator {
		LT("<"), GT(">"), LE("<="), GE(">=");

		private final String operatorAsString;

		RelationalOperator(String operatorAsString) {
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

	public static enum KeywordRelationalOperator implements
			JSKeywordBinaryOperator {
		INSTANCEOF("instanceof"), IN("in");

		private final String operatorAsString;

		KeywordRelationalOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

		public <V, E extends Exception> V acceptOperatorVisitor(
				JSOperatorVisitor<V, E> visitor) throws E {
			return visitor.visitKeywordBinaryOperator(this);
		}

	}

}
