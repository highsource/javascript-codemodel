/*
 * Empty root for all values.
 */

package roland.javascript;

import java.lang.String;
import java.util.Vector;

abstract class JSValue
{
	// Casting operators
	public JSValue asBoolean( JSContext context) throws JSException {
  	throw new JSException( "Can't convert " + typeof(context) + " to boolean.", context);
  }
  public JSValue asNumber( JSContext context) throws JSException {
  	throw new JSException( "Can't convert " + typeof(context) + " to number.", context);
  }
  public JSValue asString( JSContext context) throws JSException {
  	throw new JSException( "Can't convert " + typeof(context) + " to string.", context);
  }
  public JSValue asObject( JSContext context) throws JSException {
  	throw new JSException( "Can't convert " + typeof(context) + " to object.", context);
  }
  public JSValue asFunction( JSContext context) throws JSException {
  	throw new JSException( "Can't convert " + typeof(context) + " to function.", context);
  }

  // String convertability for + and comparison operators.
  public JSValue asStringConversion( JSContext context) throws JSException {
  	return asString(context);
  }

	// Type-checking operators. These have to pass context and throw exceptions
  //	in the case of invalid lvalues.
	public boolean isBoolean( JSContext context) throws JSException {
  	return false;
  }
  public boolean isNumber( JSContext context) throws JSException {
  	return false;
  }
  public boolean isString( JSContext context) throws JSException {
  	return false;
  }
  public boolean isObject( JSContext context) throws JSException {
  	return false;
  }
  public boolean isFunction( JSContext context) throws JSException {
  	return false;
  }
  public boolean isUndefined( JSContext context) throws JSException {
  	return false;
  }

  // String convertability for + and comparison operators. If this is true,
  //	then asStringConversion(...) will return the string conversion...
  public boolean isStringConvertible( JSContext context) throws JSException {
  	if( isString(context) || isFunction(context)) {
    	return true;
    }
    if( isObject(context)) {
    	try {
      	if( ((JSObjectValue)this).InvokeMethod( context, new Vector(), "valueOf").isString(context)) {
        	// valueOf() returns a string
        	return true;
        }
      }
      catch( JSException e) {
      	// No such method, or null object, (or method threw exception)
      	return true;
      }
    }
    return false;
  }

  // Copy value for re-assignment. For value types this copies the value, for
  //	reference types, this just copies the reference.
  // However, all of our values are immutable, so we can return the same
  //	reference for all types except lvalue's. For lvalue's the value refered to
  //	by the lvalue is returned.
  public JSValue copy( JSContext context) throws JSException {
  	return this;
  }

  // typeof operator
  public abstract String typeof( JSContext context) throws JSException;
}

