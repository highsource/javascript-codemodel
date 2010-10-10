package org.hisrc.jscm.codemodel.statement.impl;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.statement.JSBreakStatement;
import org.hisrc.jscm.codemodel.statement.JSStatementVisitor;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;

public class BreakStatementImpl extends StatementImpl implements JSBreakStatement{

	private final JSLabel label;
	
	public BreakStatementImpl(JSCodeModel codeModel) {
		super(codeModel);
		this.label = null;
	}

	public BreakStatementImpl(JSCodeModel codeModel, JSLabel label) {
		super(codeModel);
		Validate.notNull(label);
		this.label = label;
	}
	
	@Override
	public JSLabel getLabel() {
		return label;
	}

	@Override
	public <V, E extends Exception> V acceptStatementVisitor(
			JSStatementVisitor<V, E> visitor) throws E {
		return visitor.visitBreak(this);
	}
	
	

}
