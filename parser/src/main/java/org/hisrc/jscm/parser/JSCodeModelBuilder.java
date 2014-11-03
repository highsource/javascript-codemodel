package org.hisrc.jscm.parser;

import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;

public interface JSCodeModelBuilder {

	public JSNullLiteral nullLiteral(Token token) throws ParseException;

	public JSBooleanLiteral booleanLiteral(Token token) throws ParseException;

	public JSDecimalLiteral decimalLiteral(Token token) throws ParseException;
}
