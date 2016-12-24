package uk.ac.le.cs.CO3098.cw3.domain;

public class PrimitiveType extends MetaType{
	

	public PrimitiveType(String name, PRIMITIVE_TYPE type) {
		super(type.toString());
		this.type = type;
	}

	PRIMITIVE_TYPE type;

}

enum PRIMITIVE_TYPE{
	
		String,
		Int,
		Double,
		Boolean;
}
