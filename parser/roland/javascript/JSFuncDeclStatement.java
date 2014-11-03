/*
	Statement for a function declaration. The statement creates the appropriate
  (global) function when executed.
 */

package roland.javascript;

import java.lang.String;
import java.util.Vector;

class JSFuncDeclStatement
	extends JSParsedStatement
{
	private String _name;
  private Vector _formals;	// Vector of String's
  private JSStatement _body;

  public JSFuncDeclStatement( Token t, String name, Vector formals, JSStatement body) {
  	super(t);
    _name = name;
    _formals = formals;
    _body = body;
  }

  public void Exec( JSContext context) throws JSException {
  	setLineCol(context);
    context.putGlobal( _name, new JSFunctionValue( _name, _formals, _body));
  }
  
  public String Decompile(int indent) {
  	String s = Indent(indent) + "function " + _name + "(";
    for(int i=0; i < _formals.size(); i++) {
    	s += (String)_formals.elementAt(i);
      if( i != _formals.size()-1) {
      	s += ", ";
      }
    }
    s += ") {\n" + _body.Decompile( indent+2) + "\n" + Indent(indent) + "}";

    return s;
  }
}

    

