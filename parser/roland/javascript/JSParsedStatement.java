/*
	Abstract class that takes care of the line and column positions of parsed
  elements.
 */

package roland.javascript;

import java.lang.String;

abstract class JSParsedStatement
	implements JSStatement
{
	private int _line;
  private int _col;

  JSParsedStatement( int line, int col) {
  	_line = line;
    _col = col;
  }

  JSParsedStatement( Token t) {
  	this( t.beginLine, t.beginColumn);
  }

  public abstract void Exec( JSContext context) throws JSException, JSReturnException, JSBreakException, JSContinueException;
  public abstract String Decompile(int indent);

  protected void setLineCol( JSContext context) {
  	context.setLineCol( _line, _col);
  }

  String Indent( int indent) {
  	String s = new String();
    for( int i=0; i<indent; i++) {
    	s += " ";
    }
    return s;
  }
}
