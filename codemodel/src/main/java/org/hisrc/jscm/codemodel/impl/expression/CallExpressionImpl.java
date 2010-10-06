package org.hisrc.jscm.codemodel.impl.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSCallExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression;
import org.hisrc.jscm.codemodel.impl.IdentifierNameImpl;

public abstract class CallExpressionImpl extends LeftHandSideExpressionImpl
		implements JSCallExpression {

	public CallExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public CallArgs invoke() {
		return new CallArgsImpl(getCodeModel(), this);
	}

	@Override
	public CallElement element(JSExpression index) {
		return new CallElementImpl(getCodeModel(), this, index);
	}

	@Override
	public CallProperty property(String name) {
		return new CallPropertyImpl(getCodeModel(), this, new IdentifierNameImpl(getCodeModel(), name));
	}

	@Override
	public CallProperty property(JSPropertyName name) {
		return new CallPropertyImpl(getCodeModel(), this, name);
	}

	public static abstract class CallImpl extends CallExpressionImpl implements
			Call {

		private final JSCallExpression base;

		public CallImpl(JSCodeModel codeModel, JSCallExpression base) {
			super(codeModel);
			this.base = base;
			Validate.notNull(base);
		}

		public JSCallExpression getBase() {
			return base;
		}
	}

	public static class CallArgsImpl extends CallImpl implements CallArgs {
		private final List<JSAssignmentExpression> args = new ArrayList<JSAssignmentExpression>();
		private final List<JSAssignmentExpression> unmodifiableArgs = Collections
				.unmodifiableList(args);

		public CallArgsImpl(JSCodeModel codeModel, JSCallExpression base) {
			super(codeModel, base);
		}
		
		public CallArgs args(JSAssignmentExpression... args) {
			Validate.noNullElements(args);
			this.args.addAll(Arrays.asList(args));
			return this;
		}


		public List<JSAssignmentExpression> getArgs() {
			return unmodifiableArgs;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitCallArgs(this);
		}
	}

	public static class CallElementImpl extends CallImpl implements CallElement {
		private final JSExpression index;

		public CallElementImpl(JSCodeModel codeModel, JSCallExpression base,
				JSExpression index) {
			super(codeModel, base);
			Validate.notNull(index);
			this.index = index;
		}

		public JSExpression getIndex() {
			return index;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitCallElement(this);
		}
	}

	public static class CallPropertyImpl extends CallImpl implements
			CallProperty {
		private final JSPropertyName name;

		public CallPropertyImpl(JSCodeModel codeModel, JSCallExpression base,
				JSPropertyName name) {
			super(codeModel, base);
			Validate.notNull(name);
			this.name = name;
		}

		public JSPropertyName getName() {
			return name;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitCallProperty(this);
		}
	}

	public static class MemberCallImpl extends CallExpressionImpl implements
			JSCallExpression.MemberCall {
		private final List<JSAssignmentExpression> args = new ArrayList<JSAssignmentExpression>();
		private final List<JSAssignmentExpression> unmodifiableArgs = Collections
				.unmodifiableList(args);
		private final JSMemberExpression base;

		public MemberCallImpl(JSCodeModel codeModel, JSMemberExpression base) {
			super(codeModel);
			Validate.notNull(base);
			this.base = base;
		}

		public MemberCall args(JSAssignmentExpression... args) {
			Validate.noNullElements(args);
			this.args.addAll(Arrays.asList(args));
			return this;
		}

		public JSMemberExpression getBase() {
			return base;
		}

		public List<JSAssignmentExpression> getArgs() {
			return unmodifiableArgs;
		}

		@Override
		public <V, E extends Exception> V acceptExpressionVisitor(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitMemberCall(this);
		}
	}

}
