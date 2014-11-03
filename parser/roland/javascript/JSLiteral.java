/*
 * Definition of expression tree for a literal.
 */

package roland.javascript;

import java.lang.String;
import java.lang.Double;
import java.util.Hashtable;

class JSLiteral
  extends JSExpression
{
  private JSValue _val;
  private int _kind;

  public JSLiteral( Token t) {
  	super(t);
  	_kind = t.kind;
    switch(_kind) {
    	case JSParserConstants.FLOATING_POINT_LITERAL:
    	case JSParserConstants.INTEGER_LITERAL:
      	_val = new JSNumberValue( Double.valueOf(t.image).doubleValue());
        break;
      case JSParserConstants.STRING_LITERAL:
      	_val = new JSStringValue( t.image.substring(1, t.image.length()-1));    
        break;
      case JSParserConstants.BOOLEAN_LITERAL:
      	_val = new JSBooleanValue( t.image == "true");
        break;
      case JSParserConstants.NULL_LITERAL:
      	_val = new JSObjectValue((Hashtable)null);
        break;
    }
  }

  public JSValue Eval( JSContext context) {
    return context._lastExpr = _val;
  }

  public String Decompile(int indent) {
    try {
    	return ((JSStringValue)_val.asString(null)).s();			// ### Pass through a null context since
      																											//	 we shouldn't need it for a literal.
    }
    catch( JSException e) {}

    return "<We should never get here - error converting literal to string.>";
  }
}
