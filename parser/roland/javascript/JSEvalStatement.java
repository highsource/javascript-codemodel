/*
	Builtin "eval" function body.
 */

package roland.javascript;

import java.io.ByteArrayInputStream;

class JSEvalStatement
	implements JSStatement
{
  public void Exec( JSContext context) throws JSException, JSReturnException {
  	JSValue val = context.get("val").copy(context);

    if( val.isString(context)) {
    	JSParser parser = new JSParser( new ByteArrayInputStream(((JSStringValue)val).s().getBytes()));
      try {
	      JSStatement stmt = parser.Program();
        // eval() calls are actually evaluated in the caller's() scope
        context.popCall();
        context._lastExpr = new JSUndefinedValue();
        try {
	        stmt.Exec(context);
        }
        catch( JSReturnException e) {
        	throw new JSException( "Top-level \"return\" in \"eval()\" function.", context);
        }
        catch( JSBreakException e) {
        	throw new JSException( "Top-level \"break\" in \"eval()\" function.", context);
        }
        catch( JSContinueException e) {
        	throw new JSException( "Top-level \"continue\" in \"eval()\" function.", context);
        }
        // push a dummy call since it will be popped by the calling routine
        context.pushCall(new JSCall());
        // Get the value of the last executed expression.
        throw new JSReturnException( context._lastExpr.copy(context));
      }
      catch( ParseError e) {
      	throw new JSException( "Parse error in argument to \"eval()\" function: " + e.toString(), context);
      }
    }
    else {
    	throw new JSReturnException( val);
    }
  }

  public String Decompile(int indent) {
  	return "<internal fn>";
  }
}