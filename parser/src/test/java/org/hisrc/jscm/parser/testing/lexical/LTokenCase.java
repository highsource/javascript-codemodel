package org.hisrc.jscm.parser.testing.lexical;

import java.util.LinkedList;
import java.util.List;

import org.hisrc.jscm.codemodel.lang.Validate;

public class LTokenCase {

	private final String input;
	private final List<LToken> expectedTokens = new LinkedList<LToken>();

	public LTokenCase(String input, List<LToken> expectedTokens) {
		Validate.notNull(input);
		Validate.noNullElements(expectedTokens);
		this.input = input;
		this.expectedTokens.addAll(expectedTokens);
	}

	public String getInput() {
		return input;
	}

	public List<LToken> getExpectedTokens() {
		return expectedTokens;
	}

	@Override
	public String toString() {
		return "LTokenCase [input=" + input + ", expectedTokens="
				+ expectedTokens + "]";
	}

}
