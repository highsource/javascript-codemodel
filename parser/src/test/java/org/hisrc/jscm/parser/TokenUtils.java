package org.hisrc.jscm.parser;

public class TokenUtils {

	private TokenUtils() {
	}

	public static boolean precededBySpecialTokenOfKind(Token token, int kind) {
		for (Token specialToken = token.specialToken; specialToken != null; specialToken = specialToken.specialToken) {
			if (specialToken.kind == kind) {
				return true;
			}
		}
		return false;
	}
}
