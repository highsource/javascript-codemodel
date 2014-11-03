/*
 * Definition of expression trees.
 */

package roland.javascript;

import java.lang.String;

class JSBooleanValue
  extends JSValue
{
  private boolean _b;

  public JSBooleanValue( boolean bval) {
    _b = bval;
  }

  public final boolean b() {
  	return _b;
  }

	// Casting operators
	public JSValue asBoolean( JSContext context) throws JSException {
  	return this;
  }

  public JSValue asNumber( JSContext context) throws JSException {
  	return new JSNumberValue( _b ? 1 : 0);
  }

  public JSValue asString( JSContext context) throws JSException {
  	return new JSStringValue( String.valueOf(_b) );
  }

  public JSValue asObject( JSContext context) throws JSException {
  	return new JSObjectValue( this);
  }

	// Type-checking operators
	public boolean isBoolean( JSContext context) throws JSException {
  	return true;
  }

  // typeof operator
  public String typeof( JSContext context) {
  	return "boolean";
  }
}

