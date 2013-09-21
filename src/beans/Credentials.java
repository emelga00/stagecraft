/***************
 *  Author: JLH
 ***************/
package beans;

public class Credentials {
	//from credentialDB
	private int credID;
	private String email;
	private String pass;
	private String role;
	private int userID;
	private String userName;
	//from credentialDB
	public void setCredID(int CredID) {
		this.credID = CredID;
	}
	public int getCredID() {
		return credID;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass() {
		return pass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return userName;
	}
	public void setClientName(String userName) {
		this.userName = userName;
	}
}
