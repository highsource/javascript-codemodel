package org.hisrc.jscm.parser.tests;

import org.hisrc.jscm.parser.testing.lexical.AbstractDualTokenTest;

public class CommentsTest extends AbstractDualTokenTest {

	@Override
	protected boolean isInputEscaped() {
		return true;
	}
}
