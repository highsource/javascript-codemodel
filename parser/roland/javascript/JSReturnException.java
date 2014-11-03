/*
   Exception thrown by a return statement. It is caught by the function/method
   invoker.
 */


package roland.javascript;

import java.lang.Exception;

class JSReturnException
  extends Exception
{
	public JSValue retVal;

  public JSReturnException( JSValue val) {
  	retVal = val;
  }
}