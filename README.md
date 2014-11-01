# JavaScript CodeModel 

JavaScript CodeModel project (JSCM for short) provides a Java API for programmatic creation and manipulation of JavaScript source code.

JSCM models grammatical structure of JavaScript (accoring to the [ECMAScript specification](http://www.ecma-international.org/publications/files/ECMA-ST/ECMA-262.pdf)) in Java classes. This allows building JavaScript code from Java programmatically.

Once object model of the JavaScript program is built, it can be formatted and printed out into a writer. The result is well-formatted (with indentation, line breaks etc.) and is almost guaranteed to be in correct JavaScript syntax.

# Using JavaScript CodeModel

Usage is rather straightforward. Here's a small example:

```java
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
```

This produces the following JavaScript code:

```javascript
function factorial(x) {
  return x <= 1 ? 1 : x * factorial(x - 1);
}
```

# Getting JavaScript CodeModel

For a single JAR file, check [releases](https://github.com/highsource/javascript-codemodel/releases).

If you're using Apache Maven (or compatible build tools), please use the following dependency:

```xml
<dependency>
	<groupId>org.hisrc.jscm</groupId>
	<artifactId>js-codemodel</artifactId>
	<version>...</version>
</dependency>
```

# Limitations

JSCM models JavaScript grammar with high precision. However there are certain things we've not implemented at the moment.

* Comments are not supported at the moment.
* Regular expressions are not supported at the moment.
* Identifier names are not checked for correctness.
* It is possible to create a construct like `for (var x in y in z) { ... }`
