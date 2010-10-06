package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.JSIdentifier;

public interface JSLabelledStatement extends JSStatement, JSStatementGenerator {

	public JSLabel getLabel();

	public JSStatement getStatement();

	public interface JSLabel extends JSIdentifier {

	}
}
