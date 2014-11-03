/*
   A stack of JSScope's is used to maintain local scopes, comprising the
   local vars and params, and nesting with object, as well as the global scope,
   comprising special global scope's (e.g. document/window in browsers), and
   the true global scope.
 */

package roland.javascript;

import java.util.Vector;
import java.lang.String;

class JSScopeStack
  extends Vector
{
  public void push( JSScope scope) {
    addElement(scope);
  }

  public void pop() {
    removeElementAt( size()-1);
  }

  public JSScope peek() {
    return (JSScope)elementAt( size()-1);
  }

  public JSScope peekFirst() {
  	return (JSScope)elementAt(0);
  }

  // Throws JSException if not found
  public JSLValue get( String name, JSContext context) throws JSException {
    for( int i = size()-1; i >= 0; i--) {
      JSScope scope = (JSScope)elementAt(i);

      try {
	      JSLValue val = scope.get(name, context);
        return val;
      }
      catch( JSException e) {}
    }
    throw new JSException( "Identifier \"" + name + "\" not found.", context);
  }

  public JSValue thisVal( JSContext context) throws JSException {
    return peek().thisVal(context);
  }
}
