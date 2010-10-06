package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSVariable;

public interface JSTryStatement extends JSStatement {

	public JSBlock getBody();

	public JSBlock getCatch();

	public JSBlock getFinally();

	public JSVariable getException();

}
