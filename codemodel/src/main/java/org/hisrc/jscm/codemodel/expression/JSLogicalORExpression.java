package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public interface JSLogicalORExpression extends JSConditionalExpression {

	public JSConditionalExpression.Conditional cond(
			JSAssignmentExpression ifTrue, JSAssignmentExpression ifFalse);

	public JSLogicalORExpression.Or or(JSLogicalANDExpression value);

	public interface Or extends JSLogicalORExpression, JSBinaryExpression {
		@Override
		public JSLogicalORExpression getLeft();

		@Override
		public JSBinaryOperator getOperator();

		@Override
		public JSLogicalANDExpression getRight();
	}

}
