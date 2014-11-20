package org.hisrc.jscm.parser.testing.lexical;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hisrc.jscm.codemodel.lang.Validate;

public class LTokenFactory {
	
	private static final int TOKEN_KIND_EOF = 0;

	private static final String TOKEN_IMAGE_FIELD_NAME = "tokenImage";
	private final Map<String, Integer> idToKindMap = new HashMap<String, Integer>();
	private final Map<Integer, String> kindToIdMap = new HashMap<Integer, String>();
	// private final Map<Integer, String> kindToImageMap = new HashMap<Integer,
	// String>();
	// private final Map<String, String> idToImageMap = new HashMap<String,
	// String>();
	private final List<String> lexicalStates;
	private final Set<String> lexicalStatesSet;

	public LTokenFactory(Class<?> constantsClass, String... lexicalStates) {
		Validate.notNull(constantsClass);
		Validate.noNullElements(lexicalStates);
		this.lexicalStates = Arrays.asList(lexicalStates);
		this.lexicalStatesSet = new HashSet<String>(this.lexicalStates);

		try {
			Field tokenImageField = constantsClass
					.getField(TOKEN_IMAGE_FIELD_NAME);
			// String[] tokenImages = (String[]) tokenImageField.get(null);
			Field[] fields = constantsClass.getFields();
			for (Field field : fields) {
				final String fieldName = field.getName();
				if (this.lexicalStatesSet.contains(fieldName)
						|| TOKEN_IMAGE_FIELD_NAME.equals(fieldName)) {
					// Skip the field
				} else {
					final String id = fieldName;
					final int kind = field.getInt(null);
					// final String image = tokenImages[kind];
					// this.idToImageMap.put(id, image);
					this.idToKindMap.put(id, kind);
					this.kindToIdMap.put(kind, id);
					// this.kindToImageMap.put(kind, image);
				}
			}
		} catch (IllegalArgumentException iaex) {
			throw new ExceptionInInitializerError(iaex);
		} catch (IllegalAccessException iaex) {
			throw new ExceptionInInitializerError(iaex);
		} catch (NoSuchFieldException nsfex) {
			throw new ExceptionInInitializerError(nsfex);
		}
	}

	// public LToken createToken(String id) {
	// final Integer kind = this.idToKindMap.get(id);
	// if (kind == null) {
	// throw new IllegalArgumentException(MessageFormat.format(
	// "Unknown token id [{0}].", id));
	// }
	// final String image = this.kindToImageMap.get(kind);
	// return new LToken(id, kind, image);
	// }
	//
	public LToken createToken(String id, String image) {
		final Integer kind = this.idToKindMap.get(id);
		if (kind == null) {
			throw new IllegalArgumentException(MessageFormat.format(
					"Unknown token id [{0}].", id));
		}
		return new LToken(id, kind, (kind == TOKEN_KIND_EOF ? null : image));
	}

	public LToken createToken(int kind, String image) {
		final String id = this.kindToIdMap.get(kind);
		if (id == null) {
			throw new IllegalArgumentException(MessageFormat.format(
					"Unknown token kind [{0}].", kind));
		}
		return new LToken(id, kind, (kind == TOKEN_KIND_EOF ? null : image));
	}

}
