/*
 * Definition of string value.
 */

package roland.javascript;

import java.lang.String;
import java.lang.Double;
import java.lang.NumberFormatException;

class JSStringValue
  extends JSValue
{
  private String _s;

  public JSStringValue( String string) {
    _s = string;
  }

  public String s() {
  	return _s;
  }

	// Casting operators
	public JSValue asBoolean( JSContext context) throws JSException {
  	return new JSBooleanValue( _s != "");
  }

  public JSValue asNumber( JSContext context) throws JSException {
  	try {
    	return new JSNumberValue( Double.valueOf(_s).doubleValue());
    }
    catch( NumberFormatException e) {
    	throw new JSException( "Invalid number format converting string (" + _s + ") to number.", context);
    }
  }

  public JSValue asString( JSContext context) throws JSException {
  	return this;
  }

  public JSValue asObject( JSContext context) throws JSException {
  	return new JSObjectValue( this);
  }

	// Type-checking operators
  public boolean isString( JSContext context) throws JSException {
  	return true;
  }

  // typeof operator
  public String typeof( JSContext context) {
  	return "string";
  }
}
