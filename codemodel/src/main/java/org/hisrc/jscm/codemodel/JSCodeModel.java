package org.hisrc.jscm.codemodel;

import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public interface JSCodeModel {
	JSNullLiteral _null();

	JSThis _this();

	JSBooleanLiteral _boolean(boolean value);

	JSDecimalIntegerLiteral integer(long value);

	JSDecimalNonIntegerLiteral decimal(String value);

	JSStringLiteral string(String value);

	JSArrayLiteral array();

	JSObjectLiteral object();

	JSGlobalVariable globalVariable(String name);

	JSFunctionExpression.Function function();

	JSFunctionExpression.Function function(String name);

	JSProgram program();

}
