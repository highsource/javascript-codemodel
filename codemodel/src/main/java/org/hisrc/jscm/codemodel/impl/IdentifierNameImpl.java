package org.hisrc.jscm.codemodel.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;

public class IdentifierNameImpl implements JSIdentifierName {

	private final JSCodeModel codeModel;
	private final String name;

	public IdentifierNameImpl(JSCodeModel codeModel, String name) {
		super();
		Validate.notNull(codeModel);
		Validate.notNull(name);
		this.codeModel = codeModel;
		this.name = name;
	}

	public JSCodeModel getCodeModel() {
		return codeModel;
	}

	public String getName() {
		return name;
	}

	@Override
	public <V, E extends Exception> V acceptPropertyNameVisitor(
			JSPropertyNameVisitor<V, E> visitor) throws E {
		return visitor.visitIdentifierName(this);
	}

}
