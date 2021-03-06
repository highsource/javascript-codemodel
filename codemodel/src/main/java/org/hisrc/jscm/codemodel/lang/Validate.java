package org.hisrc.jscm.codemodel.lang;

import java.util.List;

public class Validate {

	private Validate() {
	}

	public static void notNull(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("The argument must not be null.");
		}
	}

	public static <T> T[] noNullElements(T[] array) {
		Validate.notNull(array);
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				throw new IllegalArgumentException(
						"The array must not contain null elements. Element with index ["
								+ i + "] is null.");
			}
		}
		return array;
	}

	public static <T> List<T> noNullElements(List<T> list) {
		Validate.notNull(list);
		int i = 0;
		for (T item : list) {
			if (item == null) {
				throw new IllegalArgumentException(
						"The array must not contain null elements. Element with index ["
								+ i + "] is null.");
			}
			i++;
		}
		return list;
	}
}
