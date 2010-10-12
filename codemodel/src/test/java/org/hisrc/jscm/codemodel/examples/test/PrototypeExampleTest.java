package org.hisrc.jscm.codemodel.examples.test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.hisrc.jscm.codemodel.JSCodeModel;
import org.hisrc.jscm.codemodel.JSFunctionBody;
import org.hisrc.jscm.codemodel.JSProgram;
import org.hisrc.jscm.codemodel.expression.JSFunctionExpression.Function;
import org.hisrc.jscm.codemodel.expression.JSGlobalVariable;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.impl.CodeModelImpl;
import org.hisrc.jscm.codemodel.writer.CodeWriter;
import org.junit.Assert;
import org.junit.Test;

public class PrototypeExampleTest {

	private JSCodeModel codeModel = new CodeModelImpl();

	@Test
	public void programsPrototype() throws IOException {
		JSProgram program = codeModel.program();

		JSGlobalVariable $Object = codeModel.globalVariable("Object");
		JSGlobalVariable $navigator = codeModel.globalVariable("navigator");
		JSGlobalVariable $window = codeModel.globalVariable("window");
		JSGlobalVariable $document = codeModel.globalVariable("document");

		JSObjectLiteral _Prototype = codeModel.object();
		program.variable("Prototype", _Prototype);

		{
			_Prototype.append("Version", codeModel.string("1.6.1"));
		}
		{
			final Function function = codeModel.function();
			JSFunctionBody body = function.getBody();
			JSVariable $ua = body.variable("ua",
					$navigator.property("userAgent")).getVariable();
			JSVariable $isOpera = body.variable(
					"isOpera",
					$Object.property("prototype").property("toString")
							.invoke("call").args($window.property("opera"))
							.eq(codeModel.string("[object Opera]")))
					.getVariable();
			body._return(codeModel
					.object()
					.append("IE",
							$window.property("attachEvent").not().not()
									.and($isOpera.not()))
					.append("Opera", $isOpera)
					.append("WebKit",
							$ua.invoke("indexOf")
									.args(codeModel.string("AppleWebKit/"))
									.gt(codeModel.integer(-1)))
					.append("Gecko",
							$ua.invoke("indexOf")
									.args(codeModel.string("Gecko"))
									.gt(codeModel.integer(-1))
									.and($ua.invoke("indexOf")
											.args(codeModel.string("KHTML"))
											.eeq(codeModel.integer(-1))))
			// Regexps are not supported at the moment
			// append("MobileSafari",
			// codeModel.regexp("/Apple.*Mobile.*Safari/").call("test").args($ua))
			);

			_Prototype.append("Browser", function.brackets().invoke());
		}

		{
			final JSObjectLiteral _BrowserFeatures = codeModel.object();
			_Prototype.append("BrowserFeatures", _BrowserFeatures);

			_BrowserFeatures.append("XPath", $document.property("evaluate").not().not());
			_BrowserFeatures.append("SelectorsAPI", $document.property("querySelector").not()
					.not());
			{
				final Function _ElementExtensions = codeModel.function();

				JSVariable $constructor = _ElementExtensions
						.getBody()
						.variable(
								"constructor",
								$window.property("Element").or(
										$window.property("HTMLElement")))
						.getVariable();
				_ElementExtensions.getBody()._return(
						$constructor.and($constructor.property("prototype"))
								.brackets().not().not());
				_BrowserFeatures.append("ElementExtensions", _ElementExtensions.brackets()
						.invoke());
			}

			{
				final Function f = codeModel.function();
				_BrowserFeatures.append("SpecificElementExtensions", f.brackets().invoke());
				JSFunctionBody b = f.getBody();
				b._if($window.property("HTMLDivElement").typeof()
						.nee(codeModel.string("undefined")))._then()
						._return(codeModel._boolean(true));

				JSVariable $div = b.variable(
						"div",
						$document.invoke("createElement").args(
								codeModel.string("div"))).getVariable();
				JSVariable $form = b.variable(
						"form",
						$document.invoke("createElement").args(
								codeModel.string("form"))).getVariable();

				JSVariable $isSupported = b.variable("isSupported",
						codeModel._boolean(false)).getVariable();

				b._if($div.element(codeModel.string("__proto__")).and(
						$div.element(codeModel.string("__proto__"))
								.nee($form.element(codeModel
										.string("__proto__"))).brackets()))
						._then()
						.block()
						.expression(
								$isSupported.assign(codeModel._boolean(true)));

				b.expression($div.assign($form.assign(codeModel._null())));
				b._return($isSupported);
			}

		}
		{
			_Prototype.append("ScriptFragment", codeModel
					.string("<script[^>]*>([\\\\S\\\\s]*?)<\\/script>"));
		}
		{
			// o.append("JSONFilter",
			// codeModel.regexp("/^\\/\\*-secure-([\\s\\S]*)\\*\\/\\s*$/"));
		}
		{
			_Prototype.append("emptyFunction", codeModel.function());
		}
		{
			final Function _K = codeModel.function();
			JSVariable $x = _K.parameter("x");
			_K.getBody()._return($x);
			_Prototype.append("K", _K);
		}

		final CodeWriter systemOutCodeWriter = new CodeWriter(System.out);
		systemOutCodeWriter.program(program);

		final Writer stringWriter = new StringWriter();
		final CodeWriter stringCodeWriter = new CodeWriter(stringWriter);
		stringCodeWriter.program(program);
		stringWriter.close();

		
		String test = IOUtils.toString(getClass().getResourceAsStream(
				"Prototype[0].test.js"));
		String sample = stringWriter.toString();
		
		Assert.assertEquals(test.length(), sample.length());
		Assert.assertEquals(
				test, sample);

	}
}
