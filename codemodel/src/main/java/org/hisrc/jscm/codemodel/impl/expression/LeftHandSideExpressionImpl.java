package org.hisrc.jscm.codemodel.impl.expression;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSLeftHandSideExpression;

public abstract class LeftHandSideExpressionImpl extends PostfixExpressionImpl
		implements JSLeftHandSideExpression {

	public LeftHandSideExpressionImpl(JSCodeModel codeModel) {
		super(codeModel);
	}

	@Override
	public Postfix postIncr() {
		return new PostfixImpl(getCodeModel(), this, PostfixOperator.POST_INCR);
	}

	@Override
	public Postfix postDecr() {
		return new PostfixImpl(getCodeModel(), this, PostfixOperator.POST_DECR);
	}

	@Override
	public Assignment assign(JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.ASSIGN);
	}

	public JSAssignmentExpression.Assignment mulAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.MUL_ASSIGN);
	}

	public JSAssignmentExpression.Assignment divAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.DIV_ASSIGN);
	}

	public JSAssignmentExpression.Assignment modAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.MOD_ASSIGN);
	}

	public JSAssignmentExpression.Assignment plusAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.PLUS_ASSIGN);
	}

	public JSAssignmentExpression.Assignment minusAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.MINUS_ASSIGN);
	}

	public JSAssignmentExpression.Assignment shlAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.SHL_ASSIGN);
	}

	public JSAssignmentExpression.Assignment shrAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.SHR_ASSIGN);
	}

	public JSAssignmentExpression.Assignment shrzAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.SHRZ_ASSIGN);
	}

	public JSAssignmentExpression.Assignment bandAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.BAND_ASSIGN);
	}

	public JSAssignmentExpression.Assignment borAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.BOR_ASSIGN);
	}

	public JSAssignmentExpression.Assignment xorAssign(
			JSAssignmentExpression value) {
		return new AssignmentImpl(getCodeModel(), this, value,
				AssignmentOperator.XOR_ASSIGN);
	}

}
