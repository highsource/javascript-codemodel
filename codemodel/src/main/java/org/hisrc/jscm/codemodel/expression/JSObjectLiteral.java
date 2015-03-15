package org.hisrc.jscm.codemodel.expression;

import java.util.List;

import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSPropertyName;

public interface JSObjectLiteral extends JSPrimaryExpression {

	public JSObjectLiteral append(String name, JSAssignmentExpression expression);

	public JSObjectLiteral append(JSPropertyName name,
			JSAssignmentExpression expression);

	public JSProperty appendProperty(String name,
			JSAssignmentExpression expression);

	public JSProperty appendProperty(JSPropertyName name,
			JSAssignmentExpression expression);

	public JSGetter appendGetter(String name);

	public JSGetter appendGetter(JSPropertyName name);

	public JSSetter appendSetter(String name, String parameter);

	public JSSetter appendSetter(JSPropertyName name, String parameter);

	public List<JSPropertyAssignment> getPropertyAssignments();

	public static interface JSPropertyAssignment {

		public JSPropertyName getKey();

		public <V, E extends Exception> V acceptPropertyAssignmentVisitor(
				JSPropertyAssignmentVisitor<V, E> visitor) throws E;
	}

	public static interface JSProperty extends JSPropertyAssignment {

		public JSAssignmentExpression getValue();

	}

	public static interface JSPropertyAccessor extends JSPropertyAssignment {

		public JSFunctionBody getBody();
	}

	public static interface JSGetter extends JSPropertyAccessor {

	}

	public static interface JSSetter extends JSPropertyAccessor {

		public JSVariable getParameter();

	}

}
