/*
 * Definition of expression trees.
 */

package roland.javascript;

abstract class JSExpression
  extends JSParsedStatement
{
  JSExpression( Token t) {
  	super( t.beginLine, t.beginColumn);
  }

  public void Exec( JSContext context) throws JSException {
    Eval(context);
  }
  public abstract JSValue Eval( JSContext context) throws JSException;
}


