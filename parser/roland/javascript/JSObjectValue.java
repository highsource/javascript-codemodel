/*
 * Javascript objects are merely dictionaries of values. Javascript considers
 *	both non-negative integer keys, and string keys. We convert everything to
 *	strings here for simplicity's() sake.
 * Null objects are represented, simply, by a null _ht member. Thus, null objects
 *	are created by new JSObjectValue((Hashtable)null).
 * ### this should be made Observable for those objects with side-affects
 */

package roland.javascript;

import java.util.Hashtable;
import java.util.Vector;
import java.lang.String;

class JSObjectValue
  extends JSValue
  implements JSScope
{
	private Hashtable _ht;

  //////////////////////////////////////////////////////////////////////////////
  // Constructors

  public JSObjectValue() {
  	_ht = new Hashtable();
  }

  // contructor for those descendant classes which have their own special hash-tables
  public JSObjectValue( Hashtable ht) {
  	_ht = ht;
  }

  // Constructor for function objects. Function object are ordinary objects in
  // every sense except that they have three special members:
  //	 1. "<name>", a JSStringValue containing the name of the function
  // 	 2. "<body>", a JSFunctionBodyValue, containing the executable body of the function, and
  //   3. "<formals>", a JSFuntionFormalsValue, containing the formal parameters of the function
  // The two types of value, JSFunctionBodyValue, and FSFuntionFormalsValue, can only
  // arise in the formation of a function object (either through the builtin Function()
  // constructor, or through casting a function to a function object).
  public JSObjectValue( String name, Vector formals, JSStatement body) {
  	this();

    _ht.put( "<name>", new JSStringValue(name));
    _ht.put( "<formals>", new JSFunctionFormalsValue(formals));
    _ht.put( "<body>", new JSFunctionBodyValue(body));

    // ###...etc.
  }

  // Here are constructors for the object wrappers for the other standard classes.
  // Each contains its intrinsic value in "<value>"
  // Constructor for Number object.
  public JSObjectValue( JSNumberValue dVal) {
  	this();

    _ht.put( "<value>", dVal);		// pass through null context since we know it won't be needed
    // ###...etc.
  }

  // Constructor for Boolean object.
  public JSObjectValue( JSBooleanValue bVal) {
  	this();

    _ht.put( "<value>", bVal);		// pass through null context since we know it won't be needed
    // ###...etc.
  }

  // Constructor for String object.
  public JSObjectValue( JSStringValue sVal) {
  	this();

    _ht.put( "<value>", sVal);		// pass through null context since we know it won't be needed
    // ###...etc.
  }

  // Constructor for Array object.
  public JSObjectValue( int len) {
  	this();

    _ht.put( "length", new JSNumberValue(len));
    for( int i=0; i < len; i = i+1) {
    	_ht.put( String.valueOf((double)i), new JSUndefinedValue());
    }
  }

  /////////////////////////////////////////////////////////////////////////////
  // Implementation of JSScope
  // look up a member value
  public final JSLValue get( String name, JSContext context) throws JSException {
  	return get( name, false, context);
  }

  public final JSLValue get( String name, boolean create, JSContext context) throws JSException {
  	if( isNull()) {
    	throw new JSException( "Object is null in member lookup (\"" + name + "\").", context);
    }
    else if ( !create && !_ht.containsKey(name)) {
    	throw new JSException( "Can't find member \"" + name + "\" in object.", context);
    }
    else {
	  	return new JSLValue( this, name);
    }
  }

  // create/overwrite a member value
  public final void put( String name, JSValue val, JSContext context) throws JSException {
  	if( isNull()) {
    	throw new JSException( "Object is null in member creation/write (\"" + name + "\").", context);
    }
    else {
    	_ht.put( name, val);
    }
  }

  // resolve the member's() value
  public JSValue getVal( String name, JSContext context) throws JSException {
  	JSValue val = (JSValue)_ht.get(name);

    if( val != null) {
    	return val;
    }
    else {
    	throw new JSException( "Can't find member \"" + name + "\" in object.", context);
		}
  }

  // remove a member
  public void remove( String name, JSContext context) throws JSException {
  	if( _ht.containsKey(name)) {
    	_ht.remove(name);
    }
    else {
    	throw new JSException( "Can't find member \"" + name + "\" in object.", context);
    }
  }

  // the this value
  public JSValue thisVal(JSContext context) {
  	return this;
  }

  
  // "this" value used for function invocation
  public JSValue thisValInvoke(JSContext context) {
  	return this;
  }

	//////////////////////////////////////////////////////////////////////////////
  // Local methods
  
  // check if the object is null
  public final boolean isNull() {
  	return (_ht == null);
  }

  // Invoke the given method of the object.
  public JSValue InvokeMethod( JSContext context, Vector actuals, String methodName) throws JSException {
  	if( isNull()) {
    	throw new JSException( "Object is null in method invocation (\"" + methodName + "\").", context);
    }
    JSValue val = (JSValue)_ht.get(methodName);
    if( val != null) {
     	JSFunctionValue fn = (JSFunctionValue)val.asFunction(context);
      return fn.Invoke(context, this, actuals);
    }
    else {
    	throw new JSException("No such method \"" + methodName + "\".", context);
    }
  }

	// Casting operators
	public JSValue asBoolean( JSContext context) throws JSException {
  	boolean b = true;

    if( isNull()) {
    	b = false;
    }
    else {
      try {
        JSValue ret = InvokeMethod( context, new Vector(), "valueOf");
        if( ret instanceof JSBooleanValue) {
          b = ((JSBooleanValue)ret).b();
        }
      }
      catch (JSException e) {}
    }
    return new JSBooleanValue(b);
  }

  public JSValue asNumber( JSContext context) throws JSException {
  	if( isNull()) {
    	return new JSNumberValue(0);
    }
    else {
      try {
        JSValue ret = InvokeMethod( context, new Vector(), "valueOf");
        if( ret instanceof JSNumberValue) {
          return (JSNumberValue)ret.copy(context);
        }
      }
      catch (JSException e) {}
      
      throw new JSException( "Can't convert object to number.", context);
    }
  }

  public JSValue asString( JSContext context) throws JSException {
  	return _asString(context, true);
  }

  public JSValue asObject( JSContext context) throws JSException {
  	return this;
  }

  // This possibly depart from the standard, since the spec is unclear in this
  //		regard. If the object has the three members "<name>", "<body>" and "<formals>", and
  //		they are, respectively, of types JSFunctionBodyValue and
  //		JSFunctionFormalsValue, then we create a new function value using these
  //		parameters.
  public JSValue asFunction( JSContext context) throws JSException {
  	if( isNull()) {
    	throw new JSException( "Can't convert object to function (object is null).", context);
    }
  	JSValue name = (JSValue)_ht.get("<name>");
  	JSValue body = (JSValue)_ht.get("<body>");
    JSValue formals = (JSValue)_ht.get("<formals>");

    if( (name != null) && (name instanceof JSStringValue)
    			&& (body != null) && (body instanceof JSFunctionBodyValue)
    					&& (formals != null) && (formals instanceof JSFunctionFormalsValue)) {
    	return new JSFunctionValue( ((JSStringValue)name).s(),
      																((JSFunctionFormalsValue)formals).formals(),
                                      		((JSFunctionBodyValue)body).body());
    }
    else {
      throw new JSException( "Can't convert object to function.", context);
    }
  }

  // String convertability for + and comparison operators. This is slightly
  //	different from asString() in that toString() is not attempted.
  public JSValue asStringConversion( JSContext context) throws JSException {
  	return _asString(context, false);
  }

  // Convert to a string for both normal conversions and string-convertible conversions.
  private JSValue _asString(  JSContext context, boolean tryToString) throws JSException {
  	if( isNull()) {
    	return new JSStringValue("null");
    }
    else {
    	if( tryToString) {
        try {
          JSValue ret1 = InvokeMethod( context, new Vector(), "toString");
          if( ret1 instanceof JSStringValue) {
            return (JSStringValue)ret1.copy(context);
          }
        }
      	catch (JSException e) {}
      }
      try {
        JSValue ret2 = InvokeMethod( context, new Vector(), "valueOf");
        if( ret2 instanceof JSStringValue) {
          return (JSStringValue)ret2.copy(context);
        }
      }
      catch (JSException e) {}
      
      throw new JSException( "Can't convert object to string.", context);
    }
  }


	// Type-checking operators
  public boolean isObject( JSContext context) throws JSException {
  	return true;
  }

  // typeof operator
  public String typeof( JSContext context) {
  	return "object";
  }
}

