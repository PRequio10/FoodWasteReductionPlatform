package fwrp.model;

/**
 * This model represent the user of the application.
 * 
 * @author John Philip William Requio
 */
public class User {
	private int id;
	private String userName;
	
	private String passWord;
	private String email;
	private int phone;
	private String userType;
	
	 public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
