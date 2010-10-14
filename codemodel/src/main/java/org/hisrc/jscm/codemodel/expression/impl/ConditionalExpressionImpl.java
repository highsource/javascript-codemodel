package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSConditionalExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSLogicalORExpression;
import org.hisrc.jscm.codemodel.lang.Validate;

public abstract class ConditionalExpressionImpl extends
		AssignmentExpressionImpl implements JSConditionalExpression {

	public ConditionalExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public static class ConditionalImpl extends ConditionalExpressionImpl
			implements Conditional {

		private final JSLogicalORExpression condition;
		private final JSAssignmentExpression ifTrue;
		private final JSAssignmentExpression ifFalse;

		public ConditionalImpl(JSCodeModel codeModel,
				JSLogicalORExpression condition, JSAssignmentExpression ifTrue,
				JSAssignmentExpression ifFalse) {
			super(codeModel);
			Validate.notNull(condition);
			Validate.notNull(ifTrue);
			this.condition = condition;
			this.ifTrue = ifTrue;
			this.ifFalse = ifFalse;
		}

		public JSLogicalORExpression getCondition() {
			return condition;
		}

		public JSAssignmentExpression getIfTrue() {
			return ifTrue;
		}

		public JSAssignmentExpression getIfFalse() {
			return ifFalse;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitConditional(this);
		}

	}

}
