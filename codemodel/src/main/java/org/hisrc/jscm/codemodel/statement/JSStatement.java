package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.JSSourceElement;

public interface JSStatement extends JSSourceElement {
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E;
}
