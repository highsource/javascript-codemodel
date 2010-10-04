package org.hisrc.jscm.codemodel;

public interface JSTryFinallyStatement extends JSTryStatement {

	public String getCatchIdentifier();

	public JSBlock _catch();

}
