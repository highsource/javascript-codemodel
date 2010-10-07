package org.hisrc.jscm.codemodel.test;

import java.io.IOException;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.writer.FormatterImpl;
import org.hisrc.jscm.codemodel.writer.SourceElementWriterVisitor;
import org.junit.Test;

public class ExpressionsTest {

	@Test
	public void allExpressions() throws IOException {

		final JSFormatter out = new FormatterImpl(System.out);
		JSCodeModel codeModel = new CodeModelImpl();

		JSProgram program = codeModel.program();
		JSFunctionDeclaration f = program.functionDeclaration("f");

		JSVariable x = f.parameter("x");
		JSVariable y = f.parameter("y");

		JSFunctionBody body = f.getBody();

		JSBlock assignmentExpressions = body.block();

		assignmentExpressions.expression(x.assign(y));
		assignmentExpressions.expression(x.mulAssign(y));
		assignmentExpressions.expression(x.divAssign(y));
		assignmentExpressions.expression(x.modAssign(y));
		assignmentExpressions.expression(x.plusAssign(y));
		assignmentExpressions.expression(x.minusAssign(y));
		assignmentExpressions.expression(x.shlAssign(y));
		assignmentExpressions.expression(x.shrAssign(y));
		assignmentExpressions.expression(x.shrzAssign(y));
		assignmentExpressions.expression(x.bandAssign(y));
		assignmentExpressions.expression(x.borAssign(y));
		assignmentExpressions.expression(x.xorAssign(y));

		JSBlock conditionalExpressions = assignmentExpressions.block();
		conditionalExpressions.expression(x.cond(y, y.not()));

		JSBlock logicalORExpressions = conditionalExpressions.block();
		logicalORExpressions.expression(x.or(y));

		JSBlock logicalANDExpressions = logicalORExpressions.block();
		logicalANDExpressions.expression(x.and(y));

		JSBlock bitwiseORExpressions = logicalANDExpressions.block();
		bitwiseORExpressions.expression(x.bor(y));

		JSBlock bitwiseXORExpressions = bitwiseORExpressions.block();
		bitwiseXORExpressions.expression(x.xor(y));

		JSBlock bitwiseANDExpressions = bitwiseXORExpressions.block();

		bitwiseANDExpressions.expression(x.band(y));

		JSBlock equalityExpressions = bitwiseANDExpressions.block();
		equalityExpressions.expression(x.eq(y));
		equalityExpressions.expression(x.ne(y));
		equalityExpressions.expression(x.eeq(y));
		equalityExpressions.expression(x.nee(y));

		JSBlock relationalExpressions = equalityExpressions.block();

		relationalExpressions.expression(x.lt(y));
		relationalExpressions.expression(x.gt(y));
		relationalExpressions.expression(x.le(y));
		relationalExpressions.expression(x.ge(y));
		relationalExpressions.expression(x._instanceof(y));
		relationalExpressions.expression(x.in(y));

		JSBlock shiftExpressions = relationalExpressions.block();

		shiftExpressions.expression(x.shl(y));
		shiftExpressions.expression(x.shr(y));
		shiftExpressions.expression(x.shrz(y));

		JSBlock additiveExpressions = shiftExpressions.block();

		additiveExpressions.expression(x.plus(y));
		additiveExpressions.expression(x.minus(y));

		JSBlock multiplicativeExpressions = additiveExpressions.block();

		multiplicativeExpressions.expression(x);
		multiplicativeExpressions.expression(x.mul(y));
		multiplicativeExpressions.expression(x.div(y));
		multiplicativeExpressions.expression(x.mod(y));

		JSBlock unaryExpressions = multiplicativeExpressions.block();

		unaryExpressions.expression(x.delete());
		unaryExpressions.expression(x._void());
		unaryExpressions.expression(x.typeof());
		unaryExpressions.expression(x.preIncr());
		unaryExpressions.expression(x.preDecr());
		unaryExpressions.expression(x.positive());
		unaryExpressions.expression(x.negative());
		unaryExpressions.expression(x.complement());
		unaryExpressions.expression(x.not());

		JSBlock postfixExpressions = unaryExpressions.block();

		postfixExpressions.expression(x.postIncr());
		postfixExpressions.expression(x.postDecr());

		JSBlock leftHandSideExpressions = postfixExpressions.block();

		JSBlock callExpressions = leftHandSideExpressions.block();

		callExpressions.expression(x.invoke().args(y));
		callExpressions.expression(x.invoke().args(y).args(y).args(y));
		callExpressions.expression(x.invoke().args(y, y, y));
		callExpressions
				.expression(x.invoke().args(y).element(codeModel.lit(0)));
		callExpressions.expression(x.invoke().args(y).property("zero"));
		callExpressions.expression(x.invoke().args(y)
				.property(codeModel.lit(0)));
		callExpressions.expression(x.invoke().args(y)
				.property(codeModel.lit("two")));

		JSBlock newExpressions = leftHandSideExpressions.block();

		newExpressions.expression(x._new());

		JSBlock memberExpressions = newExpressions.block();

		memberExpressions.expression(codeModel.function());
		memberExpressions.expression(x.element(codeModel.lit(0)));
		memberExpressions.expression(x.property("zero"));
		memberExpressions.expression(x.property(codeModel.lit(1)));
		memberExpressions.expression(x.property(codeModel.lit("two")));
		memberExpressions.expression(x.instantiate().args(y));

		JSBlock primaryExpressions = memberExpressions.block();

		primaryExpressions.expression(codeModel._this());
		primaryExpressions.expression(x);

		JSBlock literals = primaryExpressions.block();
		literals.expression(codeModel._null());
		literals.expression(codeModel.lit(true));
		literals.expression(codeModel.lit(false));
		literals.expression(codeModel.lit(-1L));
		literals.expression(codeModel.lit(0L));
		literals.expression(codeModel.lit(1L));
		literals.expression(codeModel.lit(-1.1d));
		literals.expression(codeModel.lit(0.0d));
		literals.expression(codeModel.lit(1.1d));
		literals.expression(codeModel.lit("a"));

		primaryExpressions.expression(codeModel.array(x, y));
		primaryExpressions.expression(codeModel.array().append(x, y));
		primaryExpressions.expression(codeModel.object().append("zero", x)
				.append(codeModel.lit(1), x).append(codeModel.lit("two"), x));
		primaryExpressions.expression(codeModel.lit(true).brackets());

		f.acceptSourceElementVisitor(new SourceElementWriterVisitor(out));

	}
}
