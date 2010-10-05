package org.hisrc.jscm.codemodel.impl.statement;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSIdentifier;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementGenerator;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;

public class LabelledStatementImpl extends StatementGeneratorImpl implements JSLabelledStatement{

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
	
	public JSIdentifier getLabel() {
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
	
	private class LabelImpl implements JSLabel
	{
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
