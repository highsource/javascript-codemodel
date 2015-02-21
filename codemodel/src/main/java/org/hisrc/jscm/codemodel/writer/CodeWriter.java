package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.io.DefaultIndentedAppendable;
import org.hisrc.jscm.codemodel.io.IndentedAppendable;
import org.hisrc.jscm.codemodel.lang.StringEscapeUtils;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.literal.JSLiteral;
import org.hisrc.jscm.codemodel.operator.JSOperator;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.impl.AbstractStatementVisitor;

public class CodeWriter {

	public static final String INDENTATION = "  ";

	protected final IndentedAppendable writer;

	public CodeWriter(Appendable writer) {
		this(new DefaultIndentedAppendable(writer));
	}

	private CodeWriter(IndentedAppendable writer) {
		Validate.notNull(writer);
		this.writer = writer;
	}

	protected ExpressionWriter createExpressionWriter() {
		return new ExpressionWriter(this);
	}

	public CodeWriter identifier(String identifier) throws IOException {
		Validate.notNull(identifier);
		writer.append(StringEscapeUtils.escapeEcmaScript(identifier));
		return this;
	}

	public CodeWriter keyword(String keyword) throws IOException {
		Validate.notNull(keyword);
		writer.append(keyword);
		return this;
	}

	public CodeWriter operator(JSOperator operator) throws IOException {
		Validate.notNull(operator);
		operator.acceptOperatorVisitor(new OperatorWriter(writer));
		return this;
	}

	public CodeWriter _null() throws IOException {
		writer.append("null");
		return this;
	}

	public CodeWriter string(String value) throws IOException {
		writer.append('\'').append(StringEscapeUtils.escapeEcmaScript(value))
				.append('\'');
		return this;
	}

	public CodeWriter _boolean(boolean value) throws IOException {
		writer.append(Boolean.toString(value));
		return this;
	}

	public CodeWriter decimal(BigDecimal value) throws IOException {
		Validate.notNull(value);
		writer.append(value.toString());
		return this;
	}

	public CodeWriter hexInteger(BigInteger value) throws IOException {
		Validate.notNull(value);
		writer.append("0x");
		writer.append(value.toString(16));
		return this;
	}

	public CodeWriter octalInteger(BigInteger value) throws IOException {
		Validate.notNull(value);
		writer.append("0");
		writer.append(value.toString(8));
		return this;
	}

	public CodeWriter indented() {
		return new CodeWriter(writer.indent(INDENTATION));
	}

	public CodeWriter lineTerminator() throws IOException {
		writer.lineTerminator();
		return this;
	}

	public CodeWriter comma() throws IOException {
		writer.append(',');
		return this;
	}

	public CodeWriter colon() throws IOException {
		writer.append(':');
		return this;
	}

	public CodeWriter semicolon() throws IOException {
		writer.append(';');
		return this;
	}

	public CodeWriter dot() throws IOException {
		writer.append('.');
		return this;
	}

	public CodeWriter questionMark() throws IOException {
		writer.append('?');
		return this;
	}

	public CodeWriter openCurlyBracket() throws IOException {
		writer.append('{');
		return this;
	}

	public CodeWriter closeCurlyBracket() throws IOException {
		writer.append('}');
		return this;
	}

	public CodeWriter openRoundBracket() throws IOException {
		writer.append('(');
		return this;
	}

	public CodeWriter closeRoundBracket() throws IOException {
		writer.append(')');
		return this;
	}

	public CodeWriter openSquareBracket() throws IOException {
		writer.append('[');
		return this;
	}

	public CodeWriter closeSquareBracket() throws IOException {
		writer.append(']');
		return this;
	}

	public CodeWriter inlineComment(String comment) throws IOException {
		writer.append("/*");
		// TODO Check
		writer.append(comment);
		writer.append("*/");
		return this;
	}

	public CodeWriter expression(JSExpression expression) throws IOException {
		expression.acceptExpressionVisitor(createExpressionWriter());
		return this;
	}

	public CodeWriter statement(JSStatement statement) throws IOException {
		statement.acceptStatementVisitor(new StatementWriter(this));
		return this;
	}

	public CodeWriter block(JSStatement statement) throws IOException {
		final CodeWriter f = this;
		final CodeWriter fi = f.indented();
		return statement
				.acceptStatementVisitor(new AbstractStatementVisitor<CodeWriter, IOException>() {

					@Override
					public CodeWriter visitBlock(JSBlock block)
							throws IOException {
						return f.statement(block);
					}

					@Override
					public CodeWriter visitStatement(JSStatement statement)
							throws IOException {
						return fi.lineTerminator().statement(statement);
					}
				});
	}

	public CodeWriter whiteSpace() throws IOException {
		writer.whiteSpace();
		return this;
	}

	public CodeWriter sourceElement(JSSourceElement sourceElement)
			throws IOException {
		return sourceElement
				.acceptSourceElementVisitor(new SourceElementWriter(this));
	}

	public CodeWriter literal(JSLiteral literal) throws IOException {
		return literal.acceptLiteralVisitor(createLiteralWriter());
	}

	protected LiteralWriter createLiteralWriter() {
		return new LiteralWriter(this);
	}

	public CodeWriter propertyName(JSPropertyName propertyName)
			throws IOException {
		return propertyName.acceptPropertyNameVisitor(new PropertyNameWriter(
				this));
	}

	public CodeWriter program(JSProgram program) throws IOException {
		Validate.notNull(program);
		return program.acceptProgramVisitor(new ProgramWriter(this));
	}

	public CodeWriter regularExpression(String body, String flags)
			throws IOException {
		Validate.notNull(body);
		writer.append('/').append(body).append('/');
		if (flags != null) {
			writer.append(flags);
		}
		return this;
	}
}
