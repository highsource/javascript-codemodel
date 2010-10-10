package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSPostfixOperator;

public interface JSPostfixExpression extends JSUnaryExpression {

	public interface Postfix extends JSPostfixExpression {
		public JSLeftHandSideExpression getBase();

		public JSPostfixOperator getOperator();
	}

}
