package org.hisrc.jscm.codemodel.impl.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;

public abstract class AssignmentExpressionImpl extends ExpressionImpl implements
		JSAssignmentExpression {

	public AssignmentExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public static class AssignmentImpl extends AssignmentExpressionImpl
			implements Assignment {

		private final JSLeftHandSideExpression left;
		private final JSAssignmentExpression right;
		private final AssignmentOperator operator;

		public AssignmentImpl(JSCodeModel codeModel, JSLeftHandSideExpression left,
				JSAssignmentExpression right, AssignmentOperator operator) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			Validate.notNull(operator);
			this.left = left;
			this.right = right;
			this.operator = operator;

		}

		public JSLeftHandSideExpression getLeft() {
			return left;
		}

		public JSAssignmentExpression getRight() {
			return right;
		}

		public AssignmentOperator getOperator() {
			return operator;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitAssignment(this);
		}

	}

}
