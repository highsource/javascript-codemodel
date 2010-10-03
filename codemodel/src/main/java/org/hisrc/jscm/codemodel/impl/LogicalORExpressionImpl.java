package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSLogicalANDExpression;
import org.hisrc.jscm.codemodel.JSLogicalORExpression;

public abstract class LogicalORExpressionImpl extends ConditionalExpressionImpl
		implements JSLogicalORExpression {

	public LogicalORExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public Conditional cond(JSAssignmentExpression ifTrue,
			JSAssignmentExpression ifFalse) {
		return new ConditionalImpl(getCodeModel(), this, ifTrue, ifFalse);
	}

	@Override
	public Or or(JSLogicalANDExpression value) {
		return new OrImpl(getCodeModel(), this, value);
	}

	public static class OrImpl extends LogicalORExpressionImpl implements Or {
		private final JSLogicalORExpression left;
		private final JSLogicalANDExpression right;

		public OrImpl(JSCodeModel codeModel, JSLogicalORExpression left,
				JSLogicalANDExpression right) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			this.left = left;
			this.right = right;

		}

		public JSLogicalORExpression getLeft() {
			return left;
		}

		public JSLogicalANDExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitOr(this);
		}

	}

}
