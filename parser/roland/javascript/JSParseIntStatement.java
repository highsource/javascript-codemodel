/*
	Builtin "parseInt" function body.
 */

package roland.javascript;

import java.lang.String;
import java.lang.Integer;
import java.lang.Double;

class JSParseIntStatement
	implements JSStatement
{
  public void Exec( JSContext context) throws JSException, JSReturnException {
  	String string = ((JSStringValue)context.get("string").asString(context)).s();
    int radix;
  	JSObjectValue args = (JSObjectValue)context.get("arguments").copy(context);
    int nArgs = (int)((JSNumberValue)args.get("length",context).copy(context)).d();
    if(nArgs > 1) {
    	radix = (int)((JSNumberValue)context.get("radix").asNumber(context)).d();
    }
    else {
      radix = 10;
    }

    // Check for an radix defined by a prefix of the string arg
    if( (string.length() > 1) && (string.charAt(0) == '0')) {
    	// This copies NS's approach of returning NaN for "0x"
      if( (string.charAt(1) == 'x') || (string.charAt(1) == 'X')) {
        radix = 16;
        string = string.substring(2);
      }
      else {
        radix = 8;
        string = string.substring(1);
      }
    }

    // Now extract the valid leading substring of the string
    int len;
    for( len = 0; len < string.length(); len++) {
    	int ch = string.charAt(len);
    	if( ((ch >= '0') && (ch <= '9'))
      		|| ((ch >= 'a') && (ch-'a' < radix-10))
      		|| ((ch >= 'A') && (ch-'A' < radix-10)) ) {
      	continue;
      }
      else {
      	break;
      }
    }
    string = string.substring(0, len);

    double d = Double.NaN;

    try {
      d = (double) Integer.parseInt(string, radix);
    }
    catch( NumberFormatException e) { /* Run through to end */ }

    throw new JSReturnException(new JSNumberValue(d));
  }

  public String Decompile(int indent) {
  	return "<internal fn>";
  }
}
