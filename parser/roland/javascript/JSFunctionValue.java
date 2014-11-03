/*
	A value representing a function. The function is characterised by its
  name, its parameter list, and by its (executable) body.              
 */

package roland.javascript;

import java.util.Vector;

class JSFunctionValue
	extends JSValue
{
	private String _name;
	// Vector of Strings (parameter names)
	private Vector _formals;
	private JSStatement _body;

  public JSFunctionValue( String name, Vector formals, JSStatement body) {
  	_name = name;
  	_formals = formals;
    _body = body;
  }

  // Takes a Vector of JSValue's to place in the formal parameters.
  // Returns the result of the function.
  public JSValue Invoke( JSContext context, Vector actuals) throws JSException {
  	return Invoke( context, null, actuals);
  }

  public JSValue Invoke( JSContext context, JSValue thisVal, Vector actuals) throws JSException {
  	JSValue retVal;
    
  	// Create a call environment
    JSCall call = new JSCall( thisVal);
    // Create the arguments array - this is empty if there are fewer actuals than formals
    int nargs = actuals.size();
    JSObjectValue args = new JSObjectValue( nargs);
    call.put( "arguments", args, context);

    // Instantiate the formals
    for( int i=0; i < _formals.size(); i++) {
    	JSValue arg;
    	if( i < actuals.size()) {
      	arg = (JSValue)actuals.elementAt(i);
        args.put( String.valueOf((double)i), arg, context);
      }
      else {
      	arg = new JSUndefinedValue();
      }
      call.put( (String)_formals.elementAt(i), arg, context);
    }

    // Put the remainder into "arguments"
    for( int i = _formals.size(); i < nargs; i = i+1) {
    	args.put( String.valueOf((double)i), (JSValue)actuals.elementAt(i), context);
    }

    // Push the new call onto the call stack.
    context.pushCall(call);

    // Execute the body.
    try {
    	_body.Exec(context);
      retVal = new JSUndefinedValue();
    }
    catch (JSReturnException e) {
    	retVal = e.retVal;
    }
    catch (JSBreakException e) {
    	// ### not sure that this should get through here
      retVal = new JSUndefinedValue();
    }
    catch (JSContinueException e) {
    	// ### not sure that this should get through here
      retVal = new JSUndefinedValue();
    }

    // Pop the call
    context.popCall();

    // Return the return value
    return retVal;
  }

	// Casting operators
	public JSValue asBoolean( JSContext context) throws JSException {
  	// spec says valueOf() or "true" - I can't see when there would ever be a valueOf()
    //	unless this started out as a Function Object, in which case this would
    //	be handled by JSObjectValue.asBoolean()
    return new JSBooleanValue(true);
  }
  
  //public JSValue asNumber( JSContext context) throws JSException {
  	// spec says valueOf() or error - I can't see when there would ever be a valueOf()
    //	unless this started out as a Function Object, in which case this would
    //	be handled by JSObjectValue.asBoolean()
    //throw new JSException( "Can't convert function to number.", context);
  //}

  public JSValue asString( JSContext context) throws JSException {
  	String decomp = "function " + _name + "(";

    for(int i=0; i<_formals.size(); i++) {
    	decomp += (String)_formals.elementAt(i);
      if( i != _formals.size()-1) {
      	decomp += ", ";
      }
    }

    decomp += ") {\n" + _body.Decompile(1) + "}\n";

  	return new JSStringValue( decomp);
  }

  public JSValue asObject( JSContext context) throws JSException {
  	return new JSObjectValue( _name, _formals, _body);
  }
  
  public JSValue asFunction( JSContext context) throws JSException {
  	return this;
  }

	// Type-checking operators
  public boolean isFunction( JSContext context) throws JSException {
  	return true;
  }

  // typeof operator
  public String typeof( JSContext context) {
  	return "function";
  }
}
