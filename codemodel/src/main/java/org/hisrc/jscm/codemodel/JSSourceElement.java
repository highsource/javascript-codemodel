package org.hisrc.jscm.codemodel;


public interface JSSourceElement {

	public <V, E extends Exception> V acceptSourceElementVisitor(
			JSSourceElementVisitor<V, E> visitor) throws E;
}
