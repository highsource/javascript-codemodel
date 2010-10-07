package org.hisrc.jscm.codemodel.operator;


public interface JSOperator {

	public String asString();

	public <V, E extends Exception> V acceptOperatorVisitor(
			JSOperatorVisitor<V, E> visitor) throws E;

}
