package org.hisrc.jscm.codemodel.expression;

public interface JSArrayElement {

	public <V, E extends Exception> V acceptArrayElementVisitor(
			JSArrayElementVisitor<V, E> visitor) throws E;

}
