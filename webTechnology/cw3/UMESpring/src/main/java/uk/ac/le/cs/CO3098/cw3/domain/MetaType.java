package uk.ac.le.cs.CO3098.cw3.domain;

public abstract class MetaType {


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MetaType(String name) {
		super();
		this.name = name;
	}

	String name;

	
}
