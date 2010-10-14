package org.hisrc.jscm.codemodel.test;

import java.io.IOException;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.statement.JSForVarInStatement;
import org.hisrc.jscm.codemodel.statement.JSForVarStatement;
import org.hisrc.jscm.codemodel.statement.JSIfStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement;
import org.hisrc.jscm.codemodel.statement.JSLabelledStatement.JSLabel;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement.JSCaseClause;
import org.hisrc.jscm.codemodel.statement.JSSwitchStatement.JSDefaultClause;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.junit.Assert;
import org.junit.Test;

public class StatementsTest {
	@Test
	public void allExpressions() throws IOException {

		final CodeWriter out = new CodeWriter(System.out);
		JSCodeModel codeModel = new CodeModelImpl();

		JSProgram program = codeModel.program();
		program.empty();
		JSGlobalVariable window = codeModel.globalVariable("window");
		JSGlobalVariable window1 = codeModel.globalVariable("window");
		Assert.assertSame(window, window1);

		JSFunctionDeclaration f = program.functionDeclaration("f");

		JSVariable x = f.parameter("x");
		JSVariable y = f.parameter("y");

		JSFunctionBody body = f.getBody();

		{
			body.functionDeclaration("g").getBody().functionDeclaration("h")
					.getBody().functionDeclaration("i");
			body.expression(window.p("open").i()
					.args(codeModel._this()));
			body.block();
			body.block().block();
			body.block().block();
			body.block().block().block().block();
			body.block().block().block().block().debugger();
		}
		{
			body.variable("a").getVariable();
			body.variable("b", codeModel.string("b"));

			body.variable("c", codeModel.string("c")).comma("d")
					.comma("e", codeModel.string("e"));
		}
		body.empty();
		{
			{
				body._if(x.gt(y))._then()._return();
			}
			{
				body._if(x.gt(y))._then().block()._return();
			}

			{
				JSIfStatement _if = body._if(x.le(y));
				_if._then()._return(x.mul(y));
				_if._else()._return(x.plus(x));
			}
			{
				JSIfStatement _if = body._if(x.le(y));
				_if._then().block()._return(x.mul(y));
				_if._else().block()._return(x.plus(x));
			}
			{
				JSIfStatement _if = body._if(x.le(y));
				_if._then().block()._return(x.mul(y));
				_if._else()._if(x.eq(y))._then()._return(x.plus(x));
			}
		}

		{

			body._for().test(x.lt(y)).update(x.postIncr())
					.expression(y.preIncr());
			body._for().update(x.postIncr()).expression(y.preIncr());
			body._for().test(x.lt(y)).expression(y.preIncr());
			body._for()._break();
			body._for(x.assign(codeModel.integer(0))).test(x.lt(y))
					.update(x.postIncr()).expression(y.preIncr());
			body.forIn(x, y).block().expression(x.postDecr());

			JSForVarStatement _forVar0 = body.forVar("x0");
			Assert.assertNull(_forVar0.getExpression());
			JSVariable x0 = _forVar0.getVariable();
			_forVar0.comma("x1").comma("x2", codeModel.integer(10));
			_forVar0.test(x0.lt(codeModel.integer(5))).update(x0.postIncr())
					.expression(y.plusAssign(x0));

			JSForVarStatement _forVar1 = body
					.forVar("x1", codeModel.integer(0));
			Assert.assertNotNull(_forVar1.getExpression());
			JSVariable x1 = _forVar1.getVariable();
			_forVar1.test(x1.lt(codeModel.integer(5))).update(x1.postIncr())
					.expression(y.plusAssign(x1));

			JSForVarInStatement _forVarIn = body.forVarIn("x3", y);
			JSVariable x3 = _forVarIn.getVariable();
			_forVarIn.expression(y.plusAssign(x3));

			body.doWhile(x.lt(y)).expression(x.postIncr());
			body.doWhile(x.lt(y)).block().expression(x.postIncr());

			body._while(x.lt(y)).expression(x.postIncr());
			body._while(x.lt(y)).block().expression(x.postIncr());
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
			body.with(x).block().expression(y.postIncr());
		}
		{
			JSSwitchStatement _switch0 = body._switch(x);
			JSCaseClause _case00 = _switch0._case(codeModel.integer(0));
			_case00.empty();
			_case00._return(codeModel.integer(1));

			JSSwitchStatement _switch1 = body._switch(x);
			JSCaseClause _case10 = _switch1._case(codeModel.integer(0));
			_case10.empty();
			_case10._return(codeModel.integer(1));
			JSDefaultClause _default1 = _switch1._default();
			_default1.empty();
			_default1._return(codeModel.integer(2));

			JSSwitchStatement _switch2 = body._switch(x);
			JSCaseClause _case20 = _switch2._case(codeModel.integer(0));
			_case20.expression(x.postIncr());
			_case20._return(codeModel.integer(1));
			JSDefaultClause _default2 = _switch2._default();
			_default2.empty();
			_default2._return(codeModel.integer(2));
			JSCaseClause _case22 = _switch2._case(codeModel.integer(2));
			_case22.empty();
			_case22._return(codeModel.integer(3));

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
			tryFinally0.getFinally()._return(codeModel._boolean(true));

			JSTryStatement tryCatchFinally = body.tryCatchFinally("e");
			tryCatchFinally.getBody().expression(y.preIncr());
			tryCatchFinally.getCatch()._throw(tryCatchFinally.getException());
			tryCatchFinally.getFinally()._return(codeModel._boolean(true));
		}

		{
			body.debugger();
		}

		// f.acceptSourceElementVisitor(new SourceElementWriter(out));
		out.program(program);
	}
}
