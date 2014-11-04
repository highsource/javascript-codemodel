package org.hisrc.jscm.parser;

import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalLiteral;
import org.hisrc.jscm.codemodel.literal.JSHexIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public interface JSCodeModelBuilder {
	
	public JSThis _this();
	
	public JSArrayLiteral array();

	public JSNullLiteral nullLiteral(Token token) throws ParseException;

	public JSBooleanLiteral booleanLiteral(Token token) throws ParseException;

	public JSDecimalLiteral decimalLiteral(Token token) throws ParseException;

	public JSStringLiteral stringLiteral(Token token) throws ParseException;
	
	public JSHexIntegerLiteral hexIntegerLiteral(Token token) throws ParseException;
}
