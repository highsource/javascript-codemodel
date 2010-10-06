package org.hisrc.jscm.codemodel.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSCallExpression;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression.Function;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSPropertyAssignment;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.impl.expression.ArrayLiteralImpl;
import org.hisrc.jscm.codemodel.impl.expression.FunctionExpressionImpl;
import org.hisrc.jscm.codemodel.impl.expression.GlobalVariableImpl;
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

public class CodeModelImpl implements JSCodeModel {

	public JSNullLiteral _null() {
		return new NullLiteralImpl(this);
	}

	public JSThis _this() {
		return new ThisImpl(this);
	}

	private final Map<String, JSGlobalVariable> globalVariables = Collections
			.synchronizedMap(new HashMap<String, JSGlobalVariable>());

	public JSGlobalVariable globalVariable(String name) {
		Validate.notNull(name);

		JSGlobalVariable globalVariable = globalVariables.get(name);
		if (globalVariable == null) {
			globalVariable = new GlobalVariableImpl(this, name);
			globalVariables.put(name, globalVariable);
		}

		return globalVariable;
	}

	public JSBooleanLiteral lit(boolean value) {
		return new BooleanLiteralImpl(this, value);
	}

	public JSDecimalIntegerLiteral lit(long value) {
		return new DecimalIntegerLiteralImpl(this, value);
	}

	public JSDecimalNonIntegerLiteral lit(double value) {
		return new DecimalNonIntegerLiteralImpl(this, value);
	}

	public JSStringLiteral lit(String value) {
		Validate.notNull(value);
		return new StringLiteralImpl(this, value);
	}

	public JSArrayLiteral array(JSAssignmentExpression... elements) {
		Validate.noNullElements(elements);
		return new ArrayLiteralImpl(this, Arrays.asList(elements));
	}

	@Override
	public JSObjectLiteral object(JSPropertyAssignment... entries) {
		return new ObjectLiteralImpl(this, Arrays.asList(entries));
	}
	
	public Function function() {
		return new FunctionExpressionImpl.FunctionImpl(this);
	}
	
	public Function function(String name) {
		Validate.notNull(name);
		return new FunctionExpressionImpl.FunctionImpl(this, name);
	}

	public JSProgram program() {
		return new ProgramImpl(this);
	}

	public JSCallExpression call(JSFunctionDeclaration function) {
		throw new UnsupportedOperationException();
	}
}
