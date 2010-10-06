package org.hisrc.jscm.codemodel.writer;

public interface IndentedAppendable extends Appendable{
	
	public IndentedAppendable indent(CharSequence indentation);
	
	

}
