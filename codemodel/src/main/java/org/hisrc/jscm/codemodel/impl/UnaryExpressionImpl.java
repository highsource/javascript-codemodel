package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSUnaryExpression;

public abstract class UnaryExpressionImpl extends MultiplicativeExpressionImpl
		implements JSUnaryExpression {

	public UnaryExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public JSUnaryExpression.Unary delete() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.DELETE);
	}

	@Override
	public Unary _void() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.VOID);
	}

	public JSUnaryExpression.Unary typeof() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.TYPEOF);
	}

	public JSUnaryExpression.Unary preIncr() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.PRE_INCR);
	}

	public JSUnaryExpression.Unary preDecr() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.PRE_DECR);
	}

	public JSUnaryExpression.Unary positive() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.POSITIVE);
	}

	public JSUnaryExpression.Unary negative() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.NEGATIVE);
	}

	public JSUnaryExpression.Unary complement() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.COMPLEMENT);
	}

	public JSUnaryExpression.Unary not() {
		return new UnaryImpl(getCodeModel(), this, UnaryOperator.NOT);
	}

	public static class UnaryImpl extends UnaryExpressionImpl implements Unary {

		private final JSUnaryExpression base;
		private final UnaryOperator operator;

		public UnaryImpl(JSCodeModel codeModel, JSUnaryExpression base,
				UnaryOperator operator) {
			super(codeModel);
			this.base = base;
			this.operator = operator;

			Validate.notNull(base);
			Validate.notNull(operator);
		}

		public JSUnaryExpression getBase() {
			return base;
		}

		public UnaryOperator getOperator() {
			return operator;
		}

		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitUnary(this);
		}

	}

}
