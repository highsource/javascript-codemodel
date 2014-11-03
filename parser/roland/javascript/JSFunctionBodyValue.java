/*
	Special value type which stores the _body of a function.
  This is created only when a function object is created, and is not generally
  visible to the public.
 */
package roland.javascript;

class JSFunctionBodyValue
	extends JSValue
{
	private JSStatement _body;

  public JSFunctionBodyValue( JSStatement bodyStmt) {
  	_body = bodyStmt;
  }

  public JSStatement body() {
  	return _body;
  }

	// Casting operators - none 

  // typeof operator
  public String typeof( JSContext context) {
  	return "function_body";
  }
}


