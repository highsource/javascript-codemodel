/*
	Special value type which stores the formal parameter list of a function.
  This is created only when a function object is created, and is not generally
  available to the public.
 */
package roland.javascript;

import java.util.Vector;

class JSFunctionFormalsValue
	extends JSValue
{
	private Vector _formals;

  public JSFunctionFormalsValue( Vector formalsVec) {
  	_formals = formalsVec;
  }

  public Vector formals() {
  	return _formals;
  }

	// Casting operators - none

  // typeof operator
  public String typeof( JSContext context) {
  	return "function_formals";
  }
}


