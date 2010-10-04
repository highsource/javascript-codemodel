package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.JSVariable;

public interface JSTryStatement extends JSStatement {

	public JSBlock body();

	public JSBlock _catch();

	public JSVariable exception();

	public JSBlock _finally();

}
