package beans;

public class Submission 
{
	private int    subID;
	private int    projID;
	private int    userID;
	private String URL;
	private byte[] blob;
	private String type;
	private String date;
	private String category;
	
	//from submissionDB
  public void setSubID(int subID) {
    this.subID = subID;
  }
  public int getSubID() {
    return subID;
  }
	public void setProjID(int projID) {
	  this.projID = projID;
	}
	public int getProjID() {
	  return projID;
	}
	public void setURL(String URL) {
	  this.URL = URL;
	}
	public String getURL() {
	  return URL;
	}
	public void setBlob(byte[] blob) {
	  this.blob = blob;
	}
	public byte[] getBlob() {
	  return blob;
	}
	public void setUserID(int userID) {
	  this.userID = userID;
	}
	public int getUserID() {
	  return userID;
	}
	public void setType(String type) {
	  this.type = type;
	}
	public String getType() {
	  return type;
	}
	public void setDate(String date) {
	  this.date = date;
	}
	public String getDate() {
	  return date;
	}
	public void setCategory(String category) {
	  this.category = category;
	}
	public String getCategory() {
	  return category;
	}	
}