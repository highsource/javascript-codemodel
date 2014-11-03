/*
   The environment frame for a function/method call, comprising the local
   scope stack (local vars plus nesting "with" statements), and a "this" reference.
   If "thisVal" is null, this means that the function was not invoked as a
   method, and "this" is resolved according to 3.51 of the JS spec, i.e.
   the innermost "with" object, else the function itself.
   The return value is primed as 'undefined' immediately prior to the function
   call. A return statement in the body will then overwrite the 'undefined'
   return. 
 */

package roland.javascript;

import java.lang.String;

class JSCall
{
  private JSScopeStack _scopeStack;
  private JSValue _thisVal;

  public JSCall( JSValue thisVal) {
    _thisVal = thisVal;
    _scopeStack = new JSScopeStack();
    // The scope for local variables and parameters
    _scopeStack.push( new JSCallScope());
  }

  public JSCall() {
    this(null);
  }

  public JSValue thisVal( JSContext context) throws JSException {
    // _thisVal is non-null for member calls
    if(_thisVal != null) {
      return _thisVal;
    }
    // otherwise the _thisVal of the innermost scope is what is used
    else return _scopeStack.thisVal(context);
  }

  // Add a new variable/member to the innermost scope?
  public void put( String name, JSValue val, JSContext context) throws JSException {
    _scopeStack.peek().put(name, val, context);
  }

  // Add a variable to the outermost scope, for local variable declarations
  public void putLocal( String name, JSValue val, JSContext context) throws JSException {
  	_scopeStack.peekFirst().put(name, val, context);
  }

  // Find a local-scope identifier
  public JSValue get( String name, JSContext context) throws JSException {
    return _scopeStack.get( name, context);
  }

  // Push a new ("with") scope
  public void push( JSScope scope) {
    _scopeStack.push(scope);
  }

  // Pop the innermost ("with") scope
  public void pop() {
    _scopeStack.pop();
  }

  public JSScopeStack scopeStack() {
  	return _scopeStack;
  }
}