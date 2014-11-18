package org.hisrc.jscm.codemodel.statement;

public interface JSLabelledStatement extends JSStatement, JSStatementGenerator {

	public JSLabel getLabel();

	public JSStatement getStatement();

	public interface JSLabel extends JSLabelReference {

	}
}
