package uk.ac.le.cs.CO3098.bean;

public class User {
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public User(String userName, String userEmail, String userFullName, String dateOfBirth, String homeAddress,
			String password, String securityCode) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userFullName = userFullName;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
		this.password = password;
		this.securityCode = securityCode;
	}
	
	String userName,userEmail,userFullName,dateOfBirth,homeAddress,password,securityCode;

}
