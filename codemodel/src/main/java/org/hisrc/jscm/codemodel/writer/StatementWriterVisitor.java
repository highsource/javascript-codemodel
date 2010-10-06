package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
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

public class StatementWriterVisitor implements
		JSStatementVisitor<JSFormatter, IOException> {

	private final JSFormatter f;

	public StatementWriterVisitor(JSFormatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public JSFormatter visitBlock(JSBlock value) throws IOException {
		f.openCurlyBracket();
		if (!value.getStatements().isEmpty()) {
			f.lineBreak();
			JSFormatter fi = f.indented();
			for (JSStatement statement : value.getStatements()) {
				statement
						.acceptStatementVisitor(new StatementWriterVisitor(fi));
				fi.lineBreak();
			}
		}
		f.closeCurlyBracket();
		return f;
	}

	@Override
	public JSFormatter visitBreak(JSBreakStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitContinue(JSContinueStatement value)
			throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitDebugger(JSDebuggerStatement value)
			throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitDoWhile(JSDoWhileStatement value)
			throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitEmpty(JSEmptyStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitExpression(JSExpressionStatement value)
			throws IOException {
		value.getExpression().acceptExpressionVisitor(
				new ExpressionWriterVisitor(f));
		f.semicolon();
		return f;
	}

	@Override
	public JSFormatter visitFor(JSForStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitForIn(JSForInStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitForVar(JSForVarStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitForVarIn(JSForVarInStatement value)
			throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitIf(JSIfStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitLabelled(JSLabelledStatement value)
			throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitReturn(JSReturnStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitSwitch(JSSwitchStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitThrow(JSThrowStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitTry(JSTryStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitVariable(JSVariableStatement value)
			throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitWhile(JSWhileStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSFormatter visitWith(JSWithStatement value) throws IOException {
		throw new UnsupportedOperationException();
	}
}
