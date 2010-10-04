package org.hisrc.jscm.codemodel;

public interface JSTryCatchStatement extends JSTryStatement {

	public String getCatchIdentifier();

	public JSBlock _catch();

}
