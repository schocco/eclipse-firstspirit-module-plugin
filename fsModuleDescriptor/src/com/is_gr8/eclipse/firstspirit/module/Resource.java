package com.is_gr8.eclipse.firstspirit.module;

import org.eclipse.sapphire.Element;
import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.Type;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.EnumSerialization;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Required;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlNamespace;
import org.eclipse.sapphire.modeling.xml.annotations.XmlSchema;

@XmlBinding(path = "resource")
public interface Resource extends Element {
	
	ElementType TYPE = new ElementType(Resource.class);
	
	@Label(standard = "resource")
	@XmlBinding(path = "")
	@Required
	ValueProperty PROP_RESOURCE = new ValueProperty(TYPE, "resource");

	Value<String> getResource();

	void setResource(String resource);
	
	@Label(standard = "scope")
	enum Scope
    {
		@Label( standard = "server" )
		@EnumSerialization( primary = "server" )
        SERVER,
        @Label( standard = "module" )
		@EnumSerialization( primary = "module" )
        MODULE
    }
    
	@XmlBinding(path = "@scope")
    @Type( base = Scope.class )
	@Label(standard = "scope")
    ValueProperty PROP_SCOPE = new ValueProperty( TYPE, "Scope" );
    
    Value<Scope> getScope();
    void setScope( String value );
    void setScope( Scope value );
	
	@Label(standard = "name")
	@XmlBinding(path = "@name")
	ValueProperty PROP_NAME = new ValueProperty(TYPE, "Name");

	Value<String> getName();

	void setName(String name);
	
	@Label(standard = "version")
	@XmlBinding(path = "@version")
	ValueProperty PROP_VERSION = new ValueProperty(TYPE, "Version");

	Value<String> getVersion();

	void setVersion(String version);
	
	
	@Label(standard = "minVersion")
	@XmlBinding(path = "@minVersion")
	ValueProperty PROP_MIN_VERSION = new ValueProperty(TYPE, "MinVersion");

	Value<String> getMinVersion();

	void setMinVersion(String version);
	
	
	@Label(standard = "maxVersion")
	@XmlBinding(path = "@maxVersion")
	ValueProperty PROP_MAX_VERSION = new ValueProperty(TYPE, "MaxVersion");

	Value<String> getMaxVersion();

	void setMaxVersion(String version);

}
