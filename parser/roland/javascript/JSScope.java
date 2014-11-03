/*
   A scope is a collection of name->value maps. A scope also has the concept of
   a "this": the "this" of a "with" scope is the object; the "this" of a
   function is the function itself; the "this" of a special global scope is the
   object whose scope is being used (window/document/top).

   We have three implementations of scopes - JSObjectValue's (for "with" and
   object index/member operator ('.') scopes), JSCallScope's for function
   invocations (containing local var's), and JSGlobalScope's for global scopes.
   JSGlobalScope's are a wrapper around a JSObjectValue, but have a different
   semantics for the "this" used in function invocation.
   When a function invocation is performed on a JSLValue with a JSGlobalScope,
   the invocation is a function invocation, not a member invocation, hence the
   called function does not have a "this" value. On the other hand, when a
   function invocation is done on a JSObjectValue, it is a member invocation,
   hence the called function's "this" is the JSObjectValue itself. 
 */

package roland.javascript;

interface JSScope
{
	// Return an lvalue for the given identifier (null if it doesn't exist)
  //	Throws JSException if the identifier does not exist
  public JSLValue get( String name, JSContext context) throws JSException;

  // Return an lvalue for the given identifier. If create is true then create
  //	it regardless of current existence, otherwise throws JSException
  //	if the identifier does not exist
  public JSLValue get( String name, boolean create, JSContext context) throws JSException;

	// Resolve the name, returning the value of the identifier (not the lvalue).
  //	Throws JSException if the identifier does not exist
  public JSValue getVal( String name, JSContext context) throws JSException;

  // Set the value of the given identifier. A new one is created if necessary.
  public void put( String name, JSValue val, JSContext context) throws JSException;

  // Remove the identifier from the dictionary
  //	Throws JSException if the identifier does not exist
  public void remove( String name, JSContext context) throws JSException;

  // Return the "this" value. For object ("with"/global) scopes, this is the
  //	object. For local scopes, this is null.
  public JSValue thisVal(JSContext context) throws JSException;

  // Return the "this" value used for function invocation. For object scopes,
  //	this is the object. For local and global scopes, this is null.
  public JSValue thisValInvoke(JSContext context) throws JSException;
}



