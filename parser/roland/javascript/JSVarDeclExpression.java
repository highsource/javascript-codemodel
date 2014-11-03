/*
 * Definition of expression tree for a variable declaration expression.
 */

package roland.javascript;

import java.util.Vector;
import java.lang.String;

class JSVarDeclExpression
  extends JSExpression
{
  private Vector _v;        // of JSVarDecl's

  public JSVarDeclExpression( Token t, Vector v) {
  	super(t);
  	_v = v;
  }

  public JSValue Eval( JSContext context) throws JSException {
  	JSValue val = null;

    for( int i=0; i < _v.size(); i++) {
    	JSVarDecl d = (JSVarDecl)_v.elementAt(i);
      JSExpression e = d.getInit();

      if(e != null) {
      	val = e.Eval(context);
      }
      else {
      	val = new JSUndefinedValue();
      }

      JSLValue lval = context.getLocal( d.getName());

      lval.Set(val, context);
    }

    return context._lastExpr = val;
  }

  public String Decompile(int indent) {
  	String s = Indent(indent) + "var ";
    for(int i=0; i < _v.size(); i++) {
    	JSVarDecl d = (JSVarDecl)_v.elementAt(i);

    	s += d.getName();
      if( d.getInit() != null) {
      	s += " = " + d.getInit().Decompile(indent+2);
      }
    }
    return s;
  }
}
