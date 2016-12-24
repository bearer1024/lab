package uk.ac.le.cs.CO3098.cw3.domain;

public class Property {
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public MetaType getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(MetaType propertyType) {
		this.propertyType = propertyType;
	}

	public Property(String propertyName, MetaType propertyType) {
		super();
		this.propertyName = propertyName;
		this.propertyType = propertyType;
	}
	
	
	public Property(String propertyName, PRIMITIVE_TYPE propertyType) {
		super();
		this.propertyName = propertyName;
		this.propertyType = new PrimitiveType(propertyName,propertyType);;
	}

	

	String propertyName;
	MetaType propertyType;
	
	public String toString(){
		return propertyName+":"+propertyType.getName();
	}

	

}
