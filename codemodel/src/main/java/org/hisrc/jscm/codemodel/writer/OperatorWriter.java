package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;

import org.hisrc.jscm.codemodel.io.IndentableAppendable;
import org.hisrc.jscm.codemodel.lang.Validate;
import org.hisrc.jscm.codemodel.operator.JSAssignmentOperator;
import org.hisrc.jscm.codemodel.operator.JSBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSKeywordBinaryOperator;
import org.hisrc.jscm.codemodel.operator.JSKeywordPrefixOperator;
import org.hisrc.jscm.codemodel.operator.JSOperatorVisitor;
import org.hisrc.jscm.codemodel.operator.JSPostfixOperator;
import org.hisrc.jscm.codemodel.operator.JSPrefixOperator;
import org.hisrc.jscm.codemodel.operator.JSUnaryOperator;

public class OperatorWriter implements
		JSOperatorVisitor<IndentableAppendable, IOException> {

	private final IndentableAppendable appendable;

	public OperatorWriter(IndentableAppendable appendable) {
		Validate.notNull(appendable);
		this.appendable = appendable;
	}

	@Override
	public IndentableAppendable visitAssignmentOperator(
			JSAssignmentOperator operator) throws IOException {
		appendable.whiteSpace();
		appendable.append(operator.asString());
		appendable.whiteSpace();
		return appendable;
	}

	@Override
	public IndentableAppendable visitBinaryOperator(JSBinaryOperator operator)
			throws IOException {
		appendable.whiteSpace();
		appendable.append(operator.asString());
		appendable.whiteSpace();
		return appendable;
	}

	@Override
	public IndentableAppendable visitKeywordBinaryOperator(
			JSKeywordBinaryOperator operator) throws IOException {
		appendable.whiteSpace();
		appendable.append(operator.asString());
		appendable.whiteSpace();
		return appendable;
	}

	@Override
	public IndentableAppendable visitUnaryOperator(JSUnaryOperator operator)
			throws IOException {
		appendable.append(operator.asString());
		return appendable;
	}

	@Override
	public IndentableAppendable visitPrefixOperator(JSPrefixOperator operator)
			throws IOException {
		appendable.append(operator.asString());
		return appendable;
	}

	@Override
	public IndentableAppendable visitKeywordPrefixOperator(
			JSKeywordPrefixOperator operator) throws IOException {
		appendable.append(operator.asString());
		appendable.whiteSpace();
		return appendable;
	}

	@Override
	public IndentableAppendable visitPostfixOperator(JSPostfixOperator operator)
			throws IOException {
		appendable.append(operator.asString());
		return appendable;
	}
}
