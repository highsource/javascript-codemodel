/*
 * A routine for testing the javascript engine.
 */

package roland.javascript;

class JSTester
{
  public static void main(String args[]) {
    JSRuntime runtime;
    java.io.InputStream stream;
    if (args.length == 0) {
      System.out.println("Javascript Parser Version 0.1:  Reading from standard input . . .");
      runtime = new JSRuntime();
      stream = new java.io.DataInputStream(System.in);
    }
    else if (args.length == 1) {
      System.out.println("Javascript Parser Version 0.1:  Reading from file " + args[0] + " . . .");
      try {
	      runtime = new JSRuntime();
        stream = new java.io.DataInputStream(new java.io.FileInputStream(args[0]));
      }
      catch (java.io.FileNotFoundException e) {
        System.out.println("Javascript Tester Version 0.1:  File " + args[0] + " not found.");
        return;
      }
    }
    else {
      System.out.println("Javascript Tester Version 0.1:  Usage is one of:");
      System.out.println("         java roland.javascript.JSTester < inputfile");
      System.out.println("OR");
      System.out.println("         java roland.javascript.JSTester inputfile");
      return;
    }
    runtime.Run(stream);
  }
}
