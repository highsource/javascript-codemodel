package org.hisrc.jscm.codemodel.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.JSCallExpression;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSExpression;
import org.hisrc.jscm.codemodel.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.JSMemberExpression;
import org.hisrc.jscm.codemodel.JSPropertyName;

public abstract class MemberExpressionImpl extends NewExpressionImpl implements
		JSMemberExpression {

	public MemberExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	public JSCallExpression.MemberCall call(JSAssignmentExpression... args) {
		return new CallExpressionImpl.MemberCallImpl(getCodeModel(), this, args);
	}

	@Override
	public MemberElement element(JSExpression index) {
		return new MemberElementImpl(getCodeModel(), this, index);
	}

	@Override
	public MemberProperty property(JSPropertyName name) {
		return new MemberPropertyImpl(getCodeModel(), this, name);
	}

	@Override
	public MemberNew _new(JSAssignmentExpression... args) {
		return new MemberNewImpl(getCodeModel(), this, args);
	}

	public static abstract class MemberImpl extends MemberExpressionImpl
			implements Member {

		private final JSMemberExpression base;

		public MemberImpl(JSCodeModel codeModel, JSMemberExpression base) {
			super(codeModel);
			Validate.notNull(base);
			this.base = base;
		}

		public JSMemberExpression getBase() {
			return base;
		}
	}

	public static class MemberElementImpl extends MemberImpl implements
			MemberElement {
		private final JSExpression index;

		public MemberElementImpl(JSCodeModel codeModel,
				JSMemberExpression base, JSExpression index) {
			super(codeModel, base);
			Validate.notNull(index);
			this.index = index;
		}

		public JSExpression getIndex() {
			return index;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitMemberElement(this);
		}
	}

	public static class MemberPropertyImpl extends MemberImpl implements
			MemberProperty {
		private final JSPropertyName name;

		public MemberPropertyImpl(JSCodeModel codeModel,
				JSMemberExpression base, JSPropertyName name) {
			super(codeModel, base);
			Validate.notNull(name);
			this.name = name;
		}

		public JSPropertyName getName() {
			return name;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitMemberProperty(this);
		}
	}

	public static class MemberNewImpl extends MemberImpl implements MemberNew {
		private final List<JSAssignmentExpression> args;

		public MemberNewImpl(JSCodeModel codeModel, JSMemberExpression base,
				JSAssignmentExpression... args) {
			super(codeModel, base);
			Validate.noNullElements(args);
			this.args = Collections.unmodifiableList(Arrays.asList(args));
		}

		public List<JSAssignmentExpression> getArgs() {
			return args;
		}

		@Override
		public <V, E extends Exception> V accept(
				JSExpressionVisitor<V, E> visitor) throws E {
			return visitor.visitMemberNew(this);
		}
	}

}
