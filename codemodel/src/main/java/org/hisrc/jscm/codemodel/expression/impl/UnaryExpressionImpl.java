package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSUnaryExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSUnaryOperator;
import org.hisrc.jscm.codemodel.operator.impl.KeywordPrefixOperator;
import org.hisrc.jscm.codemodel.operator.impl.PrefixOperator;
import org.hisrc.jscm.codemodel.operator.impl.UnaryOperator;

public abstract class UnaryExpressionImpl extends MultiplicativeExpressionImpl
		implements JSUnaryExpression {

	public UnaryExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public JSUnaryExpression.Unary delete() {
		return new UnaryImpl(getCodeModel(), this, KeywordPrefixOperator.DELETE);
	}

	@Override
	public Unary _void() {
		return new UnaryImpl(getCodeModel(), this, KeywordPrefixOperator.VOID);
	}

	@Override
	public JSUnaryExpression.Unary typeof() {
		return new UnaryImpl(getCodeModel(), this, KeywordPrefixOperator.TYPEOF);
	}

	@Override
	public JSUnaryExpression.Unary preIncr() {
		return new UnaryImpl(getCodeModel(), this, PrefixOperator.PRE_INCR);
	}

	@Override
	public JSUnaryExpression.Unary preDecr() {
		return new UnaryImpl(getCodeModel(), this, PrefixOperator.PRE_DECR);
	}

	@Override
	public JSUnaryExpression.Unary positive() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.POSITIVE);
	}

	@Override
	public JSUnaryExpression.Unary negative() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.NEGATIVE);
	}

	@Override
	public JSUnaryExpression.Unary complement() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.COMPLEMENT);
	}

	@Override
	public JSUnaryExpression.Unary not() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.NOT);
	}

	public static class UnaryImpl extends UnaryExpressionImpl implements Unary {

		private final JSUnaryExpression base;
		private final JSUnaryOperator operator;

		public UnaryImpl(JSCodeModel codeModel, JSUnaryExpression base,
				JSUnaryOperator operator) {
			super(codeModel);
			this.base = base;
			this.operator = operator;

			Validate.notNull(base);
			Validate.notNull(operator);
		}

		@Override
		public JSUnaryExpression getBase() {
			return base;
		}

		@Override
		public JSUnaryOperator getOperator() {
			return operator;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitUnary(this);
		}

	}

}
