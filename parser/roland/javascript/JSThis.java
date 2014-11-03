/*
 * Definition of expression tree for a "this" literal.
 */

package roland.javascript;

class JSThis
  extends JSExpression
{
  public JSThis( Token t) {
  	super(t);
  }

  public JSValue Eval( JSContext context) throws JSException {
  	return context._lastExpr = context.thisVal();
  }
  
  public String Decompile(int indent) {
  	return "this";
  }
}
  

