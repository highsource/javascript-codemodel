package org.hisrc.jscm.codemodel.lang;

import java.util.Locale;

public class StringEscapeUtils {

	public static final String escapeEcmaScript(String string) {
		if (string == null) {
			return null;
		} else if (string.length() == 0) {
			return string;
		} else {
			final StringBuilder sb = new StringBuilder();

			for (int index = 0; index < string.length(); index++) {
				final char character = string.charAt(index);
				final int codePoint = string.codePointAt(index);

				switch (character) {
				case '\t':
					sb.append("\\t");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\'':
					sb.append("\\'");
					break;
				case '\"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '/':
					sb.append("\\/");
					break;
				default:
					if (codePoint < 0x20 || codePoint > 0x7f) {

						if (codePoint > 0xffff) {
							sb.append("\\u");
						} else if (codePoint > 0xfff) {
							sb.append("\\u");
						} else if (codePoint > 0xff) {
							sb.append("\\u0");
						} else if (codePoint > 0xf) {
							sb.append("\\u00");
						} else {
							sb.append("\\u000");
						}
						sb.append(Integer.toHexString(codePoint).toUpperCase(
								Locale.ENGLISH));
					} else {
						sb.append(character);
					}
				}

			}
			return sb.toString();
		}
	}

}
