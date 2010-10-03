package org.hisrc.jscm.codemodel;

// 11.13
public interface JSLeftHandSideExpression extends JSPostfixExpression {

	public JSPostfixExpression.Postfix postIncr();

	public JSPostfixExpression.Postfix postDecr();

	public JSAssignmentExpression.Assignment assign(JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment mulAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment divAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment modAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment plusAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment minusAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment shlAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment shrAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment shrzAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment bandAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment borAssign(
			JSAssignmentExpression value);

	public JSAssignmentExpression.Assignment xorAssign(
			JSAssignmentExpression value);

}
