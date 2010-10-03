package org.hisrc.jscm.codemodel;

public interface JSLiteral extends JSPrimaryExpression {

	public <V, E extends Exception> V acceptLiteralVisitor(
			JSLiteralVisitor<V, E> visitor) throws E;

}
