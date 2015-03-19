package org.hisrc.jscm.parser;

import org.apache.commons.lang3.StringUtils;

public class TokenUtils {

	private TokenUtils() {
	}

	public static boolean precededByLineTerminator(Token token) {
		for (Token specialToken = token.specialToken; specialToken != null; specialToken = specialToken.specialToken) {
			if (specialToken.kind == EcmaScriptParserConstants.LINE_TERMINATOR) {
				return true;
			} else if (specialToken.kind == EcmaScriptParserConstants.MULTI_LINE_COMMENT) {
				final String image = specialToken.image;
				if (StringUtils.containsAny(image, (char)0x000A, (char)0x000D, (char)0x2028,
						(char)0x2029)) {
					return true;
				}
			}
		}
		return false;
	}
}
