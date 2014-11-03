/*
 * Execution context for evaluating Javascript statements and expressions.
 *   The execution context comprises the global scope list, the local scope list,
 *   the current function hierarchy, and the current source _line and _col.
 */

package roland.javascript;

import java.lang.String;
import java.util.EmptyStackException;

class JSContext
{
  JSScopeStack _globalScope;

  JSCallStack _callStack;

  int _line;
  int _col;

  // This is set by every expression evaluation routine - we need it for the "eval()" builtin
  public JSValue _lastExpr;

  public JSContext( JSScope globalScope) {
    _globalScope = new JSScopeStack();
    _globalScope.push(globalScope);

    _callStack = new JSCallStack();

    _line = 0;

    _col = 0;

    _lastExpr = new JSUndefinedValue();
  }

  // Return an lval for the given identifier
  JSLValue get( String name) {
    JSLValue val = null;

    // try the local scope
    try {
      val = _callStack.localScope().get(name, this);
    }
    catch( EmptyStackException e) {}
    catch( JSException e) {}

    // then try the global scope
    if( val == null) {
    	try {
	      val = _globalScope.get(name, this);
      }
      catch( JSException e) {}
    }

  	// otherwise default to a (top-level) global reference
    if( val == null) {
    	try {
	      val = _globalScope.peekFirst().get(name, true, this);
      }
      catch( JSException e) { /*this should never occur*/ }
    }

    return val;
  }

  // Return an lval for a local variable regardless of existence (used for "var" declarations)
  //	If there is no local scope then this is done in the outermost global scope.
  JSLValue getLocal( String name) {
  	JSLValue val = null;

    // try the local scope
    try {
      val = _callStack.localScope().peekFirst().get(name, true, this);
    }
    catch( EmptyStackException e) {}
    catch( JSException e) { /*this should never occur*/ }

    // otherwise place it in the global scope
    if( val == null) {
    	try {
	      val = _globalScope.peekFirst().get(name, true, this);
      }
      catch( JSException e) { /*this should never occur*/ }
    }

    return val;
  }

  // Create a global variable (used for function declarations)
  void putGlobal( String name, JSValue val) throws JSException {
  	_globalScope.peekFirst().put( name, val, this);
  }

  // Resolve "this"
  JSValue thisVal() throws JSException {
    JSValue val = null;

    try {
      // method call or "with" object, or function we are in
      val = _callStack.localScope().thisVal(this);
    }
    catch( EmptyStackException e) {}
    catch( JSException e) {}

    // global scope resolution
    if( val == null) {
    	try {
	      val = _globalScope.peek().thisVal(this);
      }
      catch( JSException e) {}
    }

    if( val == null) {
      throw new JSException( "Can't resolve \"this\".", _line, _col);
    }

    return val;
  }

  void pushGlobalScope( JSScope scope) {
    _globalScope.push(scope);
  }

  void popGlobalScope() {
    _globalScope.pop();
  }

  void pushCall( JSCall call) {
    _callStack.push( call);
  }

  void popCall() {
    _callStack.pop();
  }

  void setLineCol( int line, int col) {
    _line = line;
    _col = col;
  }

  int getLine() {
  	return _line;
  }

  int getCol() {
  	return _col;
  }
}
