package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.JSOperator;

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
		writer.append(keyword);
		return this;
	}

	public JSFormatter operator(JSOperator operator) throws IOException {
		Validate.notNull(operator);
		writer.append(operator.asString());
		return this;
	}

	@Override
	public JSFormatter _null() throws IOException {
		writer.append("null");
		return this;
	}
	
	@Override
	public JSFormatter string(String value) throws IOException {
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
		writer.append('\n');
		return this;
	}

	@Override
	public JSFormatter comma() throws IOException {
		writer.append(",");
		return this;
	}

	@Override
	public JSFormatter colon() throws IOException {
		writer.append(":");
		return this;
	}

	@Override
	public JSFormatter semicolon() throws IOException {
		writer.append(";");
		return this;
	}

	@Override
	public JSFormatter dot() throws IOException {
		writer.append(".");
		return this;
	}

	@Override
	public JSFormatter questionMark() throws IOException {
		writer.append("?");
		return this;
	}

	@Override
	public JSFormatter openCurlyBracket() throws IOException {
		writer.append("{");
		return this;
	}

	@Override
	public JSFormatter closeCurlyBracket() throws IOException {
		writer.append("}");
		return this;
	}

	@Override
	public JSFormatter openRoundBracket() throws IOException {
		writer.append("(");
		return this;
	}

	@Override
	public JSFormatter closeRoundBracket() throws IOException {
		writer.append(")");
		return this;
	}

	@Override
	public JSFormatter openSquareBracket() throws IOException {
		writer.append("[");
		return this;
	}

	@Override
	public JSFormatter closeSquareBracket() throws IOException {
		writer.append("]");
		return this;
	}

}
