/*
	Javascript undefined value.
 */

package roland.javascript;

import java.util.Hashtable;

class JSUndefinedValue
	extends JSValue
{
	// Casting operators
	public JSValue asBoolean( JSContext context) throws JSException {
  	return new JSBooleanValue( false);
  }

  public JSValue asString( JSContext context) throws JSException {
  	return new JSStringValue( "undefined");
  }

  public JSValue asObject( JSContext context) throws JSException {
  	return new JSObjectValue((Hashtable)null);
  }

	// Type-checking operators
  public boolean isUndefined( JSContext context) throws JSException {
  	return true;
  }

  // typeof operator
  public String typeof( JSContext context) {
  	return "undefined";
  }
}
