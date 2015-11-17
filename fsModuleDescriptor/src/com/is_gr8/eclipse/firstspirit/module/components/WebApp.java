package com.is_gr8.eclipse.firstspirit.module.components;

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

@XmlBinding(path = "resource")
public interface WebApp extends Component {
	
	public static final String FIRSTSPIRIT_WEBAPP_TYPE = "de.espirit.firstspirit.module.AbstractWebApp";


	ElementType TYPE = new ElementType(WebApp.class);
	
	
	@Label(standard = "class")
	@XmlBinding(path = "class")
	@Type( base = JavaTypeName.class )
	@Reference( target = JavaType.class )
	@JavaTypeConstraint( kind = JavaTypeKind.CLASS, type = { FIRSTSPIRIT_WEBAPP_TYPE } )
	@MustExist
	@Required
	ValueProperty PROP_JAVACLASS = new ValueProperty(TYPE, "JavaClass");

	ReferenceValue<JavaTypeName,JavaType> getJavaClass();
	void setJavaClass( String value );
	void setJavaClass( JavaTypeName value );
	
	
	@Label(standard = "configurable")
	@XmlBinding(path = "configurable")
	@Type( base = JavaTypeName.class )
	@Reference( target = JavaType.class )
	@JavaTypeConstraint(kind = JavaTypeKind.CLASS, type = {FIRSTSPIRIT_CONFIGURATION_TYPE})
	@MustExist
	ValueProperty PROP_CONFIGURABLE = new ValueProperty(TYPE, "Configurable");

	ReferenceValue<JavaTypeName,JavaType> getConfigurable();
	void setConfigurable( String value );
	void setConfigurable( JavaTypeName value );
	
	
	@Label(standard = "WebXml")
	@XmlBinding(path = "web-xml")
	ValueProperty PROP_WEBXML = new ValueProperty(TYPE, "WebXml");

	Value<String> getWebXml();

	void setWebXml(String dependency);
	
	
	// web resources
	@Type(base = Resource.class)
	@Label(standard = "Webresources")
	@XmlListBinding(path = "web-resources", mappings = @XmlListBinding.Mapping(element = "resource", type = Resource.class))
	ListProperty PROP_WEBRESOURCES = new ListProperty(TYPE, "WebResources");

	ElementList<Resource> getWebResources();

}
