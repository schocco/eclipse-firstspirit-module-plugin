package com.is_gr8.eclipse.firstspirit.module.components;

import org.eclipse.sapphire.Element;
import org.eclipse.sapphire.ElementList;
import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.ListProperty;
import org.eclipse.sapphire.ReferenceValue;
import org.eclipse.sapphire.Type;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.java.JavaType;
import org.eclipse.sapphire.java.JavaTypeConstraint;
import org.eclipse.sapphire.java.JavaTypeKind;
import org.eclipse.sapphire.java.JavaTypeName;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.MustExist;
import org.eclipse.sapphire.modeling.annotations.Reference;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;

import com.is_gr8.eclipse.firstspirit.module.Resource;

public interface Component extends Element {
	
	public static final String FIRSTSPIRIT_CONFIGURATION_TYPE = "de.espirit.firstspirit.module.Configuration";
	
	ElementType TYPE = new ElementType(Component.class);
	
	@Label(standard = "name")
	@XmlBinding(path = "name")
	@Required
	ValueProperty PROP_NAME = new ValueProperty(TYPE, "Name");

	Value<String> getName();

	void setName(String name);
	
	//displayname
	@Label(standard = "displayname")
	@XmlBinding(path = "displayname")
	ValueProperty PROP_DISPLAYNAME = new ValueProperty(TYPE, "Displayname");

	Value<String> getDisplayName();

	void setDisplayName(String displayname);
	
	
	@Label(standard = "description")
	@XmlBinding(path = "description")
	ValueProperty PROP_DESCRIPTION = new ValueProperty(TYPE, "Description");

	Value<String> getDescription();

	void setDescription(String configurableClass);
	
	// resources
	@Type(base = Resource.class)
	@Required
	@Label(standard = "Resources")
	@XmlListBinding(path = "resources", mappings = @XmlListBinding.Mapping(element = "resource", type = Resource.class))
	ListProperty PROP_RESOURCES = new ListProperty(TYPE, "Resources");

	ElementList<Resource> getResources();

}
