package org.hisrc.jscm.codemodel.test;

import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSIfStatement;
import org.hisrc.jscm.codemodel.JSNumericLiteral;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.JSVariable;
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
}
