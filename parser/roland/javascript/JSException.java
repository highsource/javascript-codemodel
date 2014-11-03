/*
   Exception which can report the line number and column on which it occurred,
   and what went wrong.
 */


package roland.javascript;

import java.lang.Exception;
import java.lang.String;

class JSException
  extends Exception
{
  public int line;
  public int col;

  public JSException( String mess, int lineInit, int colInit) {
     super(mess);
     line = lineInit;
     col = colInit;
  }

  public JSException( String mess, JSContext context) {
  	this( mess, context.getLine(), context.getCol());
  }

  public String toString() {
  	return "[line " + String.valueOf(line) + ", column " + String.valueOf(col) + "] " + super.toString();
  }
}

