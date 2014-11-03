/*
 * Javascript while statement.
 */

package roland.javascript;

import java.lang.String;

class JSWhileStatement
	extends JSParsedStatement
{
	private JSExpression _cond;
  private JSStatement _body;

  public JSWhileStatement( Token t, JSExpression cond, JSStatement body) {
  	super(t);
    _cond = cond;
    _body = body;
  }

  public void Exec( JSContext context) throws JSException, JSReturnException {
  	setLineCol(context);
    // ### Check semantics here
  	while( ((JSBooleanValue)_cond.Eval(context).asBoolean(context)).b()) {
    	try {
    		_body.Exec(context);
      }
      catch( JSContinueException e) {}
      catch( JSBreakException e) {
      	break;
      }
    }
  }
  public String Decompile(int indent) {
  	return Indent(indent) + "while(" + _cond.Decompile(indent) + " ) " + _body.Decompile(indent + 2);
  }
}
