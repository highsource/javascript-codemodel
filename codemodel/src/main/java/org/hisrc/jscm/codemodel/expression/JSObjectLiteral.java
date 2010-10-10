package org.hisrc.jscm.codemodel.expression;

import java.util.List;

import org.hisrc.jscm.codemodel.JSPropertyName;

public interface JSObjectLiteral extends JSPrimaryExpression {

	public JSObjectLiteral append(String name, JSAssignmentExpression expression);

	public JSObjectLiteral append(JSPropertyName name,
			JSAssignmentExpression expression);

	public List<JSPropertyAssignment> getPropertyAssignments();

	public static interface JSPropertyAssignment {

		public JSPropertyName getKey();

		public JSAssignmentExpression getValue();

	}
}
