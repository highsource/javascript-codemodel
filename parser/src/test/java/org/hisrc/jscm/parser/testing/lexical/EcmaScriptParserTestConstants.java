package org.hisrc.jscm.parser.testing.lexical;

import org.hisrc.jscm.parser.EcmaScriptParserConstants;

public class EcmaScriptParserTestConstants {

	public static final LTokenFactory ECMASCRIPT_PARSER_TOKEN_FACTORY = new LTokenFactory(
	EcmaScriptParserConstants.class, "DEFAULT", "IN_REGEX",
	"IN_MULTI_LINE_COMMENT", "IN_SINGLE_LINE_COMMENT", "IN_PATTERN");

}
