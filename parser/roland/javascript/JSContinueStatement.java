/*
 * The continue statement.
 */

package roland.javascript;

import java.lang.String;

class JSContinueStatement
	extends JSParsedStatement
{
  public JSContinueStatement( Token t) {
  	super(t);
  }

  public void Exec( JSContext context) throws JSContinueException {
  	setLineCol(context);
  	throw new JSContinueException();
  }
  public String Decompile(int indent) {
  	return Indent(indent) + "continue";
  }
}