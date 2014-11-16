package org.hisrc.jscm.codemodel.expression;

import java.util.List;

import org.hisrc.jscm.codemodel.JSPropertyName;

public interface JSMemberExpression extends JSNewExpression {

	public JSMemberExpression.MemberElement e(JSExpression expression);

	public JSMemberExpression.MemberElement element(JSExpression expression);

	public JSMemberExpression.MemberProperty p(String propertyName);

	public JSMemberExpression.MemberProperty property(String propertyName);

	// TODO Not correct
	public JSMemberExpression.MemberProperty p(JSPropertyName propertyName);

	public JSMemberExpression.MemberProperty property(
			JSPropertyName propertyName);

	public JSCallExpression.MemberCall i();

	public JSCallExpression.MemberCall invoke();

	public JSCallExpression.MemberCall i(String name);

	public JSCallExpression.MemberCall invoke(String name);

	public JSMemberExpression.MemberNew instantiate();

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

		@Override
		public List<JSAssignmentExpression> getArgs();
	}
}
