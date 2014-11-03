/*
 * Definition of expression trees for binary expressions.
 */

package roland.javascript;

class JSBinaryExpression
  extends JSExpression
{
	static private final int _ERR = 0;
	static private final int _NUM = 1;
  static private final int _STR = 2;
  static private final int _REF = 3;
  static private final int _BOOL = 4;

  // instance vars
  private int _kind;
  private JSExpression _left;
  private JSExpression _right;

  public JSBinaryExpression( Token t, JSExpression left, JSExpression right) {
  	super(t);
  	_kind = t.kind;
    _left = left;
    _right = right;
  }

  public JSValue Eval( JSContext context) throws JSException {
    JSValue leftVal = null;
    JSValue rightVal = null;
    JSLValue lval = null;      // Non-null only when assignment is to be done
    JSValue val = null;
    double leftNum = 0;
    double rightNum = 0;
    double valNum = 0;
    String leftStr = "";
    String rightStr = "";
    String valStr = "";
    boolean valBool = false;
    int opTypes = _ERR;
    int valType = _ERR;

    setLineCol(context);
    leftVal = _left.Eval(context);
    setLineCol(context);
    
    // Get the right-hand operand, and store the l.h.s(). value of assignments to avoid
    //	r.h.s(). side-affects trashing it (see spec).
    switch(_kind) {
    	case JSParserConstants.SC_ANDASSIGN:
    	case JSParserConstants.SC_ORASSIGN:
    	case JSParserConstants.PLUSASSIGN:
    	case JSParserConstants.MINUSASSIGN:
    	case JSParserConstants.STARASSIGN:
    	case JSParserConstants.SLASHASSIGN:
    	case JSParserConstants.BIT_ANDASSIGN:
    	case JSParserConstants.BIT_ORASSIGN:
    	case JSParserConstants.XORASSIGN:
    	case JSParserConstants.REMASSIGN:
    	case JSParserConstants.LSHIFTASSIGN:
    	case JSParserConstants.RSIGNEDSHIFTASSIGN:
    	case JSParserConstants.RUNSIGNEDSHIFTASSIGN:
    	case JSParserConstants.ASSIGN:
      	if( leftVal instanceof JSLValue) {
        	lval = (JSLValue)leftVal;
        	if( _kind != JSParserConstants.ASSIGN) {
	      		leftVal = lval.Get(context);
          }
        }
        else {
        	// ### this should be a compile-time error according to the spec
        	throw new JSException( "Left-hand side of assignment is not an lvalue.", context);
        }
				// run through...

    	case JSParserConstants.GT:
    	case JSParserConstants.LT:
    	case JSParserConstants.DOT:
      case JSParserConstants.LBRACKET:
    	case JSParserConstants.EQ:
    	case JSParserConstants.LE:
    	case JSParserConstants.GE:
    	case JSParserConstants.NE:
    	case JSParserConstants.PLUS:
    	case JSParserConstants.MINUS:
    	case JSParserConstants.STAR:
    	case JSParserConstants.SLASH:
    	case JSParserConstants.BIT_AND:
    	case JSParserConstants.BIT_OR:
    	case JSParserConstants.XOR:
    	case JSParserConstants.REM:
    	case JSParserConstants.LSHIFT:
    	case JSParserConstants.RSIGNEDSHIFT:
    	case JSParserConstants.RUNSIGNEDSHIFT:
      	rightVal = _right.Eval(context);
        break;
      // Don't evaluate the r.h.s(). for logical && and || since they are early-out
    }

    // Convert the operands to appropriate types
    switch(_kind) {
    	case JSParserConstants.MINUS:
    	case JSParserConstants.STAR:
    	case JSParserConstants.SLASH:
    	case JSParserConstants.REM:
    	case JSParserConstants.LSHIFT:
    	case JSParserConstants.RSIGNEDSHIFT:
    	case JSParserConstants.RUNSIGNEDSHIFT:
    	case JSParserConstants.BIT_AND:
    	case JSParserConstants.BIT_OR:
    	case JSParserConstants.XOR:
    	case JSParserConstants.STARASSIGN:
    	case JSParserConstants.SLASHASSIGN:
    	case JSParserConstants.REMASSIGN:
    	case JSParserConstants.MINUSASSIGN:
    	case JSParserConstants.LSHIFTASSIGN:
    	case JSParserConstants.RSIGNEDSHIFTASSIGN:
    	case JSParserConstants.RUNSIGNEDSHIFTASSIGN:
    	case JSParserConstants.BIT_ANDASSIGN:
    	case JSParserConstants.BIT_ORASSIGN:
    	case JSParserConstants.XORASSIGN:
				// Both operands must be numbers
        leftNum = ((JSNumberValue)leftVal.asNumber(context)).d();
        rightNum = ((JSNumberValue)rightVal.asNumber(context)).d();
        opTypes = _NUM;
        valType = _NUM;
        break;

    	case JSParserConstants.PLUS:
    	case JSParserConstants.GT:
    	case JSParserConstants.LT:
    	case JSParserConstants.LE:
    	case JSParserConstants.GE:
    	case JSParserConstants.PLUSASSIGN:
      	// If one operand is string-convertible, then do this on strings,
        //	else on numbers
        if( leftVal.isStringConvertible(context)) {
        	leftStr = ((JSStringValue)leftVal.asStringConversion(context)).s();
          rightStr = ((JSStringValue)rightVal.asString(context)).s();
          opTypes = _STR;
          valType = _STR;
        }
        else if( rightVal.isStringConvertible(context)) {
        	rightStr = ((JSStringValue)rightVal.asStringConversion(context)).s();
          leftStr = ((JSStringValue)leftVal.asString(context)).s();
          opTypes = _STR;
          valType = _STR;
        }
        else {
          leftNum = ((JSNumberValue)leftVal.asNumber(context)).d();
          rightNum = ((JSNumberValue)rightVal.asNumber(context)).d();
          opTypes = _NUM;
          valType = _NUM;
        }
        if( ! ((_kind == JSParserConstants.PLUS) || (_kind == JSParserConstants.PLUSASSIGN)) ) {
        	valType = _BOOL;
        }
        break;

    	case JSParserConstants.EQ:
    	case JSParserConstants.NE:
      	// If both operators are objects or functions, then they are compared as
        //	pointers, otherwise if both are string-convertible, they are
        //	compared as strings, otherwise they are compared as numbers.
        if( (leftVal.isObject(context) || leftVal.isFunction(context))
        				&& (rightVal.isObject(context) || rightVal.isFunction(context)) ) {
        	opTypes = _REF;
        }
        else if( leftVal.isStringConvertible(context) && rightVal.isStringConvertible(context)) {
          leftStr = ((JSStringValue)leftVal.asStringConversion(context)).s();
          rightStr = ((JSStringValue)rightVal.asStringConversion(context)).s();
          opTypes = _STR;
        }
        else {
          leftNum = ((JSNumberValue)leftVal.asNumber(context)).d();
          rightNum = ((JSNumberValue)rightVal.asNumber(context)).d();
          opTypes = _NUM;
        }
        valType = _BOOL;
        break;

    	case JSParserConstants.SC_AND:
    	case JSParserConstants.SC_OR:
    	case JSParserConstants.SC_ANDASSIGN:
    	case JSParserConstants.SC_ORASSIGN:
    	case JSParserConstants.ASSIGN:
      	opTypes = _REF;
        valType = _REF;
        break;

    	case JSParserConstants.DOT:
      case JSParserConstants.LBRACKET:
      	// left operand must be object
        leftVal = leftVal.asObject(context);
        // right operand must be a string
        rightStr = ((JSStringValue)rightVal.asString(context)).s();
        opTypes = _REF;
        valType = _REF;
        break;
    }

    // finally perform the operation
    switch(_kind) {
    	case JSParserConstants.MINUS:
    	case JSParserConstants.MINUSASSIGN:
      	valNum = leftNum-rightNum;
        break;

    	case JSParserConstants.STAR:
    	case JSParserConstants.STARASSIGN:
      	valNum = leftNum*rightNum;
        break;

    	case JSParserConstants.SLASH:
    	case JSParserConstants.SLASHASSIGN:
      	valNum = leftNum/rightNum;
        break;

    	case JSParserConstants.REM:
    	case JSParserConstants.REMASSIGN:
      	valNum = leftNum%rightNum;
        break;

    	case JSParserConstants.LSHIFT:
    	case JSParserConstants.LSHIFTASSIGN:
      	// Implicit masking to low 5 bits of r.h.s().
      	valNum = (int)leftNum << ((int)rightNum & 31);
        break;

    	case JSParserConstants.RSIGNEDSHIFT:
    	case JSParserConstants.RSIGNEDSHIFTASSIGN:
      	// Implicit masking to low 5 bits of r.h.s().
      	valNum = (int)leftNum >> ((int)rightNum & 31);
        break;

    	case JSParserConstants.RUNSIGNEDSHIFT:
    	case JSParserConstants.RUNSIGNEDSHIFTASSIGN:
      	// Implicit masking to low 5 bits of r.h.s().
      	valNum = (int)leftNum >>> ((int)rightNum & 31);
        break;

    	case JSParserConstants.BIT_AND:
    	case JSParserConstants.BIT_ANDASSIGN:
      	valNum = (int)leftNum & (int)rightNum;
        break;

    	case JSParserConstants.BIT_OR:
    	case JSParserConstants.BIT_ORASSIGN:
      	valNum = (int)leftNum | (int)rightNum;
        break;

    	case JSParserConstants.XOR:
    	case JSParserConstants.XORASSIGN:
      	valNum = (int)leftNum ^ (int)rightNum;
        break;

    	case JSParserConstants.PLUS:
    	case JSParserConstants.PLUSASSIGN:
      	switch( opTypes) {
        	case _NUM:
          	valNum = leftNum + rightNum;
            break;
          case _STR:
          	valStr = leftStr + rightStr;
            break;
          default:
          	throw new JSException( "Internal error - invalid op types in +/+=. This should not happen.", context);
        }
        break;
        
    	case JSParserConstants.GT:
      	switch( opTypes) {
        	case _NUM:
          	valBool = (leftNum > rightNum);
            break;
          case _STR:
          	valBool = (leftStr.compareTo(rightStr) > 0);
            break;
          default:
          	throw new JSException( "Internal error - invalid op types in >. This should not happen.", context);
        }
        break;

    	case JSParserConstants.LT:
      	switch( opTypes) {
        	case _NUM:
          	valBool = (leftNum < rightNum);
            break;
          case _STR:
          	valBool = (leftStr.compareTo(rightStr) < 0);
            break;
          default:
          	throw new JSException( "Internal error - invalid op types in <. This should not happen.", context);
        }
        break;

    	case JSParserConstants.LE:
      	switch( opTypes) {
        	case _NUM:
          	valBool = (leftNum <= rightNum);
            break;
          case _STR:
          	valBool = (leftStr.compareTo(rightStr) <= 0);
            break;
          default:
          	throw new JSException( "Internal error - invalid op types in <=. This should not happen.", context);
        }
        break;

    	case JSParserConstants.GE:
      	switch( opTypes) {
        	case _NUM:
          	valBool = (leftNum >= rightNum);
            break;
          case _STR:
          	valBool = (leftStr.compareTo(rightStr) >= 0);
            break;
          default:
          	throw new JSException( "Internal error - invalid op types in >=. This should not happen.", context);
        }
        break;

    	case JSParserConstants.EQ:
      	switch( opTypes) {
        	case _NUM:
          	valBool = (leftNum == rightNum);
            break;
          case _STR:
          	valBool = (leftStr.compareTo(rightStr) == 0);
            break;
          case _REF:
          	// ### check that Java will do this as reference checking
          	valBool = leftVal.equals(rightVal);
            break;
          default:
          	throw new JSException( "Internal error - invalid op types in ==. This should not happen.", context);
        }
        break;

    	case JSParserConstants.NE:
      	switch( opTypes) {
        	case _NUM:
          	valBool = (leftNum != rightNum);
            break;
          case _STR:
          	valBool = (leftStr.compareTo(rightStr) != 0);
            break;
          case _REF:
          	// ### check that Java will do this as reference checking
          	valBool = !leftVal.equals(rightVal);
            break;
          default:
          	throw new JSException( "Internal error - invalid op types in !=. This should not happen.", context);
        }
        break;

    	case JSParserConstants.SC_AND:
    	case JSParserConstants.SC_ANDASSIGN:
      	if( !((JSBooleanValue)leftVal.asBoolean(context)).b()) {
        	val = leftVal;
        }
        else {
        	// Check that it can be converted to boolean
	      	rightVal = _right.Eval(context);
        	rightVal.asBoolean(context);
          val = rightVal;
        }
        break;

    	case JSParserConstants.SC_OR:
    	case JSParserConstants.SC_ORASSIGN:
      	if( ((JSBooleanValue)leftVal.asBoolean(context)).b()) {
        	val = leftVal;
        }
        else {
        	// Check that it can be converted to boolean
	      	rightVal = _right.Eval(context);
        	rightVal.asBoolean(context);
          val = rightVal;
        }
        break;

    	case JSParserConstants.ASSIGN:
      	val = rightVal.copy(context);
        break;

    	case JSParserConstants.DOT:
      case JSParserConstants.LBRACKET:
      	// left operand must be object
        val = ((JSObjectValue)leftVal).get( rightStr, context);
        break;

      default:
      	throw new JSException( "Unknown unary operator (" + String.valueOf(_kind) + ").", context);
    }

    // ### I'm worried about assignments where the r.h.s(). is an lval - it seems
    //	that the l.h.s. will be assigned an lval, whereas what is required is
    //	the current value of the lval

    // Construct the expression value
    switch( valType) {
			case _NUM:
      	val = new JSNumberValue(valNum);
        break;
			case _STR:
      	val = new JSStringValue(valStr);
        break;
			case _REF:
      	// already in val
        break;
			case _BOOL:
      	val = new JSBooleanValue(valBool);
        break;
      default:
       	throw new JSException( "Internal error - invalid result type in binary expression. This should not happen.", context);
    }

    // Do assignment where necessary
    if( lval != null) {
    	lval.Set(val, context);
    }

    return context._lastExpr = val;
  }
  
  public String Decompile(int indent) {
  	return "(" + _left.Decompile(indent) + ") "
                            + JSParserConstants.tokenImage[_kind]
                                + " (" + _right.Decompile(indent) + ")";
  }
}

