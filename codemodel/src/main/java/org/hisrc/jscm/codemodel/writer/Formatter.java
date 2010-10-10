package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.formatter.OperatorFormatter;
import org.hisrc.jscm.codemodel.io.DefaultIndentedAppendable;
import org.hisrc.jscm.codemodel.io.IndentedAppendable;
import org.hisrc.jscm.codemodel.literal.JSLiteral;
import org.hisrc.jscm.codemodel.operator.JSOperator;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.impl.AbstractStatementVisitor;

public class Formatter {

	public static final String INDENTATION = "  ";

	private final IndentedAppendable writer;

	public Formatter(Appendable writer) {
		this(new DefaultIndentedAppendable(writer));
	}

	private Formatter(IndentedAppendable writer) {
		Validate.notNull(writer);
		this.writer = writer;
	}

	public Formatter identifier(String identifier) throws IOException {
		Validate.notNull(identifier);
		writer.append(identifier);
		return this;
	}

	public Formatter keyword(String keyword) throws IOException {
		Validate.notNull(keyword);
		writer.append(keyword);
		return this;
	}

	public Formatter operator(JSOperator operator) throws IOException {
		Validate.notNull(operator);
		operator.acceptOperatorVisitor(new OperatorFormatter(writer));
		return this;
	}

	public Formatter _null() throws IOException {
		writer.append("null");
		return this;
	}

	public Formatter string(String value) throws IOException {
		// TODO
		writer.append('\"').append(value).append('\"');
		return this;
	}

	public Formatter _boolean(boolean value) throws IOException {
		writer.append(Boolean.toString(value));
		return this;
	}

	public Formatter decimal(BigDecimal value) throws IOException {
		Validate.notNull(value);
		writer.append(value.toString());
		return this;
	}

	public Formatter indented() {
		return new Formatter(writer.indent(INDENTATION));
	}

	public Formatter lineTerminator() throws IOException {
		writer.lineTerminator();
		return this;
	}

	public Formatter comma() throws IOException {
		writer.append(',');
		return this;
	}

	public Formatter colon() throws IOException {
		writer.append(':');
		return this;
	}

	public Formatter semicolon() throws IOException {
		writer.append(';');
		return this;
	}

	public Formatter dot() throws IOException {
		writer.append('.');
		return this;
	}

	public Formatter questionMark() throws IOException {
		writer.append('?');
		return this;
	}

	public Formatter openCurlyBracket() throws IOException {
		writer.append('{');
		return this;
	}

	public Formatter closeCurlyBracket() throws IOException {
		writer.append('}');
		return this;
	}

	public Formatter openRoundBracket() throws IOException {
		writer.append('(');
		return this;
	}

	public Formatter closeRoundBracket() throws IOException {
		writer.append(')');
		return this;
	}

	public Formatter openSquareBracket() throws IOException {
		writer.append('[');
		return this;
	}

	public Formatter closeSquareBracket() throws IOException {
		writer.append(']');
		return this;
	}

	public Formatter expression(JSExpression expression) throws IOException {
		expression.acceptExpressionVisitor(new ExpressionWriter(this));
		return this;
	}

	public Formatter statement(JSStatement statement) throws IOException {
		statement.acceptStatementVisitor(new StatementWriter(this));
		return this;
	}

	public Formatter block(JSStatement statement) throws IOException {
		final Formatter f = this;
		final Formatter fi = f.indented();
		return statement
				.acceptStatementVisitor(new AbstractStatementVisitor<Formatter, IOException>() {

					@Override
					public Formatter visitBlock(JSBlock block)
							throws IOException {
						return f.statement(block);
					}

					@Override
					public Formatter visitStatement(JSStatement statement)
							throws IOException {
						return fi.lineTerminator().statement(statement);
					}
				});
	}

	public Formatter whiteSpace() throws IOException {
		writer.whiteSpace();
		return this;
	}

	public Formatter sourceElement(JSSourceElement sourceElement)
			throws IOException {
		return sourceElement
				.acceptSourceElementVisitor(new SourceElementWriter(this));
	}

	public Formatter literal(JSLiteral literal) throws IOException {
		return literal.acceptLiteralVisitor(new LiteralWriter(this));
	}

	public Formatter propertyName(JSPropertyName propertyName)
			throws IOException {
		return propertyName.acceptPropertyNameVisitor(new PropertyNameWriter(
				this));
	}
}
