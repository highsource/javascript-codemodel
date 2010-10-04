package org.hisrc.jscm.codemodel.expression;

public interface JSNewExpression extends JSLeftHandSideExpression {

	public JSNewExpression.New _new();

	public interface New extends JSNewExpression {

		public JSNewExpression getBase();

	}
}
