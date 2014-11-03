/*
	Variable declaration.
 */

package roland.javascript;

import java.lang.String;

class JSVarDecl
{
	private String _name;
  private JSExpression _init;			// null if none
  private int _line;
  private int _col;

  public JSVarDecl( Token t, JSExpression init) {
  	_name = t.image;
    _init = init;
  	_line = t.beginLine;
    _col = t.beginColumn;
  }

  public final String getName() {
  	return _name;
  }

  public final JSExpression getInit() {
  	return _init;
  }

  public void setLineCol( JSContext context) {
  	context.setLineCol( _line, _col);
  }
}
