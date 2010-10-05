package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;

public interface JSBreakStatement extends JSStatement {

	public JSLabel getLabel();

}
