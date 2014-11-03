/*
   List of currently active environment frames.
 */

package roland.javascript;

import java.util.Stack;

class JSCallStack
  extends Stack
{
  JSScopeStack localScope() {
    return ((JSCall)peek()).scopeStack();
  }

  JSValue thisVal(JSContext context) throws JSException {
    return ((JSCall)peek()).thisVal(context);
  }
}
