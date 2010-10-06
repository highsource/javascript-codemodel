package org.hisrc.jscm.codemodel.impl.expression;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSOperator;
import org.hisrc.jscm.codemodel.expression.JSBitwiseANDExpression;
import org.hisrc.jscm.codemodel.expression.JSEqualityExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;

public abstract class BitwiseANDExpressionImpl extends BitwiseXORExpressionImpl
		implements JSBitwiseANDExpression {

	public BitwiseANDExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public Band band(JSEqualityExpression value) {
		return new BandImpl(getCodeModel(), this, value);
	}

	public static class BandImpl extends BitwiseANDExpressionImpl implements
			Band {
		private final JSBitwiseANDExpression left;
		private final JSOperator operator = new OperatorImpl("&");
		private final JSEqualityExpression right;

		public BandImpl(JSCodeModel codeModel, JSBitwiseANDExpression left,
				JSEqualityExpression right) {
			super(codeModel);
			Validate.notNull(left);
			Validate.notNull(right);
			this.left = left;
			this.right = right;

		}

		public JSBitwiseANDExpression getLeft() {
			return left;
		}
		
		public JSOperator getOperator() {
			return operator;
		}
		

		public JSEqualityExpression getRight() {
			return right;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitBand(this);
		}

	}
}
