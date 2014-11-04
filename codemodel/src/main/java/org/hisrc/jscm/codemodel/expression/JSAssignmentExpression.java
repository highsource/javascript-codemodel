package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.impl.AssignmentOperator;

// 11.14
public interface JSAssignmentExpression extends JSExpression, JSArrayElement {

	public interface Assignment extends JSAssignmentExpression,
			JSBinaryExpression {

		@Override
		public JSLeftHandSideExpression getLeft();

		@Override
		public AssignmentOperator getOperator();

		@Override
		public JSAssignmentExpression getRight();
	}
}
