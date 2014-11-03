/*
 * Javascript for statement.
 */

package roland.javascript;

import java.lang.String;

class JSForStatement
	extends JSParsedStatement
{
	private JSExpression _init;
	private JSExpression _cond;                    
	private JSExpression _next;
  private JSStatement _body;

  public JSForStatement( Token t, JSExpression init, JSExpression cond, JSExpression next, JSStatement body) {
  	super(t);
    _init = init;
    _cond = cond;
    _next = next;
    _body = body;
  }

  public void Exec( JSContext context) throws JSException, JSReturnException {
  	JSValue dummy;
    
  	setLineCol(context);
    for( dummy = ((_init != null) ? _init.Eval(context) : null);
         		(_cond != null) ? ((JSBooleanValue)_cond.Eval(context).asBoolean(context)).b() : true;
	            	dummy = ((_next != null) ? _next.Eval(context) : null) ) {
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
  	String s = Indent(indent) + "for(";
    if( _init != null) {
    	s += _init.Decompile(indent);
    }
    s += ";";
    if( _cond != null) {
    	s += _cond.Decompile(indent);
    }
    s += ";";
    if( _next != null) {
    	s += _next.Decompile(indent);
    }
    _body.Decompile(indent + 2);

    return s;
  }
}