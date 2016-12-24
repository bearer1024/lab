package uk.ac.le.cs.CO3098.cw3.domain;

import java.util.*;

public class Instance {
	

	public MetaType getType() {
		return type;
	}
	public void setType(MetaType type) {
		this.type = type;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
	public Instance(MetaType type, String instanceName) {
		super();
		this.type = type;
		this.instanceName = instanceName;
	}
	
	
	public void setPropertyValue(Property property, Object value){
		propertValues.put(property,value);
	}

	MetaType type;
	String instanceName;
	HashMap<Property, Object> propertValues= new HashMap<Property, Object>();
	
	
	public String toString(){
		
	
		StringBuffer sb=new StringBuffer(instanceName+":"+type.getName()+"{");
		
	    String comma="";
	    boolean first =true;
		for (Map.Entry<Property, Object> entry : propertValues.entrySet()) {
			Property key = entry.getKey();
		    Object value = entry.getValue();
		    if(value!=null){
		    	if(first){comma="";}else{comma=",";}
		    	
		    	if(value instanceof Instance){
		    		sb.append( comma+" property('"+ key.getPropertyName()+"',"+((Instance)value).getInstanceName()+")");
		    	}else{
		    		sb.append( comma+" property('"+ key.getPropertyName()+"','"+value+"')");
		    	}
		    	first=false;
		    }
		}
		
		sb.append("}");
		
		return sb.toString();
	
	}

}
