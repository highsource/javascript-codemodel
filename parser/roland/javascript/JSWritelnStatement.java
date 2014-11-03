/*
	Debug statement that writes to stdout.
 */

package roland.javascript;

class JSWritelnStatement
	implements JSStatement
{
  public void Exec( JSContext context) throws JSException {
  	System.out.println( ((JSStringValue)context.get("s").asString(context)).s());
  }

  public String Decompile(int indent) {
  	return "<internal fn>";
  }
}
