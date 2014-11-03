/*
 * The break statement.
 */

package roland.javascript;

import java.lang.String;

class JSBreakStatement
	extends JSParsedStatement
{
  public JSBreakStatement( Token t) {
  	super(t);
  }

  public void Exec( JSContext context) throws JSBreakException {
  	setLineCol(context);
  	throw new JSBreakException();
  }
  public String Decompile(int indent) {
  	return Indent(indent) + "break";
  }
}