package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSEqualityExpression;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSRelationalExpression;

public abstract class EqualityExpressionImpl extends BitwiseANDExpressionImpl
		implements JSEqualityExpression {

	public EqualityExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public Equality eq(JSRelationalExpression value) {
		return new EqualityImpl(getCodeModel(), this, value,
				EqualityOperator.EQ);
	}

	public Equality ne(JSRelationalExpression value) {
		return new EqualityImpl(getCodeModel(), this, value,
				EqualityOperator.EQ);
	}

	public Equality eeq(JSRelationalExpression value) {
		return new EqualityImpl(getCodeModel(), this, value,
				EqualityOperator.EEQ);
	}

	public Equality nee(JSRelationalExpression value) {
		return new EqualityImpl(getCodeModel(), this, value,
				EqualityOperator.NEE);
	}

	public static class EqualityImpl extends EqualityExpressionImpl implements
			Equality {

		private final JSEqualityExpression left;
		private final JSRelationalExpression right;
		private final EqualityOperator operator;

		public EqualityImpl(JSCodeModel codeModel, JSEqualityExpression left,
				JSRelationalExpression right,
				JSEqualityExpression.EqualityOperator operator) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			Validate.notNull(operator);
			this.left = left;
			this.right = right;
			this.operator = operator;
		}

		public JSEqualityExpression getLeft() {
			return left;
		}

		public JSRelationalExpression getRight() {
			return right;
		}

		public EqualityOperator getOperator() {
			return operator;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitEquality(this);
		}
	}

}
