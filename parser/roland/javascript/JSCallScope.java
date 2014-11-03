/*
	The local scope for a function invocation
 */

package roland.javascript;

import java.util.Hashtable;

class JSCallScope
	implements JSScope
{
  Hashtable _ht;

  /////////////////////////////////////////////////////////////////////////////
  // Constructor
  public JSCallScope() {
  	_ht = new Hashtable();
  }

  /////////////////////////////////////////////////////////////////////////////
  // Implementation of JSScope.
  // This is identical to JSObjectValue's implementation, but without multiple
  //	inheritance we can't really do much better
  // look up a member value
  public final JSLValue get( String name, JSContext context) throws JSException {
  	return get( name, false, context);
  }

  public final JSLValue get( String name, boolean create, JSContext context) throws JSException {
  	if ( !create && !_ht.containsKey(name)) {
    	throw new JSException( "Local variable \"" + name + "\" does not exist.", context);
    }
    else {
	  	return new JSLValue( this, name);
    }
  }

  // create/overwrite a member value
  public final void put( String name, JSValue val, JSContext context) {
	 	_ht.put( name, val);
  }

  // resolve the member's value
  public JSValue getVal( String name, JSContext context) throws JSException {
  	JSValue val = (JSValue)_ht.get(name);

    if( val != null) {
    	return val;
    }
    else {
    	throw new JSException( "Can't find member \"" + name + "\" in object.", context);
		}
  }

  // remove a member
  public void remove( String name, JSContext context) throws JSException {
  	if( _ht.containsKey(name)) {
    	_ht.remove(name);
    }
    else {
    	throw new JSException( "Can't find member \"" + name + "\" in object.", context);
    }
  }

  // the this value
  public JSValue thisVal(JSContext context) {
  	return null;
  }


  // the "this" value used for function invocation.
  public JSValue thisValInvoke(JSContext context) {
  	return null;
  }
}


