package org.hisrc.jscm.codemodel.examples.test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionDeclaration;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.literal.JSDecimalIntegerLiteral;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.junit.Assert;
import org.junit.Test;

public class FactorialExampleTest {

	@Test
	public void programsFactorial() throws IOException {
		// Instantiate the code model
		JSCodeModel codeModel = new CodeModelImpl();
		// Create the program
		JSProgram program = codeModel.program();
		// Add a function declaration
		JSFunctionDeclaration factorial = program
				.functionDeclaration("factorial");
		// Add a function parameter
		JSVariable x = factorial.parameter("x");
		// Create an integer literal
		JSDecimalIntegerLiteral one = codeModel.integer(1);
		// Add a return statement to the function body
		factorial.getBody()._return(
				x.le(one).cond(
						one,
						x.mul(factorial.getFunctionExpression().i()
								.args(x.minus(one)))));

		// Write the program code to the System.out
		new CodeWriter(System.out).program(program);

		final Writer stringWriter = new StringWriter();
		final CodeWriter stringCodeWriter = new CodeWriter(stringWriter);
		stringCodeWriter.program(program);
		stringWriter.close();

		String test = IOUtils.toString(getClass().getResourceAsStream(
				"Factorial[0].test.js"));
		String sample = stringWriter.toString();

		// Assert.assertEquals(test.length(), sample.length());
		Assert.assertEquals(test, sample);

	}
}
