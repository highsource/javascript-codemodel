package org.hisrc.jscm.codemodel.expression;

public interface JSArrayElementVisitor<V, E extends Exception> {

	public V visitElision(JSElision value) throws E;

	public V visitAssignment(JSAssignmentExpression value) throws E;
}
