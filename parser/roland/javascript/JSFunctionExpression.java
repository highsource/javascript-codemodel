/*
 * Definition of expression trees for function/method invocations. thisValInvoke()
 * 	of the expression lval tells us whether the function invocation came from a method
 *	or from a normal function call.
 */

package roland.javascript;

import java.util.Vector;
import java.lang.String;

class JSFunctionExpression
  extends JSExpression
{
	JSExpression _name;
  Vector _args;              // Vector of JSExpression's

  //////////////////////////////////////////////////////////////////////////////
  // Constructor
  public JSFunctionExpression( Token t, JSExpression name, Vector args) {
  	super(t);
    _name = name;
    _args = args;
  }

  //////////////////////////////////////////////////////////////////////////////
  // Extension of JSExpression
  public JSValue Eval( JSContext context) throws JSException {
  	setLineCol(context);
  	JSValue fnVal = _name.Eval(context);
  	setLineCol(context);

    Vector argVals = new Vector();
    for(int i=0; i<_args.size(); i++) {
    	argVals.addElement( ((JSExpression)_args.elementAt(i)).Eval(context));
    }

    JSValue thisVal;
    if( fnVal instanceof JSLValue) {
    	thisVal = ((JSLValue)fnVal).thisValInvoke(context);
    }
    else {
    	throw new JSException( "Can't evaluate \"this\" for function invocation - this should not happen.", context);
    }

    return context._lastExpr = ((JSFunctionValue)fnVal.asFunction(context)).Invoke( context, thisVal, argVals);
  }

  //////////////////////////////////////////////////////////////////////////////
  // Extension of JSParsedStatement
	public String Decompile(int indent) {
  	String s = _name.Decompile(indent) + "(";

    for(int i=0; i < _args.size(); i++) {
    	s += ((JSExpression)_args.elementAt(i)).Decompile(indent);
      if( i < _args.size()-1) {
      	s += ", ";
      }
    }

    s += ")";

    return s;
  }
}


