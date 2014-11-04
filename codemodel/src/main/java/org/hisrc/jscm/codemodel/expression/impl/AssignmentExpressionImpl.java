package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSArrayElementVisitor;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.impl.AssignmentOperator;

public abstract class AssignmentExpressionImpl extends ExpressionImpl implements
		JSAssignmentExpression {

	public AssignmentExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public <V, E extends Exception> V acceptArrayElementVisitor(
			JSArrayElementVisitor<V, E> visitor) throws E {
		return visitor.visitAssignment(this);
	}

	public static class AssignmentImpl extends AssignmentExpressionImpl
			implements Assignment {

		private final JSLeftHandSideExpression left;
		private final JSAssignmentExpression right;
		private final AssignmentOperator operator;

		public AssignmentImpl(JSCodeModel codeModel,
				JSLeftHandSideExpression left, JSAssignmentExpression right,
				AssignmentOperator operator) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			Validate.notNull(operator);
			this.left = left;
			this.right = right;
			this.operator = operator;

		}

		@Override
		public JSLeftHandSideExpression getLeft() {
			return left;
		}

		@Override
		public JSAssignmentExpression getRight() {
			return right;
		}

		@Override
		public AssignmentOperator getOperator() {
			return operator;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitAssignment(this);
		}

	}

}
