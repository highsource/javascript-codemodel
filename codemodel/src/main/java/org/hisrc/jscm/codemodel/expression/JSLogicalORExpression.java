package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.JSOperator;

public interface JSLogicalORExpression extends JSConditionalExpression {

	public JSConditionalExpression.Conditional cond(JSAssignmentExpression ifTrue,
			JSAssignmentExpression ifFalse);

	public JSLogicalORExpression.Or or(JSLogicalANDExpression value);

	// //
	public interface Or extends JSLogicalORExpression {
		public JSLogicalORExpression getLeft();
		
		public JSOperator getOperator();

		public JSLogicalANDExpression getRight();
	}

}
