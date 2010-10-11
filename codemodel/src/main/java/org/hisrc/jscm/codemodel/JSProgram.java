package org.hisrc.jscm.codemodel;

public interface JSProgram extends JSSourceElements {

	public <V, E extends Exception> V acceptProgramVisitor(
			JSProgramVisitor<V, E> visitor) throws E;

}
