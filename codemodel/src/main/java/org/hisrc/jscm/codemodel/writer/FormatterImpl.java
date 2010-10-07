package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.formatter.impl.operator.FormatOperatorVisitor;
import org.hisrc.jscm.codemodel.operator.JSOperator;

public class FormatterImpl implements JSFormatter {

	public static final String INDENTATION = "  ";

	private final IndentedAppendable writer;

	public FormatterImpl(Appendable writer) {
		this(new IndentedAppendableImpl(writer));
	}

	private FormatterImpl(IndentedAppendable writer) {
		Validate.notNull(writer);
		this.writer = writer;
	}

	@Override
	public JSFormatter identifier(String identifier) throws IOException {
		Validate.notNull(identifier);
		writer.append(identifier);
		return this;
	}

	public JSFormatter keyword(String keyword) throws IOException {
		Validate.notNull(keyword);
		writer.whiteSpace().append(keyword).whiteSpace();
		return this;
	}

	public JSFormatter operator(JSOperator operator) throws IOException {
		Validate.notNull(operator);
		operator.acceptOperatorVisitor(new FormatOperatorVisitor(writer));
		return this;
	}

	@Override
	public JSFormatter _null() throws IOException {
		writer.append("null");
		return this;
	}

	@Override
	public JSFormatter string(String value) throws IOException {
		// TODO
		writer.append('\"').append(value).append('\"');
		return this;
	}

	@Override
	public JSFormatter _boolean(boolean value) throws IOException {
		writer.append(Boolean.toString(value));
		return this;
	}

	@Override
	public JSFormatter decimal(BigDecimal value) throws IOException {
		Validate.notNull(value);
		writer.append(value.toString());
		return this;
	}

	@Override
	public JSFormatter indented() {
		return new FormatterImpl(writer.indent(INDENTATION));
	}

	@Override
	public JSFormatter lineBreak() throws IOException {
		writer.lineTerminator();
		return this;
	}

	@Override
	public JSFormatter comma() throws IOException {
		writer.append(',');
		return this;
	}

	@Override
	public JSFormatter colon() throws IOException {
		writer.append(':');
		return this;
	}

	@Override
	public JSFormatter semicolon() throws IOException {
		writer.append(';');
		return this;
	}

	@Override
	public JSFormatter endStatement() throws IOException {
		return semicolon().lineBreak();
	}

	@Override
	public JSFormatter dot() throws IOException {
		writer.append('.');
		return this;
	}

	@Override
	public JSFormatter questionMark() throws IOException {
		writer.append('?');
		return this;
	}

	@Override
	public JSFormatter openCurlyBracket() throws IOException {
		writer.append('{');
		return this;
	}

	@Override
	public JSFormatter closeCurlyBracket() throws IOException {
		writer.append('}');
		return this;
	}

	@Override
	public JSFormatter startBlock() throws IOException {
		return openCurlyBracket().lineBreak();
	}

	@Override
	public JSFormatter endBlock() throws IOException {
		return closeCurlyBracket().lineBreak();
	}

	@Override
	public JSFormatter openRoundBracket() throws IOException {
		writer.append('(');
		return this;
	}

	@Override
	public JSFormatter closeRoundBracket() throws IOException {
		writer.append(')');
		return this;
	}

	@Override
	public JSFormatter openSquareBracket() throws IOException {
		writer.append('[');
		return this;
	}

	@Override
	public JSFormatter closeSquareBracket() throws IOException {
		writer.append(']');
		return this;
	}

	@Override
	public JSFormatter args(List<JSAssignmentExpression> expressions)
			throws IOException {
		// writer.whiteSpace();
		writer.append('(');
		// writer.whiteSpace();

		if (expressions.isEmpty()) {
			// writer.whiteSpace();
		}

		for (int index = 0; index < expressions.size(); index++) {
			if (index > 0) {
				// writer.whiteSpace();
				writer.append(',');
				writer.whiteSpace();

			}
			JSExpression expression = expressions.get(index);
			expression(expression);
		}

		// writer.whiteSpace();
		writer.append(')');

		return this;
	}

	public JSFormatter expression(JSExpression expression) throws IOException {
		expression.acceptExpressionVisitor(new ExpressionWriterVisitor(this));
		return this;
	}
}
