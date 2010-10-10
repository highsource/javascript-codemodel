package org.hisrc.jscm.codemodel.formatter;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.hisrc.jscm.codemodel.io.IndentedAppendable;
import org.hisrc.jscm.codemodel.operator.JSAssignmentOperator;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSKeywordBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSKeywordPrefixOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;
import org.hisrc.jscm.codemodel.operator.JSPostfixOperator;
import org.hisrc.jscm.codemodel.operator.JSPrefixOperator;
import org.hisrc.jscm.codemodel.operator.JSUnaryOperator;

public class OperatorFormatter implements
		JSOperatorVisitor<IndentedAppendable, IOException> {

	private final IndentedAppendable appendable;

	public OperatorFormatter(IndentedAppendable appendable) {
		Validate.notNull(appendable);
		this.appendable = appendable;
	}

	@Override
	public IndentedAppendable visitAssignmentOperator(
			JSAssignmentOperator operator) throws IOException {
		appendable.whiteSpace();
		appendable.append(operator.asString());
		appendable.whiteSpace();
		return appendable;
	}

	@Override
	public IndentedAppendable visitBinaryOperator(JSBinaryOperator operator)
			throws IOException {
		appendable.whiteSpace();
		appendable.append(operator.asString());
		appendable.whiteSpace();
		return appendable;
	}

	@Override
	public IndentedAppendable visitKeywordBinaryOperator(
			JSKeywordBinaryOperator operator) throws IOException {
		appendable.whiteSpace();
		appendable.append(operator.asString());
		appendable.whiteSpace();
		return appendable;
	}

	@Override
	public IndentedAppendable visitUnaryOperator(JSUnaryOperator operator)
			throws IOException {
		appendable.append(operator.asString());
		return appendable;
	}

	@Override
	public IndentedAppendable visitPrefixOperator(JSPrefixOperator operator)
			throws IOException {
		appendable.append(operator.asString());
		return appendable;
	}

	@Override
	public IndentedAppendable visitKeywordPrefixOperator(
			JSKeywordPrefixOperator operator) throws IOException {
		appendable.append(operator.asString());
		appendable.whiteSpace();
		return appendable;
	}

	@Override
	public IndentedAppendable visitPostfixOperator(JSPostfixOperator operator)
			throws IOException {
		appendable.append(operator.asString());
		return appendable;
	}
}
