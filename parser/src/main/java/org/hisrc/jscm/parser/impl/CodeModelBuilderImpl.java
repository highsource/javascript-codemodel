package org.hisrc.jscm.parser.impl;

import java.math.BigInteger;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.impl.IdentifierNameImpl;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalLiteral;
import org.hisrc.jscm.codemodel.literal.JSHexIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;
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
	public JSThis _this() {
		return codeModel._this();
	}
	
	@Override
	public JSArrayLiteral array() {
		return codeModel.array();
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
	
	@Override
	public JSDecimalIntegerLiteral decimalIntegerLiteral(Token token)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public JSDecimalIntegerLiteral decimalIntegerLiteral(Token mainPart,
			Token exponentpart) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSStringLiteral stringLiteral(Token token) throws ParseException {
		// TODO Incorrect
		return codeModel.string(token.image);
	}

	@Override
	public JSHexIntegerLiteral hexIntegerLiteral(Token token)
			throws ParseException {
		// TODO Incorrect
		return codeModel
				.hexInteger(new BigInteger(token.image.substring(2), 16));
	}

	
	@Override
	public JSIdentifierName identifierName(Token token) throws ParseException {
		// TODO Incorrect
		return new IdentifierNameImpl(token.image);
	}
}
