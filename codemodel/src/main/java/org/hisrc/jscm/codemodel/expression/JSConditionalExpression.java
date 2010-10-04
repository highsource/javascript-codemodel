package org.hisrc.jscm.codemodel.expression;

// 11.12
public interface JSConditionalExpression extends JSAssignmentExpression {

	public interface Conditional extends JSConditionalExpression {
		public JSLogicalORExpression getCondition();

		public JSAssignmentExpression getIfTrue();

		public JSAssignmentExpression getIfFalse();
	}

}
