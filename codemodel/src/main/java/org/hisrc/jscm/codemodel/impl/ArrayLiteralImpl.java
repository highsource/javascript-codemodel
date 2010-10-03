package org.hisrc.jscm.codemodel.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSArrayLiteral;
import org.hisrc.jscm.codemodel.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;

public class ArrayLiteralImpl extends PrimaryExpressionImpl implements
		JSArrayLiteral {

	private final List<JSAssignmentExpression> elements;

	public ArrayLiteralImpl(JSCodeModel codeModel,
			List<JSAssignmentExpression> elements) {
		super(codeModel);
		Validate.noNullElements(elements);
		this.elements = Collections.unmodifiableList(elements);
	}

	public List<JSAssignmentExpression> getElements() {
		return elements;
	}

	@Override
	public JSArrayLiteral add(JSAssignmentExpression... elements) {
		Validate.noNullElements(elements);
		final List<JSAssignmentExpression> newElements = new ArrayList<JSAssignmentExpression>(
				this.elements.size() + elements.length);
		newElements.addAll(this.elements);
		for (JSAssignmentExpression element : elements) {
			newElements.add(element);
		}
		return new ArrayLiteralImpl(getCodeModel(), newElements);
	}

	@Override
	public <V, E extends Exception> V accept(JSExpressionVisitor<V, E> visitor)
			throws E {
		return visitor.visitArrayLiteral(this);
	}
}
