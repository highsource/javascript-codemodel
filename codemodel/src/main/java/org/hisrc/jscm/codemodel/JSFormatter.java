package org.hisrc.jscm.codemodel;

import java.io.IOException;
import java.math.BigDecimal;

public interface JSFormatter {

	public JSFormatter indented();

	public JSFormatter identifier(String identifier) throws IOException;

	public JSFormatter keyword(String keyword) throws IOException;

	public JSFormatter operator(JSOperator operator) throws IOException;

	public JSFormatter _null() throws IOException;

	public JSFormatter string(String value) throws IOException;

	public JSFormatter _boolean(boolean value) throws IOException;

	public JSFormatter decimal(BigDecimal value) throws IOException;

	public JSFormatter lineBreak() throws IOException;

	public JSFormatter comma() throws IOException;

	public JSFormatter colon() throws IOException;

	public JSFormatter semicolon() throws IOException;

	public JSFormatter questionMark() throws IOException;

	public JSFormatter dot() throws IOException;

	public JSFormatter openSquareBracket() throws IOException;

	public JSFormatter closeSquareBracket() throws IOException;

	public JSFormatter openCurlyBracket() throws IOException;

	public JSFormatter closeCurlyBracket() throws IOException;

	public JSFormatter openRoundBracket() throws IOException;

	public JSFormatter closeRoundBracket() throws IOException;

}
