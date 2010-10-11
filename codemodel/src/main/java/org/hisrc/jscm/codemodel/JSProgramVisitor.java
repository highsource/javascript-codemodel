package org.hisrc.jscm.codemodel;

public interface JSProgramVisitor<V, E extends Exception> {

	public V visitProgram(JSProgram value) throws E;

}
