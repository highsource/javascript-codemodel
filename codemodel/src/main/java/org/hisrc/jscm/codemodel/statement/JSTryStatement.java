package org.hisrc.jscm.codemodel.statement;

import org.hisrc.jscm.codemodel.expression.JSVariable;

public interface JSTryStatement extends JSStatement {

	public JSBlock getBody();

	public JSBlock getCatch();

	public JSBlock getFinally();

	public JSVariable getException();

	public interface Try {
		
		public JSBlock getBody();
		public TryCatch _catch(String identifier);
		public TryFinally _finally();
		
	}
	
	public interface TryCatch extends JSTryStatement {
		public JSBlock getCatch();
		public TryCatchFinally _finally();
	}
	
	public interface TryCatchFinally extends JSTryStatement {
		public JSBlock getCatch();
		public JSBlock getFinally();
	}
	
	public interface TryFinally extends JSTryStatement {
		public JSBlock getFinally();
	}
}
