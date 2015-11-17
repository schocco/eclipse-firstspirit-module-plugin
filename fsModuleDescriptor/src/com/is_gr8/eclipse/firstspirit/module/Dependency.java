package com.is_gr8.eclipse.firstspirit.module;

import org.eclipse.sapphire.Element;
import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;

import com.is_gr8.eclipse.firstspirit.module.components.Service;

public interface Dependency extends Element {
	
ElementType TYPE = new ElementType(Dependency.class);
	
	@Label(standard = "Dependency")
	@XmlBinding(path = "")
	ValueProperty PROP_DEPENDENCY = new ValueProperty(TYPE, "Dependency");

	Value<String> getDependency();

	void setDependency(String dependency);

}
