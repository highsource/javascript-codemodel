package org.hisrc.jscm.codemodel;

public interface JSStatementVisitor<V, E extends Exception> {

	public V visitBlock(JSBlock value) throws E;

	public V visitVariable(JSVariableStatement value) throws E;

	public V visitExpression(JSExpressionStatement value) throws E;

	public V visitIf(JSIfStatement value) throws E;

	public V visitDoWhile(JSDoWhileStatement value) throws E;

	public V visitWhile(JSWhileStatement value) throws E;

	public V visitFor(JSForStatement value) throws E;

	public V visitForVar(JSForVarStatement value) throws E;

	public V visitForIn(JSForInStatement value) throws E;

	public V visitForVarIn(JSForVarInStatement value) throws E;

	public V visitContinue(JSContinueStatement value) throws E;

	public V visitBreak(JSBreakStatement value) throws E;

	public V visitReturn(JSReturnStatement value) throws E;

	public V visitWith(JSWithStatement value) throws E;

	public V visitLabelled(JSLabelledStatement value) throws E;

	public V visitSwitch(JSSwitchStatement value) throws E;

	public V visitThrow(JSThrowStatement value) throws E;

	public V visitTryCatch(JSTryCatchStatement value) throws E;

	public V visitTryFinally(JSTryFinallyStatement value) throws E;

	public V visitTryCatchFinally(JSTryCatchFinallyStatement value) throws E;

	public V visitDebugger(JSDebuggerStatement value) throws E;
}
