package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression.AssignmentOperator;
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

public class StatementWriterVisitor implements
		JSStatementVisitor<JSFormatter, IOException> {

	private final JSFormatter f;

	public StatementWriterVisitor(JSFormatter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public JSFormatter visitBlock(JSBlock value) throws IOException {
		f.startBlock();
		if (!value.getStatements().isEmpty()) {
//			f.lineBreak();
			for (JSStatement statement : value.getStatements()) {
				statement
						.acceptStatementVisitor(indentedBlockStatementVisitor());
			}
		}
		f.endBlock();
		return f;
	}

	@Override
	public JSFormatter visitBreak(JSBreakStatement value) throws IOException {
		f.keyword("break");
		if (value.getLabel() != null) {
			f.identifier(value.getLabel().getName());
		}
		f.endStatement();
		return f;
	}

	@Override
	public JSFormatter visitContinue(JSContinueStatement value)
			throws IOException {
		f.keyword("continue");
		if (value.getLabel() != null) {
			f.identifier(value.getLabel().getName());
		}
		f.endStatement();
		return f;
	}

	@Override
	public JSFormatter visitDebugger(JSDebuggerStatement value)
			throws IOException {
		f.keyword("debugger").endStatement();
		return f;
	}

	@Override
	public JSFormatter visitDoWhile(JSDoWhileStatement value)
			throws IOException {
		f.keyword("do");
		value.getStatement().acceptStatementVisitor(indentedStatementVisitor());
		f.keyword("while");
		f.openRoundBracket();
		value.getExpression().acceptExpressionVisitor(expressionVisitor());
		f.closeRoundBracket();
		f.endStatement();
		return f;
	}

	@Override
	public JSFormatter visitEmpty(JSEmptyStatement value) throws IOException {
		f.endStatement();
		return f;

	}

	@Override
	public JSFormatter visitExpression(JSExpressionStatement value)
			throws IOException {
		value.getExpression().acceptExpressionVisitor(
				new ExpressionWriterVisitor(f));
		f.endStatement();
		return f;
	}

	@Override
	public JSFormatter visitFor(JSForStatement value) throws IOException {
		f.keyword("for");
		f.openRoundBracket();
		if (value.getExpression() != null) {
			value.getExpression().acceptExpressionVisitor(expressionVisitor());
		}
		f.semicolon();
		if (value.getTest() != null) {
			value.getTest().acceptExpressionVisitor(expressionVisitor());
		}
		f.semicolon();
		if (value.getUpdate() != null) {
			value.getUpdate().acceptExpressionVisitor(expressionVisitor());
		}
		f.closeRoundBracket();
		value.getStatement().acceptStatementVisitor(indentedStatementVisitor());

		return f;
	}

	@Override
	public JSFormatter visitForIn(JSForInStatement value) throws IOException {
		f.keyword("for");
		f.openRoundBracket();
		value.getExpression().acceptExpressionVisitor(expressionVisitor());
		f.keyword("in");
		value.getIn().acceptExpressionVisitor(expressionVisitor());
		f.closeRoundBracket();
		value.getStatement().acceptStatementVisitor(indentedStatementVisitor());

		return f;
	}

	@Override
	public JSFormatter visitForVar(JSForVarStatement value) throws IOException {
		f.keyword("for");
		f.openRoundBracket();
		f.keyword("var");
		f.identifier(value.getVariable().getName());
		if (value.getExpression() != null) {
			f.operator(AssignmentOperator.ASSIGN);
			value.getExpression().acceptExpressionVisitor(expressionVisitor());
		}
		f.semicolon();
		if (value.getTest() != null) {
			value.getTest().acceptExpressionVisitor(expressionVisitor());
		}
		f.semicolon();
		if (value.getUpdate() != null) {
			value.getUpdate().acceptExpressionVisitor(expressionVisitor());
		}
		f.closeRoundBracket();
		value.getStatement().acceptStatementVisitor(indentedStatementVisitor());

		return f;
	}

	@Override
	public JSFormatter visitForVarIn(JSForVarInStatement value)
			throws IOException {
		f.keyword("for");
		f.openRoundBracket();

		f.keyword("var");
		f.identifier(value.getVariable().getName());
		if (value.getExpression() != null) {
			f.operator(AssignmentOperator.ASSIGN);
			value.getExpression().acceptExpressionVisitor(expressionVisitor());
		}

		f.keyword("in");
		value.getIn().acceptExpressionVisitor(expressionVisitor());
		f.closeRoundBracket();
		value.getStatement().acceptStatementVisitor(indentedStatementVisitor());

		return f;
	}

	@Override
	public JSFormatter visitIf(JSIfStatement value) throws IOException {
		f.keyword("if");
		f.openRoundBracket();
		value.getIf().acceptExpressionVisitor(expressionVisitor());
		f.closeRoundBracket();
		value.getThen().acceptStatementVisitor(indentedStatementVisitor());
		if (value.getElse() != null) {
			f.keyword("else");
			value.getElse().acceptStatementVisitor(indentedStatementVisitor());
		}
		return f;
	}

	@Override
	public JSFormatter visitLabelled(JSLabelledStatement value)
			throws IOException {
		f.identifier(value.getLabel().getName());
		f.colon();
		value.getStatement().acceptStatementVisitor(this);
		return f;
	}

	@Override
	public JSFormatter visitReturn(JSReturnStatement value) throws IOException {
		f.keyword("return");
		if (value.getReturn() != null) {
			value.getReturn().acceptExpressionVisitor(
					new ExpressionWriterVisitor(f));
		}
		f.endStatement();
		return f;
	}

	@Override
	public JSFormatter visitSwitch(JSSwitchStatement value) throws IOException {

		f.keyword("switch");
		f.openRoundBracket();
		value.getExpression().acceptExpressionVisitor(expressionVisitor());
		f.closeRoundBracket();
		f.startBlock();
		for (JSCaseClause caseClause : value.getFirstCaseClauses()) {
			f.keyword("case");
			caseClause.getExpression().acceptExpressionVisitor(
					expressionVisitor());
			f.colon();
			List<JSStatement> statements = caseClause.getStatements();
			for (int index = 0; index < statements.size(); index++) {
				final JSStatement statement = statements.get(index);
				statement.acceptStatementVisitor(indentedStatementVisitor());
			}
		}
		if (value.getDefaultClause() != null) {
			f.keyword("default");
			f.colon();
			List<JSStatement> statements = value.getDefaultClause()
					.getStatements();
			for (int index = 0; index < statements.size(); index++) {
				final JSStatement statement = statements.get(index);
				statement.acceptStatementVisitor(indentedStatementVisitor());
			}
		}

		for (JSCaseClause caseClause : value.getSecondCaseClauses()) {
			f.keyword("case");
			caseClause.getExpression().acceptExpressionVisitor(
					expressionVisitor());
			f.colon();
			List<JSStatement> statements = caseClause.getStatements();
			for (int index = 0; index < statements.size(); index++) {
				final JSStatement statement = statements.get(index);
				statement.acceptStatementVisitor(indentedStatementVisitor());
			}
		}
		f.endBlock();
		return f;
	}

	@Override
	public JSFormatter visitThrow(JSThrowStatement value) throws IOException {
		f.keyword("throw");
		value.getExpression().acceptExpressionVisitor(
				new ExpressionWriterVisitor(f));
		f.endStatement();
		return f;
	}

	@Override
	public JSFormatter visitTry(JSTryStatement value) throws IOException {
		f.keyword("try");
		value.getBody().acceptStatementVisitor(indentedBlockStatementVisitor());
		if (value.getCatch() != null) {
			f.keyword("catch");
			f.openRoundBracket();
			value.getException().acceptExpressionVisitor(expressionVisitor());
			f.closeRoundBracket();
			value.getCatch().acceptStatementVisitor(indentedBlockStatementVisitor());
		}
		if (value.getFinally() != null) {
			f.keyword("finally");
			value.getFinally().acceptStatementVisitor(indentedBlockStatementVisitor());
		}
		return f;
	}

	@Override
	public JSFormatter visitVariable(JSVariableStatement value)
			throws IOException {
		final List<JSVariableDeclaration> variableDeclarations = value
				.getVariableDeclarations();
		return visitVariableDeclarations(variableDeclarations);
	}

	private JSFormatter visitVariableDeclarations(
			final List<JSVariableDeclaration> variableDeclarations)
			throws IOException {
		f.keyword("var");

		for (int index = 0; index < variableDeclarations.size(); index++) {
			if (index > 0) {
				f.comma();
			}
			final JSVariableDeclaration variableDeclaration = variableDeclarations
					.get(index);
			f.identifier(variableDeclaration.getVariable().getName());
			if (variableDeclaration.getExpression() != null) {
				f.operator(AssignmentOperator.ASSIGN);
				variableDeclaration.getExpression().acceptExpressionVisitor(
						new ExpressionWriterVisitor(f));
			}

		}
		f.endStatement();
		return f;
	}

	@Override
	public JSFormatter visitWhile(JSWhileStatement value) throws IOException {

		f.keyword("while");
		f.openRoundBracket();
		value.getExpression().acceptExpressionVisitor(expressionVisitor());
		f.closeRoundBracket();
		value.getStatement().acceptStatementVisitor(indentedStatementVisitor());

		return f;
	}

	@Override
	public JSFormatter visitWith(JSWithStatement value) throws IOException {
		f.keyword("with");
		f.openRoundBracket();
		value.getWith().acceptExpressionVisitor(expressionVisitor());
		f.closeRoundBracket();
		value.getStatement().acceptStatementVisitor(indentedStatementVisitor());

		return f;
	}

	private ExpressionWriterVisitor expressionVisitor() {
		return new ExpressionWriterVisitor(f);
	}

	private JSStatementVisitor<JSFormatter, IOException> indentedStatementVisitor() {
		return new StatementVisitorWrapper<JSFormatter, IOException>(
				new StatementWriterVisitor(f.indented())) {

			public JSFormatter visitBlock(JSBlock value) throws IOException {
				return StatementWriterVisitor.this.visitBlock(value);
			}

			public JSFormatter visitStatement(JSStatement statement)
					throws IOException {
//				f.lineBreak();
				return super.visitStatement(statement);
				//.lineBreak();
			}
		};
	}

	private JSStatementVisitor<JSFormatter, IOException> indentedBlockStatementVisitor() {
		return new StatementVisitorWrapper<JSFormatter, IOException>(
				new StatementWriterVisitor(f.indented())) {

//			public JSFormatter visitBlock(JSBlock value) throws IOException {
//				return StatementWriterVisitor.this.visitBlock(value);
//			}
//
			public JSFormatter visitStatement(JSStatement statement)
					throws IOException {
				return super.visitStatement(statement);
				//.lineBreak();
//				f.lineBreak();
//				return r;
			}
		};
	}

}
