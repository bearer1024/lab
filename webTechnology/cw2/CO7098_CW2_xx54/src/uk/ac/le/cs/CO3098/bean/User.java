package uk.ac.le.cs.CO3098.bean;

public class User {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public User(String name,String fullname,  String passwordHash) {
		super();
		this.name = name;
		this.passwordHash = passwordHash;
		this.fullname = fullname;
	}
	String name;
	String passwordHash;
	String fullname;

}
