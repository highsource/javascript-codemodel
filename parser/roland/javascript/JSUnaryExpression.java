/*
 * Definition of expression tree for a unary expression.
 */

package roland.javascript;

class JSUnaryExpression
  extends JSExpression
{
  private int _kind;
  private JSExpression _sub;
  private boolean _prefix;

  public JSUnaryExpression( Token t, JSExpression sub, boolean prefix) {
  	super(t);
  	_kind = t.kind;
    _sub = sub;
    _prefix = prefix;
  }

  public JSUnaryExpression( Token t, JSExpression sub) {
  	this(t, sub, true);
  }

  public JSValue Eval( JSContext context) throws JSException {
    JSValue subVal;
    JSValue val;

  	setLineCol(context);
    subVal = _sub.Eval(context);
    setLineCol(context);

    switch(_kind) {
    	case JSParserConstants.BANG:
      	val = new JSBooleanValue( !((JSBooleanValue)subVal.asBoolean(context)).b());
        break;
    	case JSParserConstants.TILDE:
      	val = new JSNumberValue( ~((int)((JSNumberValue)subVal.asNumber(context)).d()));
        break;
    	case JSParserConstants.INCR:
    	case JSParserConstants.DECR:
				if( subVal instanceof JSLValue) {
        	JSLValue lval = (JSLValue)subVal;
          JSValue incVal;

        	if(!_prefix) {
          	val = lval.copy(context);
          }
          else {
          	val = null; // fool the compiler that val is init'ed
          }
          
          incVal = new JSNumberValue( ((JSNumberValue)lval.asNumber(context)).d() + ((_kind == JSParserConstants.INCR) ? 1 : -1));
          lval.Set( incVal, context);
          if(_prefix) {
          	val = incVal;
          }
        }
        else {
        	throw new JSException( "Operand of ++/-- is not an lvalue.", context);
        }
        break;
    	case JSParserConstants.MINUS:
      	val = new JSNumberValue( -((JSNumberValue)subVal.asNumber(context)).d());
        break;
    	case JSParserConstants.NEW:
      	// We treat a new call as a member invoke on the newly
        //	constructed object.
        val = new JSObjectValue();
        // ### need to indicate that the call is a constructor in the JSContext somehow
        // ### get back to this
        break;
    	case JSParserConstants.DELETE:
				if( subVal instanceof JSLValue) {
        	((JSLValue)subVal).Delete(context);
          val = new JSUndefinedValue();
        }
        else {
        	throw new JSException( "Operand of delete is not an lvalue.", context);
        }
        break;
    	case JSParserConstants.TYPEOF:
      	val = new JSStringValue( subVal.typeof(context));
        break;
    	case JSParserConstants.VOID:
      	val = new JSUndefinedValue();
        break;
      default:
      	throw new JSException( "Unknown unary operator (" + String.valueOf(_kind) + ").", context);
    }
    return context._lastExpr = val;
  }

  public String Decompile(int indent) {
  	if( _prefix) {
	  	return JSParserConstants.tokenImage[_kind] + "(" + _sub.Decompile(indent) + ")";
    }
    else {
	  	return "(" + _sub.Decompile(indent) + ")" + JSParserConstants.tokenImage[_kind];
    }
  }
}

