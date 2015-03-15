package org.hisrc.jscm.codemodel.expression;

import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSGetter;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSProperty;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSSetter;

public interface JSPropertyAssignmentVisitor<V, E extends Exception> {

	public V visitProperty(JSProperty value) throws E;

	public V visitGetter(JSGetter value) throws E;

	public V visitSetter(JSSetter value) throws E;
}
