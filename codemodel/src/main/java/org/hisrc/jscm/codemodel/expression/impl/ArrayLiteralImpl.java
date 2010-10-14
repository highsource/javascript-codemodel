package org.hisrc.jscm.codemodel.expression.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.lang.Validate;

public class ArrayLiteralImpl extends PrimaryExpressionImpl implements
		JSArrayLiteral {

	private final List<JSAssignmentExpression> elements = new ArrayList<JSAssignmentExpression>();
	private final List<JSAssignmentExpression> unmodifiableElements = Collections
			.unmodifiableList(elements);

	public ArrayLiteralImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public List<JSAssignmentExpression> getElements() {
		return unmodifiableElements;
	}

	@Override
	public JSArrayLiteral append(JSAssignmentExpression... elements) {
		Validate.noNullElements(elements);
		this.elements.addAll(Arrays.asList(elements));
		return this;
	}

	@Override
	public <V, E extends Exception> V acceptExpressionVisitor(
			JSExpressionVisitor<V, E> visitor) throws E {
		return visitor.visitArrayLiteral(this);
	}
}
