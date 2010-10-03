package org.hisrc.jscm.codemodel;

import java.util.List;


public interface JSBlock extends JSStatement, JSStatements {

	public List<JSStatement> getStatements();

}
