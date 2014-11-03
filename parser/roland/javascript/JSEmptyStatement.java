/*
 * A completely empty statement (";") which does nothing.
 */

package roland.javascript;

import java.lang.String;

class JSEmptyStatement
	extends JSParsedStatement
{
  public JSEmptyStatement( Token t) {
  	super(t);
  }

  public void Exec( JSContext context) {
  	setLineCol(context);
  	// do nothing
  }
  public String Decompile(int indent) {
  	return Indent(indent) + ";";
  }
}
