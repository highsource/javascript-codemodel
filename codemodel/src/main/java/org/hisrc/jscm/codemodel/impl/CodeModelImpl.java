package org.hisrc.jscm.codemodel.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression.Function;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.expression.impl.ArrayLiteralImpl;
import org.hisrc.jscm.codemodel.expression.impl.FunctionExpressionImpl;
import org.hisrc.jscm.codemodel.expression.impl.GlobalVariableImpl;
import org.hisrc.jscm.codemodel.expression.impl.ObjectLiteralImpl;
import org.hisrc.jscm.codemodel.expression.impl.ThisImpl;
import org.hisrc.jscm.codemodel.literal.JSBooleanLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSDecimalNonIntegerLiteral;
import org.hisrc.jscm.codemodel.literal.JSNullLiteral;
import org.hisrc.jscm.codemodel.literal.JSStringLiteral;
import org.hisrc.jscm.codemodel.literal.impl.BooleanLiteralImpl;
import org.hisrc.jscm.codemodel.literal.impl.DecimalIntegerLiteralImpl;
import org.hisrc.jscm.codemodel.literal.impl.DecimalNonIntegerLiteralImpl;
import org.hisrc.jscm.codemodel.literal.impl.NullLiteralImpl;
import org.hisrc.jscm.codemodel.literal.impl.StringLiteralImpl;

public class CodeModelImpl implements JSCodeModel {

	@Override
	public JSNullLiteral _null() {
		return new NullLiteralImpl(this);
	}

	@Override
	public JSThis _this() {
		return new ThisImpl(this);
	}

	private final Map<String, JSGlobalVariable> globalVariables = Collections
			.synchronizedMap(new HashMap<String, JSGlobalVariable>());

	@Override
	public JSGlobalVariable globalVariable(String name) {
		Validate.notNull(name);

		JSGlobalVariable globalVariable = globalVariables.get(name);
		if (globalVariable == null) {
			globalVariable = new GlobalVariableImpl(this, name);
			globalVariables.put(name, globalVariable);
		}

		return globalVariable;
	}

	@Override
	public JSBooleanLiteral _boolean(boolean value) {
		return new BooleanLiteralImpl(this, value);
	}

	@Override
	public JSDecimalIntegerLiteral integer(long value) {
		return new DecimalIntegerLiteralImpl(this, value);
	}

	@Override
	public JSDecimalNonIntegerLiteral decimal(String value) {
		Validate.notNull(value);
		final BigDecimal bigDecimal = new BigDecimal(value);
		return new DecimalNonIntegerLiteralImpl(this, bigDecimal);
	}

	@Override
	public JSStringLiteral string(String value) {
		Validate.notNull(value);
		return new StringLiteralImpl(this, value);
	}

	@Override
	public JSArrayLiteral array() {
		return new ArrayLiteralImpl(this);
	}

	@Override
	public JSObjectLiteral object() {
		return new ObjectLiteralImpl(this);
	}

	@Override
	public Function function() {
		return new FunctionExpressionImpl.FunctionImpl(this);
	}

	@Override
	public Function function(String name) {
		Validate.notNull(name);
		return new FunctionExpressionImpl.FunctionImpl(this, name);
	}

	@Override
	public JSProgram program() {
		return new ProgramImpl(this);
	}
}
