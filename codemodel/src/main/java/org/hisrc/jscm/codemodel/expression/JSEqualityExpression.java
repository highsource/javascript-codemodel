package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.operator.impl.EqualityOperator;

public interface JSEqualityExpression extends JSBitwiseANDExpression {

	public JSEqualityExpression.Equality eq(JSRelationalExpression value);

	public JSEqualityExpression.Equality ne(JSRelationalExpression value);

	public JSEqualityExpression.Equality eeq(JSRelationalExpression value);

	public JSEqualityExpression.Equality nee(JSRelationalExpression value);

	public interface Equality extends JSEqualityExpression, JSBinaryExpression {
		@Override
		public JSEqualityExpression getLeft();

		@Override
		public EqualityOperator getOperator();

		@Override
		public JSRelationalExpression getRight();
	}

}
