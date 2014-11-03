/*
 * Javascript if-then(-else) statement.
 */

package roland.javascript;

import java.lang.String;

class JSIfStatement
	extends JSParsedStatement
{
	private JSExpression _cond;
  private JSStatement _then;
  private JSStatement _else;	// null if there is no else clause

  public JSIfStatement( Token t, JSExpression cond, JSStatement __then, JSStatement __else) {
  	super(t);
    _cond = cond;
    _then = __then;
    _else = __else;
  }

  public void Exec( JSContext context) throws JSException, JSReturnException, JSBreakException, JSContinueException {
  	setLineCol(context);
    // ### Check semantics here
  	if( ((JSBooleanValue)_cond.Eval(context).asBoolean(context)).b()) {
    	_then.Exec(context);
    }
    else if( _else != null) {
    	_else.Exec(context);
    }
  }
  public String Decompile(int indent) {
  	String s = Indent(indent) + "if(" + _cond.Decompile(indent) + " ) ";
    s += _then.Decompile(indent + 2);
    if( _else != null) {
    	s += "\n" + Indent(indent) + "else " + _else.Decompile(indent+2);
    }
    return s;
  }
}
