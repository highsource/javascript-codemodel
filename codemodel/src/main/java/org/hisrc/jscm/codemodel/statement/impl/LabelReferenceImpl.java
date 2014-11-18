package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSLabelReference;

public class LabelReferenceImpl implements JSLabelReference {

	private final String name;

	public LabelReferenceImpl(JSCodeModel codeModel, String name) {
		Validate.notNull(name);
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
