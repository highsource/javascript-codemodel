package org.hisrc.jscm.codemodel;

import org.hisrc.jscm.codemodel.JSObjectLiteral.JSPropertyAssignment;

public interface JSCodeModel {
	JSNullLiteral _null();

	JSThis _this();

	JSBooleanLiteral lit(boolean value);

	JSDecimalIntegerLiteral lit(long value);

	JSDecimalNonIntegerLiteral lit(double value);

	JSStringLiteral lit(String value);

	JSArrayLiteral array(JSAssignmentExpression... elements);

	JSObjectLiteral object(JSPropertyAssignment... entries);

	public JSProgram program();

	public JSCallExpression call(JSFunctionDeclaration function);
}
