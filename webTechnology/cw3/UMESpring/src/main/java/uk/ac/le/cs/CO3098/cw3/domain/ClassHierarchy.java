package uk.ac.le.cs.CO3098.cw3.domain;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClassHierarchy {
@Id
@GeneratedValue
@Column(name = "id") Integer id;
@Column(name = "classname") String classname;
@Column(name = "superclass") String superclass;

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getClassname() {
	return classname;
}
public void setClassname(String classname) {
	this.classname = classname;
}
public String getSuperclass() {
	return superclass;
}
public void setSuperclass(String superclass) {
	this.superclass = superclass;
}

public ClassHierarchy(){}

public ClassHierarchy(String classname,String superclass){
	this.classname = classname;
	this.superclass = superclass;
}

public ClassHierarchy(String classname){
	this.classname = classname;
}

Vector<ClassHierarchy> superClasses=new Vector<ClassHierarchy> ();
Vector<Property> properties=new Vector<Property>();



public boolean addSuperclass(ClassHierarchy cls){
	return superClasses.add(cls);
}

public boolean removeSuperclass(ClassHierarchy cls){
	return superClasses.remove(cls);
}


public Vector<ClassHierarchy> getSuperClasses() {
	return superClasses;
}

public void setSuperClasses(Vector<ClassHierarchy> superClasses) {
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
}
