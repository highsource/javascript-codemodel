/*
 * Definition of floating point values.
 */

package roland.javascript;

import java.lang.String;
import java.lang.Double;

class JSNumberValue
  extends JSValue
{
  private double _d;

  public JSNumberValue( double dval) {
    _d = dval;
  }

  public double d() {
  	return _d;
  }

	// Casting operators
	public JSValue asBoolean( JSContext context) throws JSException {
  	return new JSBooleanValue( (_d != 0.0) && ( !Double.isNaN(_d) ) );
  }

  public JSValue asNumber( JSContext context) throws JSException {
  	return this;
  }

  public JSValue asString( JSContext context) throws JSException {
  	String s = String.valueOf(_d);
    // Remove the trailing ".0" if this is an integer
    if( (s.length() > 2) && (s.substring(s.length()-2).equals(".0"))) {
    	s = s.substring(0, s.length()-2);
    }
  	return new JSStringValue(s);
  }

  public JSValue asObject( JSContext context) throws JSException {
  	return new JSObjectValue(this);
  }

	// Type-checking operators
  public boolean isNumber( JSContext context) throws JSException {
  	return true;
  }

  // typeof operator
  public String typeof( JSContext context) {
  	return "number";
  }
}

