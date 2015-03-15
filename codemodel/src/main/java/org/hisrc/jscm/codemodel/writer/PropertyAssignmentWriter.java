package org.hisrc.jscm.codemodel.writer;

import java.io.IOException;
import java.util.List;

import org.hisrc.jscm.codemodel.JSPropertyName;
import org.hisrc.jscm.codemodel.JSSourceElement;
import org.hisrc.jscm.codemodel.expression.JSAssignmentExpression;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSGetter;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSProperty;
import org.hisrc.jscm.codemodel.expression.JSObjectLiteral.JSSetter;
import org.hisrc.jscm.codemodel.expression.JSPropertyAssignmentVisitor;
import org.hisrc.jscm.codemodel.expression.JSVariable;
import org.hisrc.jscm.codemodel.lang.Validate;

public class PropertyAssignmentWriter implements
		JSPropertyAssignmentVisitor<CodeWriter, IOException> {
	protected final CodeWriter f;

	public PropertyAssignmentWriter(CodeWriter formatter) {
		Validate.notNull(formatter);
		this.f = formatter;
	}

	@Override
	public CodeWriter visitProperty(JSProperty property) throws IOException {
		final JSPropertyName propertyName = property.getKey();
		final JSAssignmentExpression propertyValue = property.getValue();
		f.propertyName(propertyName).colon().whiteSpace()
				.expression(propertyValue);
		return f;
	}

	@Override
	public CodeWriter visitGetter(JSGetter getter) throws IOException {
		final JSPropertyName propertyName = getter.getKey();
		f.keyword("get").whiteSpace().propertyName(propertyName);

		f.openRoundBracket();
		f.closeRoundBracket().whiteSpace();
		f.openCurlyBracket().lineTerminator();

		f.indent();
		final List<JSSourceElement> sourceElements = getter.getBody()
				.getSourceElements();
		for (int index = 0; index < sourceElements.size(); index++) {
			final JSSourceElement sourceElement = sourceElements.get(index);
			f.sourceElement(sourceElement);
			f.lineTerminator();
		}
		f.unindent();
		f.closeCurlyBracket();
		return f;
	}

	@Override
	public CodeWriter visitSetter(JSSetter setter) throws IOException {
		final JSPropertyName propertyName = setter.getKey();
		f.keyword("set").whiteSpace().propertyName(propertyName);

		f.openRoundBracket();
		JSVariable parameter = setter.getParameter();
		f.identifier(parameter.getName());
		f.closeRoundBracket().whiteSpace();
		f.openCurlyBracket().lineTerminator();
		f.indent();
		final List<JSSourceElement> sourceElements = setter.getBody()
				.getSourceElements();
		for (int index = 0; index < sourceElements.size(); index++) {
			final JSSourceElement sourceElement = sourceElements.get(index);
			f.sourceElement(sourceElement);
			f.lineTerminator();
		}
		f.unindent();
		f.closeCurlyBracket();
		return f;
	}
}
