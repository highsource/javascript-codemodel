package org.hisrc.jscm.codemodel.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSKeywordPrefixOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;
import org.hisrc.jscm.codemodel.operator.JSPrefixOperator;
import org.hisrc.jscm.codemodel.operator.JSUnaryOperator;

public interface JSUnaryExpression extends JSMultiplicativeExpression {
	public JSUnaryExpression.Unary delete();

	public JSUnaryExpression.Unary _void();

	public JSUnaryExpression.Unary typeof();

	public JSUnaryExpression.Unary preIncr();

	public JSUnaryExpression.Unary preDecr();

	public JSUnaryExpression.Unary positive();

	public JSUnaryExpression.Unary negative();

	public JSUnaryExpression.Unary complement();

	public JSUnaryExpression.Unary not();

	public interface Unary extends JSUnaryExpression {
		public JSUnaryExpression getBase();

		public JSUnaryOperator getOperator();
	}

	public static enum KeywordPrefixOperator implements JSKeywordPrefixOperator {
		DELETE("delete"), VOID("void"), TYPEOF("typeof");

		private final String operatorAsString;

		KeywordPrefixOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

		public <V, E extends Exception> V acceptOperatorVisitor(
				JSOperatorVisitor<V, E> visitor) throws E {
			return visitor.visitKeywordPrefixOperator(this);
		}
	}

	public static enum PrefixOperator implements JSPrefixOperator {
		PRE_INCR("++"), PRE_DECR("--");

		private final String operatorAsString;

		PrefixOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

		public <V, E extends Exception> V acceptOperatorVisitor(
				JSOperatorVisitor<V, E> visitor) throws E {
			return visitor.visitPrefixOperator(this);
		}
	}

	public static enum UnaryOperator implements JSUnaryOperator {
		POSITIVE("+"), NEGATIVE("-"), COMPLEMENT("~"), NOT("!");

		private final String operatorAsString;

		UnaryOperator(String operatorAsString) {
			Validate.notNull(operatorAsString);
			this.operatorAsString = operatorAsString;
		}

		public String asString() {
			return operatorAsString;
		}

		public <V, E extends Exception> V acceptOperatorVisitor(
				JSOperatorVisitor<V, E> visitor) throws E {
			return visitor.visitUnaryOperator(this);
		}
	}

}
