package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.util.List;

import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.impl.AssignmentOperator;
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
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement.JSCaseClause;
import org.hisrc.jscm.codemodel.statement.JSThrowStatement;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.statement.JSVariableDeclaration;
import org.hisrc.jscm.codemodel.statement.JSVariableStatement;
import org.hisrc.jscm.codemodel.statement.JSWhileStatement;
import org.hisrc.jscm.codemodel.statement.JSWithStatement;

public class StatementWriter implements
		JSStatementVisitor<CodeWriter, IOException> {

	protected final CodeWriter f;

	public StatementWriter(CodeWriter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public CodeWriter visitBlock(JSBlock value) throws IOException {
		f.openCurlyBracket();
		f.lineTerminator();
		f.indent();
		final List<JSStatement> statements = value.getStatements();

		for (int index = 0; index < statements.size(); index++) {
			final JSStatement statement = statements.get(index);
			f.statement(statement);
			f.lineTerminator();
		}
		f.unindent();
		f.closeCurlyBracket();
		return f;
	}

	@Override
	public CodeWriter visitBreak(JSBreakStatement value) throws IOException {
		f.keyword("break");
		if (value.getLabel() != null) {
			f.whiteSpace();
			f.identifier(value.getLabel().getName());
		}
		f.semicolon();
		return f;
	}

	@Override
	public CodeWriter visitContinue(JSContinueStatement value)
			throws IOException {
		f.keyword("continue");
		if (value.getLabel() != null) {
			f.whiteSpace();
			f.identifier(value.getLabel().getName());
		}
		f.semicolon();
		return f;
	}

	@Override
	public CodeWriter visitDebugger(JSDebuggerStatement value)
			throws IOException {
		f.keyword("debugger");
		f.semicolon();
		return f;
	}

	@Override
	public CodeWriter visitDoWhile(JSDoWhileStatement value) throws IOException {
		f.keyword("do");
		f.block(value.getStatement());
		f.lineTerminator().keyword("while").whiteSpace();
		f.openRoundBracket();
		f.expression(value.getExpression());
		f.closeRoundBracket();
		f.semicolon();
		return f;
	}

	@Override
	public CodeWriter visitEmpty(JSEmptyStatement value) throws IOException {
		f.semicolon();
		return f;

	}

	@Override
	public CodeWriter visitExpression(JSExpressionStatement value)
			throws IOException {
		f.expression(value.getExpression());
		f.semicolon();
		return f;
	}

	@Override
	public CodeWriter visitFor(JSForStatement value) throws IOException {
		f.keyword("for");
		f.openRoundBracket();
		if (value.getExpression() != null) {
			f.expression(value.getExpression());
		}
		f.semicolon();
		if (value.getTest() != null) {
			f.whiteSpace();
			f.expression(value.getTest());
		}
		f.semicolon();
		if (value.getUpdate() != null) {
			f.whiteSpace();
			f.expression(value.getUpdate());
		}
		f.closeRoundBracket().whiteSpace();
		f.block(value.getStatement());
		return f;
	}

	@Override
	public CodeWriter visitForIn(JSForInStatement value) throws IOException {
		f.keyword("for").whiteSpace();
		f.openRoundBracket();
		f.expression(value.getExpression());
		f.whiteSpace().keyword("in").whiteSpace();
		f.expression(value.getIn());
		f.closeRoundBracket();
		f.whiteSpace();
		f.block(value.getStatement());

		return f;
	}

	@Override
	public CodeWriter visitForVar(JSForVarStatement value) throws IOException {
		f.keyword("for").whiteSpace();
		f.openRoundBracket();
		visitVariableDeclarations(value.getVariableDeclarations());
		f.semicolon();
		if (value.getTest() != null) {
			f.whiteSpace();
			f.expression(value.getTest());
		}
		f.semicolon();
		if (value.getUpdate() != null) {
			f.whiteSpace();
			f.expression(value.getUpdate());
		}
		f.closeRoundBracket().whiteSpace();
		f.block(value.getStatement());

		return f;
	}

	@Override
	public CodeWriter visitForVarIn(JSForVarInStatement value)
			throws IOException {
		f.keyword("for").whiteSpace();
		f.openRoundBracket();

		f.keyword("var").whiteSpace();
		f.identifier(value.getVariable().getName());
		f.whiteSpace().keyword("in").whiteSpace();
		f.expression(value.getIn());
		f.closeRoundBracket().whiteSpace();
		f.block(value.getStatement());

		return f;
	}

	@Override
	public CodeWriter visitIf(JSIfStatement value) throws IOException {
		f.keyword("if").whiteSpace();
		f.openRoundBracket();
		f.indent().expression(value.getIf()).unindent();
		f.closeRoundBracket();
		f.whiteSpace();
		f.block(value.getThen());
		if (value.getElse() != null) {
			f.lineTerminator().keyword("else").whiteSpace();
			f.block(value.getElse());
		}
		return f;
	}

	@Override
	public CodeWriter visitLabelled(JSLabelledStatement value)
			throws IOException {
		f.identifier(value.getLabel().getName());
		f.colon().whiteSpace();
		f.statement(value.getStatement());
		return f;
	}

	@Override
	public CodeWriter visitReturn(JSReturnStatement value) throws IOException {
		f.keyword("return");
		if (value.getReturn() != null) {
			f.whiteSpace();
			f.expression(value.getReturn());
		}
		f.semicolon();
		return f;
	}

	@Override
	public CodeWriter visitSwitch(JSSwitchStatement value) throws IOException {

		f.keyword("switch").whiteSpace();
		f.openRoundBracket();
		f.indent().expression(value.getExpression()).unindent();
		f.closeRoundBracket();
		f.whiteSpace();
		f.openCurlyBracket();
		f.lineTerminator();
		// TODO
		final CodeWriter fi = f.indent();
		for (JSCaseClause caseClause : value.getFirstCaseClauses()) {
			fi.keyword("case").whiteSpace();
			fi.indent().expression(caseClause.getExpression());
			fi.colon().whiteSpace();
			List<JSStatement> statements = caseClause.getStatements();
			for (int index = 0; index < statements.size(); index++) {
				final JSStatement statement = statements.get(index);
				fi.block(statement);
			}
			fi.lineTerminator();
		}
		if (value.getDefaultClause() != null) {
			fi.keyword("default");
			fi.colon().whiteSpace();
			final List<JSStatement> statements = value.getDefaultClause()
					.getStatements();
			for (int index = 0; index < statements.size(); index++) {
				final JSStatement statement = statements.get(index);
				fi.block(statement);
			}
			fi.lineTerminator();
		}

		for (JSCaseClause caseClause : value.getSecondCaseClauses()) {
			fi.keyword("case").whiteSpace();
			f.expression(caseClause.getExpression());
			fi.colon().whiteSpace();
			final List<JSStatement> statements = caseClause.getStatements();
			for (int index = 0; index < statements.size(); index++) {
				final JSStatement statement = statements.get(index);
				fi.block(statement);
			}
			fi.lineTerminator();
		}
		f.closeCurlyBracket();
		return f;
	}

	@Override
	public CodeWriter visitThrow(JSThrowStatement value) throws IOException {
		return f.keyword("throw").whiteSpace()
				.expression(value.getExpression()).semicolon();
	}

	@Override
	public CodeWriter visitTry(JSTryStatement value) throws IOException {
		f.keyword("try").whiteSpace();
		f.statement(value.getBody());
		if (value.getCatch() != null) {
			f.lineTerminator();
			f.keyword("catch").whiteSpace();
			f.openRoundBracket();
			f.expression(value.getException());
			f.closeRoundBracket().whiteSpace();
			f.statement(value.getCatch());
		}
		if (value.getFinally() != null) {
			f.lineTerminator();
			f.keyword("finally").whiteSpace();
			f.statement(value.getFinally());
		}
		return f;
	}

	@Override
	public CodeWriter visitVariable(JSVariableStatement value)
			throws IOException {
		final List<JSVariableDeclaration> variableDeclarations = value
				.getVariableDeclarations();
		return visitVariableDeclarations(variableDeclarations);
	}

	private CodeWriter visitVariableDeclarations(
			final List<JSVariableDeclaration> variableDeclarations)
			throws IOException {
		f.keyword("var").whiteSpace();

		for (int index = 0; index < variableDeclarations.size(); index++) {
			if (index > 0) {
				f.comma().whiteSpace();
			}
			final JSVariableDeclaration variableDeclaration = variableDeclarations
					.get(index);
			f.identifier(variableDeclaration.getVariable().getName());
			if (variableDeclaration.getExpression() != null) {
				f.operator(AssignmentOperator.ASSIGN);
				f.expression(variableDeclaration.getExpression());
			}

		}
		f.semicolon();
		return f;
	}

	@Override
	public CodeWriter visitWhile(JSWhileStatement value) throws IOException {

		f.keyword("while").whiteSpace();
		f.openRoundBracket();
		f.indent().expression(value.getExpression()).unindent();
		f.closeRoundBracket();
		f.block(value.getStatement());

		return f;
	}

	@Override
	public CodeWriter visitWith(JSWithStatement value) throws IOException {
		f.keyword("with").whiteSpace();
		f.openRoundBracket();
		f.indent().expression(value.getWith()).unindent();
		f.closeRoundBracket().whiteSpace();
		f.block(value.getStatement());
		return f;
	}

}
