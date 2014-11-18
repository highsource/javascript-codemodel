package org.hisrc.jscm.codemodel.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.statement.JSStatement;
import org.hisrc.jscm.codemodel.statement.impl.StatementGeneratorImpl;

public class FunctionBodyImpl extends StatementGeneratorImpl implements
		JSFunctionBody {

	private final List<JSSourceElement> sourceElements = new ArrayList<JSSourceElement>();
	private final List<JSSourceElement> unmodifiableSourceElements = Collections
			.unmodifiableList(sourceElements);

	public FunctionBodyImpl(JSCodeModel codeModel) {
		this(codeModel, new JSSourceElement[0]);
	}

	public FunctionBodyImpl(JSCodeModel codeModel,
			JSSourceElement[] sourceElements) {
		super(codeModel);
		Validate.noNullElements(sourceElements);
		this.sourceElements.addAll(Arrays.asList(sourceElements));
	}

	protected <S extends JSStatement> S add(S statement) {
		Validate.notNull(statement);
		this.sourceElements.add(statement);
		return statement;
	}

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

}
