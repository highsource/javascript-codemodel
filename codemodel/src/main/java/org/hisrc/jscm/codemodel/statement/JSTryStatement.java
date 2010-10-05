package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSVariable;

public interface JSTryStatement extends JSStatement {

	public JSBlock body();

	public JSBlock _catch();

	public JSVariable getException();

	public JSBlock _finally();

}
