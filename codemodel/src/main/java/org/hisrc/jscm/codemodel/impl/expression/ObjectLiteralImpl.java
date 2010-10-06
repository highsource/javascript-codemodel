package org.hisrc.jscm.codemodel.impl.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.impl.IdentifierNameImpl;

public class ObjectLiteralImpl extends PrimaryExpressionImpl implements
		JSObjectLiteral {

	private final List<JSPropertyAssignment> propertyAssignments;

	public ObjectLiteralImpl(JSCodeModel codeModel,
			List<JSPropertyAssignment> propertyAssignments) {
		super(codeModel);
		Validate.noNullElements(propertyAssignments);
		this.propertyAssignments = Collections
				.unmodifiableList(propertyAssignments);
	}

	public List<JSPropertyAssignment> getPropertyAssignments() {
		return propertyAssignments;
	}

	public JSObjectLiteral append(String name, JSAssignmentExpression expression) {
		return append(new IdentifierNameImpl(getCodeModel(), name), expression);
	}

	public JSObjectLiteral append(JSPropertyName name,
			JSAssignmentExpression value) {

		final List<JSPropertyAssignment> newPropertyAssignments = new ArrayList<JSPropertyAssignment>(
				this.propertyAssignments.size() + 1);

		newPropertyAssignments.addAll(this.propertyAssignments);
		newPropertyAssignments.add(new PropertyAssignmentImpl(name, value));
		return new ObjectLiteralImpl(getCodeModel(), newPropertyAssignments);
	}

	@Override
	public <V, E extends Exception> V acceptExpressionVisitor(
			JSExpressionVisitor<V, E> visitor) throws E {
		return visitor.visitObjectLiteral(this);
	}

	public static class PropertyAssignmentImpl implements JSPropertyAssignment {
		private final JSPropertyName name;
		private final JSAssignmentExpression value;

		public PropertyAssignmentImpl(JSPropertyName name,
				JSAssignmentExpression value) {
			Validate.notNull(name);
			Validate.notNull(value);
			this.name = name;
			this.value = value;
		}

		@Override
		public JSPropertyName getKey() {
			return name;
		}

		public JSAssignmentExpression getValue() {
			return value;
		}

		@Override
		public JSAssignmentExpression setValue(JSAssignmentExpression value) {
			throw new UnsupportedOperationException();
		}
	}

}
