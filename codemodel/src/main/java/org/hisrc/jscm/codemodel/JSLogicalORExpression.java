package org.hisrc.jscm.codemodel;

public interface JSLogicalORExpression extends JSConditionalExpression {

	public JSConditionalExpression.Conditional cond(JSAssignmentExpression ifTrue,
			JSAssignmentExpression ifFalse);

	public JSLogicalORExpression.Or or(JSLogicalANDExpression value);

	// //
	public interface Or extends JSLogicalORExpression {
		public JSLogicalORExpression getLeft();

		public JSLogicalANDExpression getRight();
	}

}
