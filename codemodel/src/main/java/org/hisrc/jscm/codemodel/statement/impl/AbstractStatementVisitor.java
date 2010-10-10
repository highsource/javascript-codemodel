package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSBreakStatement;
import org.hisrc.jscm.codemodel.statement.JSContinueStatement;
import org.hisrc.jscm.codemodel.statement.JSDebuggerStatement;
import org.hisrc.jscm.codemodel.statement.JSDoWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSEmptyStatement;
import org.hisrc.jscm.codemodel.statement.JSExpressionStatement;
import org.hisrc.jscm.codemodel.statement.JSForInStatement;
import org.hisrc.jscm.codemodel.statement.JSForStatement;
import org.hisrc.jscm.codemodel.statement.JSForVarInStatement;
import org.hisrc.jscm.codemodel.statement.JSForVarStatement;
import org.hisrc.jscm.codemodel.statement.JSIfStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement;
import org.hisrc.jscm.codemodel.statement.JSReturnStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement;
import org.hisrc.jscm.codemodel.statement.JSThrowStatement;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.statement.JSVariableStatement;
import org.hisrc.jscm.codemodel.statement.JSWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSWithStatement;

public abstract class AbstractStatementVisitor<V, E extends Exception>
		implements JSStatementVisitor<V, E> {

	public abstract V visitStatement(JSStatement statement) throws E;

	public V visitBlock(JSBlock value) throws E {
		return visitStatement(value);
	}

	public V visitBreak(JSBreakStatement value) throws E {
		return visitStatement(value);
	}

	public V visitContinue(JSContinueStatement value) throws E {
		return visitStatement(value);
	}

	public V visitDebugger(JSDebuggerStatement value) throws E {
		return visitStatement(value);
	}

	public V visitDoWhile(JSDoWhileStatement value) throws E {
		return visitStatement(value);
	}

	public V visitEmpty(JSEmptyStatement value) throws E {
		return visitStatement(value);
	}

	public V visitExpression(JSExpressionStatement value) throws E {
		return visitStatement(value);
	}

	public V visitFor(JSForStatement value) throws E {
		return visitStatement(value);
	}

	public V visitForIn(JSForInStatement value) throws E {
		return visitStatement(value);
	}

	public V visitForVar(JSForVarStatement value) throws E {
		return visitStatement(value);
	}

	public V visitForVarIn(JSForVarInStatement value) throws E {
		return visitStatement(value);
	}

	public V visitIf(JSIfStatement value) throws E {
		return visitStatement(value);
	}

	public V visitLabelled(JSLabelledStatement value) throws E {
		return visitStatement(value);
	}

	public V visitReturn(JSReturnStatement value) throws E {
		return visitStatement(value);
	}

	public V visitSwitch(JSSwitchStatement value) throws E {
		return visitStatement(value);
	}

	public V visitThrow(JSThrowStatement value) throws E {
		return visitStatement(value);
	}

	public V visitTry(JSTryStatement value) throws E {
		return visitStatement(value);
	}

	public V visitVariable(JSVariableStatement value) throws E {
		return visitStatement(value);
	}

	public V visitWhile(JSWhileStatement value) throws E {
		return visitStatement(value);
	}

	public V visitWith(JSWithStatement value) throws E {
		return visitStatement(value);
	}
}
