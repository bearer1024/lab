package uk.ac.le.cs.CO3098.cw3.domain;

public class Main {
	
	public static void main(String[] args){
		
		
	
		UMTClass personClass=new UMTClass("Person");
		Property p_age=new Property("age", PRIMITIVE_TYPE.Int);	
		Property p_name=new Property("name", PRIMITIVE_TYPE.String);	
		Property p_mother=new Property("mother",personClass);
		
		
		personClass.addProperty(p_age);
		personClass.addProperty(p_name);
		personClass.addProperty(p_mother);
		
		System.out.println(personClass);
		
		
		Instance p1=new Instance(personClass,"p1");
					p1.setPropertyValue(p_age,new Integer(10));
					p1.setPropertyValue(p_name, "John Smith");
					
		Instance p2=new Instance(personClass,"p2");
					p2.setPropertyValue(p_age,new Integer(30));
					p2.setPropertyValue(p_name, "Jane Brown");
					

		p1.setPropertyValue(p_mother, p2);
		
		System.out.println(p2);
		System.out.println(p1);
		
		
		
	}

}
