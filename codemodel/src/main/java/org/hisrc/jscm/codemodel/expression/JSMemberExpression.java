package org.hisrc.jscm.codemodel.expression;

import java.util.List;

import org.hisrc.jscm.codemodel.JSPropertyName;

public interface JSMemberExpression extends JSNewExpression {

	public JSMemberExpression.MemberElement element(JSExpression expression);

	public JSMemberExpression.MemberProperty property(String propertyName);

	public JSMemberExpression.MemberProperty property(
			JSPropertyName propertyName);

	public JSMemberExpression.MemberNew instantiate();

	public JSCallExpression.MemberCall invoke();

	public interface Member extends JSMemberExpression {
		public JSMemberExpression getBase();
	}

	public interface MemberElement extends Member {
		public JSExpression getIndex();
	}

	public interface MemberProperty extends Member {
		public JSPropertyName getName();
	}

	public interface MemberNew extends Member, JSInvocationExpression {

		public MemberNew args(JSAssignmentExpression... arg);

		public List<JSAssignmentExpression> getArgs();
	}
}
