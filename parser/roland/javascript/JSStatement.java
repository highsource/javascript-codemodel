/*
 * Expression tree's for javascript statements.
 */

package roland.javascript;

import java.lang.String;

interface JSStatement
{
  public void Exec( JSContext context) throws JSException, JSReturnException, JSBreakException, JSContinueException;
  public String Decompile(int indent);
}

