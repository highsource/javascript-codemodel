package org.hisrc.jscm.codemodel;


public interface JSIfStatement extends JSStatement {

	public JSExpression getExpression();

	public JSBlock _then();

	public JSBlock _else();

}
