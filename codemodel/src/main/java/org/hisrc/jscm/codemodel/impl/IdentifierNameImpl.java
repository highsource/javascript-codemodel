package org.hisrc.jscm.codemodel.impl;

import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.JSPropertyNameVisitor;
import org.hisrc.jscm.codemodel.lang.Validate;

public class IdentifierNameImpl implements JSIdentifierName {

	private final String name;

	public IdentifierNameImpl(String name) {
		super();
		Validate.notNull(name);
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public <V, E extends Exception> V acceptPropertyNameVisitor(
			JSPropertyNameVisitor<V, E> visitor) throws E {
		return visitor.visitIdentifierName(this);
	}

}
