package org.hisrc.jscm.codemodel.test;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.JSVariable;
import org.hisrc.jscm.codemodel.literal.JSNumericLiteral;
import org.hisrc.jscm.codemodel.statement.JSBlock;
import org.hisrc.jscm.codemodel.statement.JSIfStatement;
import org.hisrc.jscm.codemodel.statement.JSTryStatement;
import org.junit.Test;

public class ProgramTest {

	@Test
	public void test() {

	}

	public void simpleProgram() {
		JSCodeModel codeModel = null;

		JSProgram program = codeModel.program();

		JSNumericLiteral one = codeModel.lit(1);
		JSFunctionDeclaration factorial = program.declare("factorial");
		JSVariable x = factorial.parameter("x");
		factorial.body()._return(
				x.le(one).cond(one,
						x.mul(codeModel.call(factorial).args(x.minus(one)))));

		JSIfStatement _if = factorial.body()._if(x.eq(one));

		_if._then()._return(one);
		_if._else()
				._return(x.mul(codeModel.call(factorial).args(x.minus(one))));
	}

	public void testOne() {
		JSBlock block = null;
		JSCodeModel codeModel = null;

		block.with(codeModel._this()).block()._return();

		JSTryStatement _try = block._tryCatch("foo");
		_try.body()._break();
		_try._catch()._throw(_try.exception());
		_try._finally()._continue();

	}
}
