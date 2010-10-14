package org.hisrc.jscm.codemodel.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.JSProgramVisitor;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.impl.StatementGeneratorImpl;

public class ProgramImpl extends StatementGeneratorImpl implements JSProgram {

	private final List<JSSourceElement> sourceElements = new ArrayList<JSSourceElement>();
	private final List<JSSourceElement> unmodifiableSourceElements = Collections
			.unmodifiableList(sourceElements);

	public ProgramImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	protected <S extends JSStatement> S add(S statement) {
		Validate.notNull(statement);
		this.sourceElements.add(statement);
		return statement;
	}

	@Override
	public List<JSSourceElement> getSourceElements() {
		return unmodifiableSourceElements;
	}

	@Override
	public JSFunctionDeclaration functionDeclaration(String name) {
		Validate.notNull(name);
		final JSFunctionDeclaration functionDeclaration = new FunctionDeclarationImpl(
				getCodeModel(), name);
		sourceElements.add(functionDeclaration);
		return functionDeclaration;
	}

	@Override
	public <V, E extends Exception> V acceptProgramVisitor(
			JSProgramVisitor<V, E> visitor) throws E {
		return visitor.visitProgram(this);
	}
}
