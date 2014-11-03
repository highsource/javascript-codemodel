/*
	The global scope for a function invocation. This is just a wrapper around
  an object, with a different thisValInvoke().
 */

package roland.javascript;

class JSGlobalScope
	implements JSScope
{
	JSObjectValue _obj;

  /////////////////////////////////////////////////////////////////////////////
  // Contructor
  public JSGlobalScope( JSObjectValue obj) {
  	_obj = obj;
  }

  /////////////////////////////////////////////////////////////////////////////
  // Implementation of JSScope.
  // We pass everything through to _obj, except thisValInvoke()

	// Return an lvalue for the given identifier (null if it doesn't exist)
  //	Throws JSException if the identifier does not exist
  public JSLValue get( String name, JSContext context) throws JSException {
  	return get(name, false, context);
  }

  // Return an lvalue for the given identifier. If create is true then create
  //	it regardless of current existence, otherwise throws JSException
  //	if the identifier does not exist
  public JSLValue get( String name, boolean create, JSContext context) throws JSException {
  	_obj.get(name, create, context);
    return new JSLValue( this, name);
  }

	// Resolve the name, returning the value of the identifier (not the lvalue).
  //	Throws JSException if the identifier does not exist
  public JSValue getVal( String name, JSContext context) throws JSException {
  	try {
	  	return _obj.getVal(name, context);
    }
    catch( JSException e) {
    	throw new JSException( "Can't resolve identifier \"" + name + "\".", context);
    }
  }

  // Set the value of the given identifier. A new one is created if necessary.
  public void put( String name, JSValue val, JSContext context) throws JSException {
  	_obj.put(name, val, context);
  }

  // Remove the identifier from the dictionary
  //	Throws JSException if the identifier does not exist
  public void remove( String name, JSContext context) throws JSException {
  	_obj.remove(name, context);
  }

  // Return the "this" value. For object ("with"/global) scopes, this is the
  //	object. For local scopes, this is null.
  public JSValue thisVal(JSContext context) throws JSException {
  	return _obj.thisVal(context);
  }

  // Return the "this" value used for function invocation. For object scopes,
  //	this is the object. For local and global scopes, this is null.
  public JSValue thisValInvoke(JSContext context) {
  	return null;
  }
}