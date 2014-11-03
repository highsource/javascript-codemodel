/*
 * The return statement.
 */

package roland.javascript;

import java.lang.String;

class JSReturnStatement
	extends JSParsedStatement
{
	private JSExpression _ret;	// null if no return

  // "ret" is null if there is no return value
  public JSReturnStatement( Token t, JSExpression ret) {
  	super(t);
    _ret = ret;
  }

  public void Exec( JSContext context)  throws JSException, JSReturnException {
  	JSValue retVal;

  	setLineCol(context);
  	if( _ret != null) {
    	retVal = _ret.Eval(context);
    }
    else {
    	retVal = new JSUndefinedValue();
    }

    throw new JSReturnException( retVal);
  }
  public String Decompile(int indent) {
  	String s = Indent(indent) + "return ";
    if( _ret != null) {
    	s += _ret.Decompile(indent);
    }
    return s;
  }
}