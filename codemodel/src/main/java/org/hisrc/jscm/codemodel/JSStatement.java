package org.hisrc.jscm.codemodel;

public interface JSStatement extends JSSourceElement {

	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E;

}
