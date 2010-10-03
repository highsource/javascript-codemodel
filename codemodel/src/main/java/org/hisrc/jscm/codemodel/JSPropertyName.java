package org.hisrc.jscm.codemodel;

public interface JSPropertyName {

	public <V, E extends Exception> V acceptPropertyNameVisitor(
			JSPropertyNameVisitor<V, E> visitor) throws E;

}
