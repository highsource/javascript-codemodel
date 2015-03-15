package org.hisrc.jscm.codemodel.expression.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSPropertyAssignmentVisitor;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.FunctionBodyImpl;
import org.hisrc.jscm.codemodel.impl.IdentifierNameImpl;
import org.hisrc.jscm.codemodel.lang.Validate;

public class ObjectLiteralImpl extends PrimaryExpressionImpl implements
		JSObjectLiteral {

	private final List<JSPropertyAssignment> propertyAssignments = new ArrayList<JSPropertyAssignment>();
	private final List<JSPropertyAssignment> unmodifiablePropertyAssignments = Collections
			.unmodifiableList(propertyAssignments);

	public ObjectLiteralImpl(JSCodeModel codeModel) {
		this(codeModel, new JSPropertyAssignment[0]);
	}

	public ObjectLiteralImpl(JSCodeModel codeModel,
			JSPropertyAssignment[] propertyAssignments) {
		super(codeModel);
		Validate.noNullElements(propertyAssignments);
		this.propertyAssignments.addAll(Arrays.asList(propertyAssignments));
	}

	@Override
	public List<JSPropertyAssignment> getPropertyAssignments() {
		return unmodifiablePropertyAssignments;
	}

	@Override
	public JSObjectLiteral append(String name, JSAssignmentExpression expression) {
		return append(new IdentifierNameImpl(name), expression);
	}

	@Override
	public JSObjectLiteral append(JSPropertyName name,
			JSAssignmentExpression value) {
		appendProperty(name, value);
		return this;
	}

	@Override
	public JSProperty appendProperty(String name,
			JSAssignmentExpression expression) {
		return appendProperty(new IdentifierNameImpl(name), expression);
	}

	@Override
	public JSProperty appendProperty(JSPropertyName name,
			JSAssignmentExpression expression) {
		final JSProperty property = new PropertyImpl(name, expression);
		this.propertyAssignments.add(property);
		return property;
	}

	@Override
	public JSGetter appendGetter(String name) {
		return appendGetter(new IdentifierNameImpl(name));
	}

	@Override
	public JSGetter appendGetter(JSPropertyName name) {
		final JSGetter getter = new GetterImpl(name, new FunctionBodyImpl(
				getCodeModel()));
		this.propertyAssignments.add(getter);
		return getter;
	}

	@Override
	public JSSetter appendSetter(String name, String parameter) {
		return appendSetter(new IdentifierNameImpl(name), parameter);
	}

	@Override
	public JSSetter appendSetter(JSPropertyName name, String parameter) {
		final JSSetter setter = new SetterImpl(name, new VariableImpl(
				getCodeModel(), parameter),
				new FunctionBodyImpl(getCodeModel()));
		this.propertyAssignments.add(setter);
		return setter;
	}

	@Override
	public <V, E extends Exception> V acceptExpressionVisitor(
			JSExpressionVisitor<V, E> visitor) throws E {
		return visitor.visitObjectLiteral(this);
	}

	public abstract static class PropertyAssignmentImpl implements
			JSPropertyAssignment {

		private final JSPropertyName name;

		public PropertyAssignmentImpl(JSPropertyName name) {
			Validate.notNull(name);
			this.name = name;
		}

		@Override
		public JSPropertyName getKey() {
			return name;
		}
	}

	public static class PropertyImpl extends PropertyAssignmentImpl implements
			JSProperty {

		private final JSAssignmentExpression value;

		public PropertyImpl(JSPropertyName name, JSAssignmentExpression value) {
			super(name);
			Validate.notNull(value);
			this.value = value;
		}

		@Override
		public JSAssignmentExpression getValue() {
			return value;
		}

		@Override
		public <V, E extends Exception> V acceptPropertyAssignmentVisitor(
				JSPropertyAssignmentVisitor<V, E> visitor) throws E {
			return visitor.visitProperty(this);
		}
	}

	public static abstract class PropertyAccessorImpl extends
			PropertyAssignmentImpl implements JSPropertyAccessor {

		private JSFunctionBody body;

		public PropertyAccessorImpl(JSPropertyName name, JSFunctionBody body) {
			super(name);
			Validate.notNull(body);
			this.body = body;
		}

		@Override
		public JSFunctionBody getBody() {
			return body;
		}
	}

	public static class GetterImpl extends PropertyAccessorImpl implements
			JSGetter {

		public GetterImpl(JSPropertyName name, JSFunctionBody body) {
			super(name, body);
		}

		@Override
		public <V, E extends Exception> V acceptPropertyAssignmentVisitor(
				JSPropertyAssignmentVisitor<V, E> visitor) throws E {
			return visitor.visitGetter(this);
		}
	}

	public static class SetterImpl extends PropertyAccessorImpl implements
			JSSetter {

		private final JSVariable parameter;

		public SetterImpl(JSPropertyName name, JSVariable parameter,
				JSFunctionBody body) {
			super(name, body);
			Validate.notNull(parameter);
			this.parameter = parameter;
		}

		@Override
		public JSVariable getParameter() {
			return parameter;
		}

		@Override
		public <V, E extends Exception> V acceptPropertyAssignmentVisitor(
				JSPropertyAssignmentVisitor<V, E> visitor) throws E {
			return visitor.visitSetter(this);
		}
	}
}
