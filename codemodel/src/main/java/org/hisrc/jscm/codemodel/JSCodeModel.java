package org.hisrc.jscm.codemodel;

import java.math.BigDecimal;

import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSPropertyAssignment;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;

public interface JSCodeModel {
	JSNullLiteral _null();

	JSThis _this();

	JSBooleanLiteral lit(boolean value);

	JSDecimalIntegerLiteral lit(long value);

	JSDecimalNonIntegerLiteral lit(double value);

	JSDecimalNonIntegerLiteral lit(BigDecimal value);

	JSStringLiteral lit(String value);

	JSArrayLiteral array(JSAssignmentExpression... elements);

	JSObjectLiteral object(JSPropertyAssignment... entries);

	JSFunctionExpression.Function function();

	JSFunctionExpression.Function function(String name);

	public JSProgram program();

}
