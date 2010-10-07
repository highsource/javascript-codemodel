package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public interface JSBinaryExpression extends JSExpression {

	public JSExpression getLeft();

	public JSBinaryOperator getOperator();

	public JSExpression getRight();

}
