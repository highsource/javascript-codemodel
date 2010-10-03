package org.hisrc.jscm.codemodel.impl;

import java.util.Arrays;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSArrayLiteral;
import org.hisrc.jscm.codemodel.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.JSCallExpression;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSNullLiteral;
import org.hisrc.jscm.codemodel.JSObjectLiteral;
import org.hisrc.jscm.codemodel.JSObjectLiteral.JSPropertyAssignment;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.JSStringLiteral;
import org.hisrc.jscm.codemodel.JSThis;

public class CodeModelImpl implements JSCodeModel {

	@Override
	public JSNullLiteral _null() {
		return new NullLiteralImpl(this);
	}

	@Override
	public JSThis _this() {
		return new ThisImpl(this);
	}

	@Override
	public JSBooleanLiteral lit(boolean value) {
		return new BooleanLiteralImpl(this, value);
	}

	@Override
	public JSDecimalIntegerLiteral lit(long value) {
		return new DecimalIntegerLiteralImpl(this, value);
	}

	@Override
	public JSDecimalNonIntegerLiteral lit(double value) {
		return new DecimalNonIntegerLiteralImpl(this, value);
	}

	@Override
	public JSStringLiteral lit(String value) {
		Validate.notNull(value);
		return new StringLiteralImpl(this, value);
	}

	@Override
	public JSArrayLiteral array(JSAssignmentExpression... elements) {
		Validate.noNullElements(elements);
		return new ArrayLiteralImpl(this, Arrays.asList(elements));
	}

	@Override
	public JSObjectLiteral object(JSPropertyAssignment... entries) {
		return new ObjectLiteralImpl(this, Arrays.asList(entries));
	}

	@Override
	public JSProgram program() {
		throw new UnsupportedOperationException();
	}

	@Override
	public JSCallExpression call(JSFunctionDeclaration function) {
		throw new UnsupportedOperationException();
	}
}
