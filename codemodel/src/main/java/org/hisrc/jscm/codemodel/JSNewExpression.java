package org.hisrc.jscm.codemodel;

public interface JSNewExpression extends JSLeftHandSideExpression {

	public JSNewExpression.New _new();

	public interface New extends JSNewExpression {

		public JSNewExpression getBase();

	}
}
