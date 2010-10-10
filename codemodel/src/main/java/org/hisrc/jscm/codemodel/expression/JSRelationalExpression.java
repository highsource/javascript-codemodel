package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;

public interface JSRelationalExpression extends JSEqualityExpression {

	public JSRelationalExpression.Relational lt(JSShiftExpression expression);

	public JSRelationalExpression.Relational gt(JSShiftExpression expression);

	public JSRelationalExpression.Relational le(JSShiftExpression expression);

	public JSRelationalExpression.Relational ge(JSShiftExpression expression);

	public JSRelationalExpression.Relational _instanceof(
			JSShiftExpression expression);

	public JSRelationalExpression.Relational in(JSShiftExpression expression);

	public interface Relational extends JSRelationalExpression,
			JSBinaryExpression {
		@Override
		public JSRelationalExpression getLeft();

		@Override
		public JSBinaryOperator getOperator();

		@Override
		public JSShiftExpression getRight();
	}

}
