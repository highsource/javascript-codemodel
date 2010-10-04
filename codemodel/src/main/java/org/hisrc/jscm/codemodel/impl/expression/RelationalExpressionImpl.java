package org.hisrc.jscm.codemodel.impl.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSRelationalExpression;
import org.hisrc.jscm.codemodel.expression.JSShiftExpression;

public abstract class RelationalExpressionImpl extends EqualityExpressionImpl
		implements JSRelationalExpression {

	public RelationalExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public Relational lt(JSShiftExpression value) {
		return new RelationalImpl(getCodeModel(), this, value,
				RelationalOperator.LT);
	}

	public Relational gt(JSShiftExpression value) {
		return new RelationalImpl(getCodeModel(), this, value,
				RelationalOperator.GT);
	}

	public Relational le(JSShiftExpression value) {
		return new RelationalImpl(getCodeModel(), this, value,
				RelationalOperator.LE);
	}

	public Relational ge(JSShiftExpression value) {
		return new RelationalImpl(getCodeModel(), this, value,
				RelationalOperator.GE);
	}

	public Relational _instanceof(JSShiftExpression value) {
		return new RelationalImpl(getCodeModel(), this, value,
				RelationalOperator.INSTANCEOF);
	}

	public Relational in(JSShiftExpression value) {
		return new RelationalImpl(getCodeModel(), this, value,
				RelationalOperator.IN);
	}

	public static class RelationalImpl extends RelationalExpressionImpl
			implements Relational {

		private final JSRelationalExpression left;
		private final JSShiftExpression right;
		private final RelationalOperator operator;

		public RelationalImpl(JSCodeModel codeModel,
				JSRelationalExpression left, JSShiftExpression right,
				RelationalOperator operator) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			Validate.notNull(operator);
			this.left = left;
			this.right = right;
			this.operator = operator;
		}

		public JSRelationalExpression getLeft() {
			return left;
		}

		public JSShiftExpression getRight() {
			return right;
		}

		public RelationalOperator getOperator() {
			return operator;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitRelational(this);
		}
	}
}
