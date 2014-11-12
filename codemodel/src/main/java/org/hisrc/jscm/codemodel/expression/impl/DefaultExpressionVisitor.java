package org.hisrc.jscm.codemodel.expression.impl;

import org.hisrc.jscm.codemodel.expression.JSAdditiveExpression.Additive;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
import org.hisrc.jscm.codemodel.expression.JSIdentifierReference;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression.Assignment;
import org.hisrc.jscm.codemodel.expression.JSBitwiseANDExpression.Band;
import org.hisrc.jscm.codemodel.expression.JSBitwiseORExpression.Bor;
import org.hisrc.jscm.codemodel.expression.JSBitwiseXORExpression.Xor;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallArgs;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallElement;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.CallProperty;
import org.hisrc.jscm.codemodel.expression.JSCallExpression.MemberCall;
import org.hisrc.jscm.codemodel.expression.JSConditionalExpression.Conditional;
import org.hisrc.jscm.codemodel.expression.JSEqualityExpression.Equality;
import org.hisrc.jscm.codemodel.expression.JSExpression;
import org.hisrc.jscm.codemodel.expression.JSExpression.Comma;
import org.hisrc.jscm.codemodel.expression.JSExpressionVisitor;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression.Function;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
import org.hisrc.jscm.codemodel.expression.JSLogicalANDExpression.And;
import org.hisrc.jscm.codemodel.expression.JSLogicalORExpression.Or;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberElement;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberNew;
import org.hisrc.jscm.codemodel.expression.JSMemberExpression.MemberProperty;
import org.hisrc.jscm.codemodel.expression.JSMultiplicativeExpression.Multiplicative;
import org.hisrc.jscm.codemodel.expression.JSNewExpression.New;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSPostfixExpression.Postfix;
import org.hisrc.jscm.codemodel.expression.JSPrimaryExpression.Brackets;
import org.hisrc.jscm.codemodel.expression.JSRelationalExpression.Relational;
import org.hisrc.jscm.codemodel.expression.JSShiftExpression.Shift;
import org.hisrc.jscm.codemodel.expression.JSThis;
import org.hisrc.jscm.codemodel.expression.JSUnaryExpression.Unary;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.literal.JSLiteral;

public class DefaultExpressionVisitor<V, E extends Exception> implements
		JSExpressionVisitor<V, E> {

	public V visitExpression(JSExpression value) throws E {
		return null;
	}

	@Override
	public V visitThis(JSThis value) throws E {
		return visitExpression(value);
	}

//	@Override
//	public V visitVariable(JSVariable value) throws E {
//		return visitExpression(value);
//	}

//	@Override
//	public V visitGlobalVariable(JSGlobalVariable value) throws E {
//		return visitExpression(value);
//	}
	
	@Override
	public V visitIdentifierReference(JSIdentifierReference value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitLiteral(JSLiteral value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitArrayLiteral(JSArrayLiteral value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitObjectLiteral(JSObjectLiteral value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitBrackets(Brackets value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitFunction(Function value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitMemberElement(MemberElement value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitMemberProperty(MemberProperty value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitMemberNew(MemberNew value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitMemberCall(MemberCall value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitNew(New value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitCallArgs(CallArgs value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitCallElement(CallElement value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitCallProperty(CallProperty value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitPostfix(Postfix value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitUnary(Unary value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitMultiplicative(Multiplicative value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitAdditive(Additive value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitShift(Shift value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitRelational(Relational value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitEquality(Equality value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitBand(Band value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitXor(Xor value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitBor(Bor value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitAnd(And value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitOr(Or value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitConditional(Conditional value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitAssignment(Assignment value) throws E {
		return visitExpression(value);
	}

	@Override
	public V visitComma(Comma value) throws E {
		return visitExpression(value);
	}
}
