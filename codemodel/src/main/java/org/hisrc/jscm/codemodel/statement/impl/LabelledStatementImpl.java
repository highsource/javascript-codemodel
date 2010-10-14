package org.hisrc.jscm.codemodel.statement.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSSourceElementVisitor;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class LabelledStatementImpl extends StatementGeneratorImpl implements
		JSLabelledStatement {

	private final JSLabel label;

	private JSStatement statement;

	public LabelledStatementImpl(JSCodeModel codeModel, String label) {
		super(codeModel);
		Validate.notNull(label);
		this.label = new LabelImpl(label);
		this.statement = new EmptyStatementImpl(codeModel);

	}

	protected <S extends JSStatement> S add(S statement) {
		Validate.notNull(statement);
		this.statement = statement;
		return statement;
	}

	public JSLabel getLabel() {
		return label;
	}

	@Override
	public JSStatement getStatement() {
		return statement;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitLabelled(this);
	}

	public <V, E extends Exception> V acceptSourceElementVisitor(
			JSSourceElementVisitor<V, E> visitor) throws E {
		return visitor.visitStatement(this);
	}

	private class LabelImpl implements JSLabel {
		private final String name;

		public LabelImpl(String name) {
			Validate.notNull(name);
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
