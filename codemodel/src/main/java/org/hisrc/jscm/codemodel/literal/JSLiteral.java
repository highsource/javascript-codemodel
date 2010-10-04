package org.hisrc.jscm.codemodel.literal;

import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression;

public interface JSLiteral extends JSPrimaryExpression {

	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E;

}
