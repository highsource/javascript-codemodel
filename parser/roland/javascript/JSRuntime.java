/*
	Runtime that can execute Javascript strings.
 */

package roland.javascript;

import java.util.Vector;

class JSRuntime
{
	private JSParser _parser;
  private JSContext _context;

  public JSRuntime() {
  	// We initialise the parser with stdin, which is ignored, since the
    //	constructor requires an input stream
  	_parser = new JSParser(new java.io.DataInputStream(System.in));

    // The global scope is actually an object value.
    JSObjectValue globalScopeObject = new JSObjectValue();

    // ### add all of the standard global Constructors and objects

    // writeln()
    try {
      Vector vWriteln = new Vector();
      vWriteln.addElement("s");
      globalScopeObject.put( "writeln", new JSFunctionValue( "writeln", vWriteln, new JSWritelnStatement()), null);
    }
    catch (JSException e) { /* this should not happpen */ }

    // eval()
    try {
    	Vector vEval = new Vector();
      vEval.addElement("val");
      globalScopeObject.put( "eval", new JSFunctionValue( "eval", vEval, new JSEvalStatement()), null);
    }
    catch (JSException e) { /* this should not happpen */ }

    // parseInt()
    try {
    	Vector vParseInt = new Vector();
      vParseInt.addElement("string");
      vParseInt.addElement("radix");
      globalScopeObject.put( "parseInt", new JSFunctionValue( "parseInt", vParseInt, new JSParseIntStatement()), null);
    }
    catch (JSException e) { /* this should not happpen */ }

    JSGlobalScope globalScope = new JSGlobalScope( globalScopeObject);

    _context = new JSContext( globalScope);
  }

  public void Run( java.io.InputStream stream) {
  	_parser.ReInit(stream);
    try {
    	JSStatement stmt = _parser.Program();

      stmt.Exec(_context);

      System.out.println("Cygnet Javascript Version 0.1:  Javascript program parsed and executed successfully.");
    }
    catch( ParseError e) {
      System.out.println("Cygnet Javascript Version 0.1:  Syntax error: " + e.toString());
    }
    catch( JSException e) {
      System.out.println("Cygnet Javascript Version 0.1:  Error: " + e.toString());
    }
    catch( JSBreakException e) {
    	// ### This should be a compile-time error
      System.out.println("Cygnet Javascript Version 0.1:  Error: Top level \"break\".");
    }
    catch( JSContinueException e) {
    	// ### This should be a compile-time error
      System.out.println("Cygnet Javascript Version 0.1:  Error: Top level \"continue\".");
    }
    catch( JSReturnException e) {
    	// ### This should be a compile-time error
      System.out.println("Cygnet Javascript Version 0.1:  Error: Top level \"return\".");
    }
  }

  public void Run( java.io.InputStream stream, JSScope extraGlobalScope) {
  	_context.pushGlobalScope( extraGlobalScope);

    Run( stream);

    _context.popGlobalScope();
  }
}

