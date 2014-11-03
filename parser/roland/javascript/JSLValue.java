/*
   Named values, whether variables within a particular scope, or members
   within an object, are stored in hash-tables with the variable/member
   name as the key, and the current value as the hash table value.
 */

package roland.javascript;

import java.util.Hashtable;
import java.util.Vector;
import java.lang.String;

class JSLValue
  extends JSValue
{
  private JSScope _scope;
  private String _name;

  public JSLValue( JSScope scope, String name) {
    _scope = scope;
    _name = name;
  }

  public JSValue Get( JSContext context) throws JSException {
  	return _scope.getVal(_name, context);
  }

  public void Set( JSValue val, JSContext context) throws JSException {
     _scope.put( _name, val, context);
  }

  public void Delete( JSContext context) throws JSException {
  	_scope.remove( _name, context);
  }

	// Casting operators
	public JSValue asBoolean( JSContext context) throws JSException {
  	return Get(context).asBoolean(context);
  }
  public JSValue asNumber( JSContext context) throws JSException {
  	return Get(context).asNumber(context);
  }
  public JSValue asString( JSContext context) throws JSException {
  	return Get(context).asString(context);
  }
  public JSValue asObject( JSContext context) throws JSException {
  	return Get(context).asObject(context);
  }
  public JSValue asFunction( JSContext context) throws JSException {
  	return Get(context).asFunction(context);
  }

	// Type-checking operators.
  // ### These are not thread-safe, since the underlying
  //	value can be changed by another thread before this one uses it.
  // One solution would be to freeze the value at the time that these checks
  //	are made.
	public boolean isBoolean( JSContext context) throws JSException {
  	return Get(context).isBoolean(context);
  }
  public boolean isNumber( JSContext context) throws JSException {
  	return Get(context).isNumber(context);
  }
  public boolean isString( JSContext context) throws JSException {
  	return Get(context).isString(context);
  }
  public boolean isObject( JSContext context) throws JSException {
  	return Get(context).isObject(context);
  }
  public boolean isFunction( JSContext context) throws JSException {
  	return Get(context).isFunction(context);
  }
  public boolean isUndefined( JSContext context) throws JSException {
  	return Get(context).isUndefined(context);
  }

  // Copy value for re-assignment. For value types this copies the value, for
  //	reference types, this just copies the reference.
  public JSValue copy( JSContext context) throws JSException {
  	return Get(context).copy(context);
  }

  // typeof operator
  public String typeof( JSContext context) throws JSException {
  	return Get(context).typeof(context);
  }

  // For invoking a function on this LValue - this returns a "this" value,
  //	which is non-null for method invocation, or null for function invocation.
  public JSValue thisValInvoke( JSContext context) throws JSException {
  	return _scope.thisValInvoke( context);
  }
}

