package com.is_gr8.eclipse.firstspirit.module.components;

import org.eclipse.sapphire.ElementType;
import org.eclipse.sapphire.ReferenceValue;
import org.eclipse.sapphire.Type;
import org.eclipse.sapphire.Value;
import org.eclipse.sapphire.ValueProperty;
import org.eclipse.sapphire.java.JavaType;
import org.eclipse.sapphire.java.JavaTypeConstraint;
import org.eclipse.sapphire.java.JavaTypeKind;
import org.eclipse.sapphire.java.JavaTypeName;
import org.eclipse.sapphire.modeling.annotations.DefaultValue;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.MustExist;
import org.eclipse.sapphire.modeling.annotations.Reference;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;

@XmlBinding(path = "resource")
public interface Library extends Component {

	ElementType TYPE = new ElementType(Library.class);
	
	
	@Label(standard = "hidden")
	@Type(base = Boolean.class)
	@DefaultValue( text = "false" )
	@XmlBinding(path = "hidden")
	ValueProperty PROP_HIDDEN = new ValueProperty(TYPE, "Hidden");

	Value<Boolean> getHidden();

	void setHidden(boolean isHidden);
	void setHidden(String hidden);
	
	
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
