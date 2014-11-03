/*
 * Definition of expression tree for ?: operator.
 */

package roland.javascript;

class JSConditionalExpression
  extends JSExpression
{
  private JSExpression _cond;
  private JSExpression _then;
  private JSExpression _else;

  public JSConditionalExpression( Token t, JSExpression cond, JSExpression __then, JSExpression __else) {
  	super(t);
  	_cond = cond;
    _then = __then;
    _else = __else;
  }

  public JSValue Eval( JSContext context) throws JSException {
  	JSValue condVal = _cond.Eval(context);
    if( ((JSBooleanValue)condVal.asBoolean(context)).b()) {
    	return context._lastExpr = _then.Eval(context);
    }
    else {
    	return context._lastExpr = _else.Eval(context);
    }
  }
  
  public String Decompile(int indent) {
  	return "(" + _cond.Decompile(indent) + ") ? "
                            + "(" + _then.Decompile(indent) + ") : "
                                + " (" + _else.Decompile(indent) + ")";
  }
}

