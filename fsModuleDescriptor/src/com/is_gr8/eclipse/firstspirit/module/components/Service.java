package com.is_gr8.eclipse.firstspirit.module.components;

import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.ReferenceValue;
import org.eclipse.sapphire.Type;
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

@XmlBinding(path = "resource")
public interface Service extends Component {
	
	public static final String FIRSTSPIRIT_SERVICE_TYPE = "de.espirit.firstspirit.module.Service";


	ElementType TYPE = new ElementType(Service.class);
	
	
	@Label(standard = "class")
	@XmlBinding(path = "class")
	@Type( base = JavaTypeName.class )
	@Reference( target = JavaType.class )
	@JavaTypeConstraint( kind = JavaTypeKind.CLASS, type = { FIRSTSPIRIT_SERVICE_TYPE } )
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

}
