package org.hisrc.jscm.codemodel.statement;

import java.util.List;

public interface JSBlock extends JSStatement, JSStatementGenerator {

	public List<JSStatement> getStatements();
}
