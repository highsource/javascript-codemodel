package org.hisrc.jscm.codemodel;

public interface JSTryCatchFinallyStatement extends JSTryCatchStatement,
		JSTryFinallyStatement {

	public String identifier();

	public JSBlock _catch();

}
