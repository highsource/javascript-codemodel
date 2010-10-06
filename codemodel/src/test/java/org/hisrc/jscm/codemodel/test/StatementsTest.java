package org.hisrc.jscm.codemodel.test;

import java.io.IOException;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFormatter;
import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.statement.JSForVarInStatement;
import org.hisrc.jscm.codemodel.statement.JSForVarStatement;
import org.hisrc.jscm.codemodel.statement.JSIfStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement.JSCaseClause;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.writer.FormatterImpl;
import org.hisrc.jscm.codemodel.writer.SourceElementWriterVisitor;
import org.junit.Test;

public class StatementsTest {
	@Test
	public void allExpressions() throws IOException {

		final JSFormatter out = new FormatterImpl(System.out);
		JSCodeModel codeModel = new CodeModelImpl();

		JSProgram program = codeModel.program();
		JSFunctionDeclaration f = program.functionDeclaration("f");

		JSVariable x = f.parameter("x");
		JSVariable y = f.parameter("y");

		JSFunctionBody body = f.getBody();

		{
			body.block();
		}
		{
			body.variable("a").getVariable();
			body.variable("b", codeModel.lit("b"));

			body.variable("c", codeModel.lit("c")).comma("d")
					.comma("e", codeModel.lit("e"));
		}
		body.empty();
		{
			body._if(x.gt(y))._then()._return();
			JSIfStatement _if = body._if(x.le(y));
			_if._then()._return(x.mul(y));
			_if._else()._return(x.plus(x));
		}

		{

			body._for().test(x.lt(y)).update(x.postIncr())
					.expression(y.preIncr());
			body._for().update(x.postIncr()).expression(y.preIncr());
			body._for().test(x.lt(y)).expression(y.preIncr());
			body._for()._break();
			body._for(x.assign(codeModel.lit(0))).test(x.lt(y))
					.update(x.postIncr()).expression(y.preIncr());
			body.forIn(x, y).block().expression(x.postDecr());

			JSForVarStatement _forVar0 = body.forVar("x0");
			JSVariable x0 = _forVar0.getVariable();
			_forVar0.test(x0.lt(codeModel.lit(5))).update(x0.postIncr())
					.expression(y.plusAssign(x0));

			JSForVarStatement _forVar1 = body.forVar("x1", codeModel.lit(0));
			JSVariable x1 = _forVar1.getVariable();
			_forVar1.test(x1.lt(codeModel.lit(5))).update(x1.postIncr())
					.expression(y.plusAssign(x1));

			JSForVarInStatement _forVarIn = body.forVarIn("x3", y);
			JSVariable x3 = _forVarIn.getVariable();
			_forVarIn.expression(y.plusAssign(x3));

			body.doWhile(x.lt(y)).expression(x.postIncr());

			body._while(x.lt(y)).expression(x.postIncr());
		}

		{
			JSLabelledStatement labelledStatement = body.label("label0");
			JSLabel label0 = labelledStatement.getLabel();
			labelledStatement.block()._break(label0);

			body._continue();
			body._continue(label0);

			body._break();
			body._break(label0);
		}
		{
			body._return();
			body._return(x);
			body.with(x).expression(y.postIncr());
		}
		{
			JSSwitchStatement _switch0 = body._switch(x);
			_switch0._case(codeModel.lit(0))._return(codeModel.lit(1));

			JSSwitchStatement _switch1 = body._switch(x);
			_switch1._case(codeModel.lit(0))._return(codeModel.lit(1));
			_switch1._default()._return(codeModel.lit(2));

			JSSwitchStatement _switch2 = body._switch(x);
			JSCaseClause _case20 = _switch2._case(codeModel.lit(0));
			_case20.expression(x.postIncr());
			_case20._return(codeModel.lit(1));
			_switch2._default()._return(codeModel.lit(2));
			_switch2._case(codeModel.lit(2))._return(codeModel.lit(3));

		}

		{
			body._throw(x);
		}

		{
			JSTryStatement tryCatch0 = body.tryCatch("e");
			tryCatch0.getBody().expression(y.preIncr());
			tryCatch0.getCatch()._throw(tryCatch0.getException());

			JSTryStatement tryFinally0 = body.tryFinally();
			tryFinally0.getBody().expression(y.preIncr());
			tryFinally0.getFinally()._return(codeModel.lit(true));

			JSTryStatement tryCatchFinally = body.tryCatchFinally("e");
			tryCatchFinally.getBody().expression(y.preIncr());
			tryCatchFinally.getCatch()._throw(tryCatchFinally.getException());
			tryCatchFinally.getFinally()._return(codeModel.lit(true));
		}

		{
			body.debugger();
		}

		f.acceptSourceElementVisitor(new SourceElementWriterVisitor(out));
	}
}
