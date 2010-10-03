package org.hisrc.jscm.codemodel;

import java.util.List;

public interface JSMemberExpression extends JSNewExpression {

	public JSMemberExpression.MemberElement element(JSExpression expression);

	public JSMemberExpression.MemberProperty property(
			JSPropertyName propertyName);

	public JSMemberExpression.MemberNew _new(
			JSAssignmentExpression... expressions);

	public JSCallExpression.MemberCall call(
			JSAssignmentExpression... expressions);

	public interface Member extends JSMemberExpression {
		public JSMemberExpression getBase();
	}

	public interface MemberElement extends Member {
		public JSExpression getIndex();
	}

	public interface MemberProperty extends Member {
		public JSPropertyName getName();
	}

	public interface MemberNew extends Member {
		public List<JSAssignmentExpression> getArgs();
	}
}
