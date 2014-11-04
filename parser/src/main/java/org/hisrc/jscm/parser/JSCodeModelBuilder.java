package org.hisrc.jscm.parser;

import org.hisrc.jscm.codemodel.JSIdentifierName;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSHexIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public interface JSCodeModelBuilder {
	
	public JSThis _this();
	
	public JSArrayLiteral array();

	public JSNullLiteral nullLiteral(Token token) throws ParseException;

	public JSBooleanLiteral booleanLiteral(Token token) throws ParseException;

	public JSDecimalLiteral decimalLiteral(Token token) throws ParseException;
	
	public JSDecimalIntegerLiteral decimalIntegerLiteral(Token token) throws ParseException;

	public JSDecimalIntegerLiteral decimalIntegerLiteral(Token mainPart, Token exponentpart) throws ParseException;

	public JSDecimalNonIntegerLiteral decimalNonIntegerLiteral(Token mainPart, Token digitsPart) throws ParseException;

	public JSDecimalNonIntegerLiteral decimalNonIntegerLiteral(Token mainPart, Token digitsPart, Token exponentpart) throws ParseException;
	
	public JSStringLiteral stringLiteral(Token token) throws ParseException;
	
	public JSHexIntegerLiteral hexIntegerLiteral(Token token) throws ParseException;
	
	public JSIdentifierName identifierName(Token token) throws ParseException;
}
