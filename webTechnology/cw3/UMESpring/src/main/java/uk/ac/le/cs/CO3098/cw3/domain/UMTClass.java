package uk.ac.le.cs.CO3098.cw3.domain;

import java.util.Vector;

public class UMTClass extends MetaType{
	
	Vector<UMTClass> superClasses=new Vector<UMTClass> ();
	Vector<Property> properties=new Vector<Property>();
	
	public UMTClass(String name) {
		super(name);
	}
	
	public boolean addSuperclass(UMTClass cls){
		return superClasses.add(cls);
	}
	
	public boolean removeSuperclass(UMTClass cls){
		return superClasses.remove(cls);
	}
	

	public Vector<UMTClass> getSuperClasses() {
		return superClasses;
	}

	public void setSuperClasses(Vector<UMTClass> superClasses) {
		this.superClasses = superClasses;
	};
	
	public Vector<Property> getProperties() {
		return properties;
	}

	public void setProperties(Vector<Property> properties) {
		this.properties = properties;
	}
	
	public boolean addProperty(Property p) {
		return properties.add(p);
	}
	
	public boolean removeProperty(Property p) {
		return properties.remove(p);
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer(this.getName()+"\n");
		
		for(Property p: properties){
			sb.append("\t"+p.getPropertyName()+":"+p.getPropertyType().getName()+"\n");
		}
		
		return sb.toString();
	}


}
