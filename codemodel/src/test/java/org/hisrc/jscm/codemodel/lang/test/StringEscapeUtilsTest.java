package org.hisrc.jscm.codemodel.lang.test;

import org.hisrc.jscm.codemodel.lang.StringEscapeUtils;
import org.junit.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class StringEscapeUtilsTest {

	@DataPoints
	public static String[][] DATA = {
			//
			{ null, null },
			//
			{ "", "" },
			//
			{ "abcdABCD", "abcdABCD" },
			//
			{ "\\t \\b \\n \\r \\f \\' \\\\ \\/", "\t \b \n \r \f \' \\ /" }

	};

	@Theory
	public void escapesCorrectly(String[] data) {
		Assert.assertEquals(data[0],
				StringEscapeUtils.escapeEcmaScript(data[1]));

	}
}
