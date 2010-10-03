package org.hisrc.jscm.codemodel;

import java.util.List;
import java.util.Map.Entry;

public interface JSObjectLiteral extends JSPrimaryExpression {

	public JSObjectLiteral add(JSPropertyName name,
			JSAssignmentExpression expression);

	public List<JSPropertyAssignment> getPropertyAssignments();

	public static interface JSPropertyAssignment extends
			Entry<JSPropertyName, JSAssignmentExpression> {

	}
}
