package org.hisrc.jscm.codemodel;

// 11.1
public interface JSPrimaryExpression extends JSMemberExpression {

	public interface Brackets extends JSPrimaryExpression {

		public JSExpression getBase();
	}

}
