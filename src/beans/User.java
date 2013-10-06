package beans;
/***************
 *  Author: JLH
 ***************/
public class User {
	//from userDB
	private int    user_ID;
	private String first_Name;
	private String last_Name;
	private String Phone;
	private String Address;
	private String City;
	private String State;
	private String ZIP;
	private String Date;
	private String Role;
	//from credentialDB
	private int 	Valid;
	private int    creds_Credential_ID;
	private String creds_Email;
	private String creds_Pass;
	private String cred_Date;
	
	//from userDB
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZIP() {
		return ZIP;
	}
	public void setZIP(String zIP) {
		ZIP = zIP;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public int getValid() {
		return Valid;
	}
	public void setValid(int valid) {
		Valid = valid;
	}
	//from credentialDB
	public int getCreds_Credential_ID() {
		return creds_Credential_ID;
	}
	public void setCreds_Credential_ID(int creds_Credential_ID) {
		this.creds_Credential_ID = creds_Credential_ID;
	}
	public String getCreds_Email() {
		return creds_Email;
	}
	public void setCreds_Email(String creds_email) {
		this.creds_Email = creds_email;
	}
	public String getCreds_Pass() {
		return creds_Pass;
	}
	public void setCreds_Pass(String creds_pass) {
		this.creds_Pass = creds_pass;
	}
	public String getCred_Date() {
		return cred_Date;
	}
	public void setCred_Date(String cred_Date) {
		this.cred_Date = cred_Date;
	}
	
}
