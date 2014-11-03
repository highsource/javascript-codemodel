/*
 * Compound statement, a container for a sequence of substatements.
 */

package roland.javascript;

import java.lang.String;
import java.util.Vector;

class JSCompoundStatement
	extends JSParsedStatement
{
	private Vector _v;		// Vector of JSStatements

  public JSCompoundStatement( Token t) {
  	super(t);
    _v = new Vector();
  }

  public void add( JSStatement s) {
  	_v.addElement(s);
  }

  public void Exec( JSContext context) throws JSException, JSReturnException, JSBreakException, JSContinueException {
  	setLineCol(context);
  	for( int i=0; i<_v.size(); i++) {
    	((JSStatement)_v.elementAt(i)).Exec(context);
    }
  }
  public String Decompile(int indent) {
  	String s = Indent(indent) + "{\n";
  	for( int i=0; i<_v.size(); i++) {
    	s += ((JSStatement)_v.elementAt(i)).Decompile(indent+2);
    }
		s += "\n" + Indent(indent) + "}";
    return s;
  }
}
