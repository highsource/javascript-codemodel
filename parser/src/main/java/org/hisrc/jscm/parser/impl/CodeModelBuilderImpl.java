package org.hisrc.jscm.parser.impl;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.parser.JSCodeModelBuilder;
import org.hisrc.jscm.parser.ParseException;
import org.hisrc.jscm.parser.Token;

public class CodeModelBuilderImpl implements JSCodeModelBuilder {

	private final JSCodeModel codeModel;

	public CodeModelBuilderImpl(JSCodeModel codeModel) {
		this.codeModel = codeModel;
	}

	public CodeModelBuilderImpl() {
		this(new CodeModelImpl());
	}

	@Override
	public JSNullLiteral nullLiteral(Token token) throws ParseException {
		return codeModel._null();
	}

	@Override
	public JSBooleanLiteral booleanLiteral(Token token) throws ParseException {
		return codeModel._boolean(Boolean.valueOf(token.image));
	}
	
	@Override
	public JSDecimalLiteral decimalLiteral(Token token) throws ParseException {
		return null;
	}

}
