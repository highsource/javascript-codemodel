package org.hisrc.jscm.codemodel.expression;

import java.util.List;

import org.hisrc.jscm.codemodel.JSPropertyName;

// 11.2
public interface JSCallExpression extends JSLeftHandSideExpression {

	public JSCallExpression.CallArgs invoke();

	public JSCallExpression.CallElement element(JSExpression index);

	public JSCallExpression.CallProperty property(String name);

	public JSCallExpression.CallProperty property(JSPropertyName name);

	public interface Call extends JSCallExpression {
		public JSCallExpression getBase();
	}

	public interface CallArgs extends Call, JSInvocationExpression {
		public CallArgs args(JSAssignmentExpression... arg);

		public List<JSAssignmentExpression> getArgs();
	}

	public interface CallElement extends Call {
		public JSExpression getIndex();
	}

	public interface CallProperty extends Call {

		public JSPropertyName getName();
	}

	public interface MemberCall extends JSCallExpression,
			JSInvocationExpression {

		public MemberCall args(JSAssignmentExpression... arg);

		public JSMemberExpression getBase();

		public List<JSAssignmentExpression> getArgs();
	}

}
