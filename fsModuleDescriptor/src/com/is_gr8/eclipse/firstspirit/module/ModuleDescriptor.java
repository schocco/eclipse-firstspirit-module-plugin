package com.is_gr8.eclipse.firstspirit.module;

import org.eclipse.sapphire.Element;
import org.eclipse.sapphire.ElementList;
import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.ListProperty;
import org.eclipse.sapphire.Type;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlListBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;
import org.eclipse.sapphire.modeling.xml.annotations.XmlSchema;

import com.is_gr8.eclipse.firstspirit.module.components.Component;
import com.is_gr8.eclipse.firstspirit.module.components.Library;
import com.is_gr8.eclipse.firstspirit.module.components.Public;
import com.is_gr8.eclipse.firstspirit.module.components.Service;
import com.is_gr8.eclipse.firstspirit.module.components.WebApp;

@XmlBinding(path = "module")
public interface ModuleDescriptor extends Element {
	ElementType TYPE = new ElementType(ModuleDescriptor.class);


	// name
	@Label(standard = "name")
	@Required
	@XmlBinding(path = "name")
	ValueProperty PROP_NAME = new ValueProperty(TYPE, "Name");

	Value<String> getName();

	void setName(String name);

	// displayname
	@Label(standard = "displayname")
	@XmlBinding(path = "displayname")
	ValueProperty PROP_DISPLAYNAME = new ValueProperty(TYPE, "Displayname");

	Value<String> getDisplayName();

	void setDisplayName(String displayname);

	// description
	@Label(standard = "description")
	@XmlBinding(path = "description")
	ValueProperty PROP_DESCRIPTION = new ValueProperty(TYPE, "Description");

	Value<String> getDescription();

	void setDescription(String description);

	// vendor
	@Label(standard = "vendor")
	@XmlBinding(path = "vendor")
	ValueProperty PROP_VENDOR = new ValueProperty(TYPE, "Vendor");

	Value<String> getVendor();

	void setVendor(String vendor);

	// version
	@Label(standard = "Version")
	@Required
	@XmlBinding(path = "version")
	ValueProperty PROP_VERSION = new ValueProperty(TYPE, "Version");

	Value<String> getVersion();

	void setVersion(String version);

	// components
	@Label(standard = "Components")
	@Required
	@Type(base = Component.class, possible = {Library.class, Service.class, Public.class, WebApp.class})
	@XmlListBinding(path = "components", mappings = { 
			@XmlListBinding.Mapping(element = "service", type = Service.class),
			@XmlListBinding.Mapping(element = "library", type = Library.class),
			@XmlListBinding.Mapping(element = "public", type = Public.class),
			@XmlListBinding.Mapping(element = "web-app", type = WebApp.class)})
	ListProperty PROP_COMPONENTS = new ListProperty(TYPE, "Components");

	ElementList<Component> getComponents();

	// dependencies
	@Type(base = Dependency.class)
	@Label(standard = "Dependencies")
	@XmlListBinding(path = "dependencies", mappings = @XmlListBinding.Mapping(element = "depends", type = Dependency.class))
	ListProperty PROP_DEPENDENCIES = new ListProperty(TYPE, "Dependencies");

	ElementList<Resource> getDependencies();

	// resources
	@Type(base = Resource.class)
	@Label(standard = "Resources")
	@XmlListBinding(path = "resources", mappings = @XmlListBinding.Mapping(element = "resource", type = Resource.class))
	ListProperty PROP_RESOURCES = new ListProperty(TYPE, "Resources");

	ElementList<Resource> getResources();

}
