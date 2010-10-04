package org.hisrc.jscm.codemodel.impl;

import java.util.Arrays;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSCallExpression;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSPropertyAssignment;
import org.hisrc.jscm.codemodel.impl.expression.ArrayLiteralImpl;
import org.hisrc.jscm.codemodel.impl.expression.ObjectLiteralImpl;
import org.hisrc.jscm.codemodel.impl.expression.ThisImpl;
import org.hisrc.jscm.codemodel.impl.literal.BooleanLiteralImpl;
import org.hisrc.jscm.codemodel.impl.literal.DecimalIntegerLiteralImpl;
import org.hisrc.jscm.codemodel.impl.literal.DecimalNonIntegerLiteralImpl;
import org.hisrc.jscm.codemodel.impl.literal.NullLiteralImpl;
import org.hisrc.jscm.codemodel.impl.literal.StringLiteralImpl;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;
import org.hisrc.jscm.codemodel.JSProgram;

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
