package org.hisrc.jscm.codemodel.expression.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression.Brackets;

public abstract class ExpressionImpl implements JSExpression {

	private final JSCodeModel codeModel;

	public ExpressionImpl(JSCodeModel codeModel) {
		Validate.notNull(codeModel);
		this.codeModel = codeModel;
	}

	public JSCodeModel getCodeModel() {
		return codeModel;
	}

	public Brackets brackets() {
		return new PrimaryExpressionImpl.BracketsImpl(getCodeModel(), this);
	}

	public Comma comma(JSAssignmentExpression expression) {
		return new CommaImpl(getCodeModel(), this, expression);
	}

	public static class CommaImpl extends ExpressionImpl implements Comma {

		private final JSExpression first;
		private final JSAssignmentExpression second;

		public CommaImpl(JSCodeModel codeModel, JSExpression first,
				JSAssignmentExpression second) {
			super(codeModel);
			Validate.notNull(first);
			Validate.notNull(second);
			this.first = first;
			this.second = second;
		}

		public JSExpression getLeft() {
			return first;
		}

		public JSAssignmentExpression getRight() {
			return second;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitComma(this);
		}
	}

}
