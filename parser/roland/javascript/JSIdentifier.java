/*
 * Definition of expression tree for an identifier literal.
 */

package roland.javascript;

import java.lang.String;

class JSIdentifier
  extends JSExpression
{
	private String _name;

  public JSIdentifier( Token t) {
  	super(t);
    _name = t.image;
  }

  public JSValue Eval( JSContext context) throws JSException {
  	return context._lastExpr = context.get(_name);
  }
  
  public String Decompile(int indent) {
  	return _name;
  }
}
