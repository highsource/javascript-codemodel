package org.hisrc.jscm.codemodel.writer.debug;

import java.io.IOException;

import org.hisrc.jscm.codemodel.expression.JSAdditiveExpression.Additive;
import org.hisrc.jscm.codemodel.expression.JSArrayLiteral;
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
import org.hisrc.jscm.codemodel.expression.JSExpression.Comma;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression.Function;
import org.hisrc.jscm.codemodel.expression.JSIdentifierReference;
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
import org.hisrc.jscm.codemodel.literal.JSLiteral;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.hisrc.jscm.codemodel.writer.ExpressionWriter;

public class CommentingExpressionWriter extends ExpressionWriter {

	public CommentingExpressionWriter(CodeWriter formatter) {
		super(formatter);
	}

	@Override
	public CodeWriter visitThis(JSThis value) throws IOException {
		f.inlineComment("E:this");
		return super.visitThis(value);
	}

	@Override
	public CodeWriter visitLiteral(JSLiteral value) throws IOException {
		f.inlineComment("E:Literal");
		return super.visitLiteral(value);
	}

	@Override
	public CodeWriter visitArrayLiteral(JSArrayLiteral value)
			throws IOException {
		f.inlineComment("E:ArrayLiteral");
		return super.visitArrayLiteral(value);
	}

	@Override
	public CodeWriter visitObjectLiteral(JSObjectLiteral value)
			throws IOException {
		f.inlineComment("E:ObjectLiteral");
		return super.visitObjectLiteral(value);
	}

	@Override
	public CodeWriter visitIdentifierReference(JSIdentifierReference value)
			throws IOException {
		f.inlineComment("E:IdentifierReference");
		return super.visitIdentifierReference(value);
	}

	@Override
	public CodeWriter visitBrackets(Brackets value) throws IOException {
		f.inlineComment("E:Brackets");
		return super.visitBrackets(value);
	}

	@Override
	public CodeWriter visitAdditive(Additive value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Additive");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitAnd(And value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:And");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitAssignment(Assignment value) throws IOException {
		f.inlineComment("E:Assignment");
		return super.visitAssignment(value);
	}

	@Override
	public CodeWriter visitBand(Band value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Band");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitBor(Bor value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Bor");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitCallArgs(CallArgs value) throws IOException {
		f.expression(value.getBase());
		f.inlineComment("E:CallArgs");
		visitInvocationArgs(value.getArgs());
		return f;
		
	}

	@Override
	public CodeWriter visitCallElement(CallElement value) throws IOException {
		f.expression(value.getBase());
		f.inlineComment("E:CallElement");
		f.openSquareBracket();
		f.indent().expression(value.getIndex()).unindent();
		f.closeSquareBracket();
		return f;
	}

	@Override
	public CodeWriter visitCallProperty(CallProperty value) throws IOException {
		f.expression(value.getBase());
		f.inlineComment("E:CallProperty");
		f.dot();
		f.indent().propertyName(value.getName()).unindent();
		return f;
	}

	@Override
	public CodeWriter visitComma(Comma value) throws IOException {
		f.inlineComment("E:Comma");
		return super.visitComma(value);
	}

	@Override
	public CodeWriter visitConditional(Conditional value) throws IOException {
		f.inlineComment("E:Conditional");
		return super.visitConditional(value);
	}

	@Override
	public CodeWriter visitEquality(Equality value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Equality");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitFunction(Function value) throws IOException {
		f.inlineComment("E:Function");
		return super.visitFunction(value);
	}

	@Override
	public CodeWriter visitMemberCall(MemberCall value) throws IOException {
		f.expression(value.getBase());
		f.inlineComment("E:MemberCall");
		visitInvocationArgs(value.getArgs());
		return f;
	}

	@Override
	public CodeWriter visitMemberElement(MemberElement value)
			throws IOException {
		f.expression(value.getBase());
		f.inlineComment("E:MemberElement");
		f.openSquareBracket();
		f.indent().expression(value.getIndex()).unindent();
		f.closeSquareBracket();
		return f;
		
	}

	@Override
	public CodeWriter visitMemberNew(MemberNew value) throws IOException {
		f.inlineComment("E:MemberNew");
		return super.visitMemberNew(value);
	}

	@Override
	public CodeWriter visitMemberProperty(MemberProperty value)
			throws IOException {
		f.expression(value.getBase());
		f.inlineComment("E:MemberProperty");
		f.dot();
		f.indent().propertyName(value.getName()).unindent();
		return f;
	}

	@Override
	public CodeWriter visitMultiplicative(Multiplicative value)
			throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Multiplicative");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitNew(New value) throws IOException {
		f.inlineComment("E:New");
		return super.visitNew(value);
	}

	@Override
	public CodeWriter visitOr(Or value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Or");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitPostfix(Postfix value) throws IOException {
		f.expression(value.getBase());
		f.inlineComment("E:Postfix");
		f.operator(value.getOperator());
		return f;
	}

	@Override
	public CodeWriter visitRelational(Relational value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Relational");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitShift(Shift value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Shift");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}

	@Override
	public CodeWriter visitUnary(Unary value) throws IOException {
		f.inlineComment("E:Unary");
		return super.visitUnary(value);
	}

	@Override
	public CodeWriter visitXor(Xor value) throws IOException {
		f.expression(value.getLeft());
		f.inlineComment("E:Xor");
		f.operator(value.getOperator());
		f.expression(value.getRight());
		return f;
	}
}
