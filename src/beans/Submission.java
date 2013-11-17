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
  private String userName; //used to get the user name from the user table and display in viewSubmissions
  
  public void setSubID(int subID){
    this.subID = subID;
  }
  public int getSubID(){
    return subID;
  }
  public void setProjID(int projID){
    this.projID = projID;
  }
  public int getProjID(){
    return projID;
  }
  public void setUserID(int usrID){
    this.userID = usrID;
  }
  public int getUserID(){
    return userID;
  }
  public void setURL(String URL){
    this.URL = URL;
  }
  public String getURL(){
    return URL;
  }
  public void setBlob(byte[] blob){
    this.blob = blob;
  }
  public byte[] getBlob(){
    return blob;
  }
  public void setType(String type){
    this.type = type;
  }
  public String getType(){
    return type;
  }
  public void setDate(String date){
    this.date = date;
  }
  public String getDate(){
    return date;
  }
  public void setCategory(String cat){
    this.category = cat;
  }
  public String getCategory(){
    return category;
  }
  public void setUserName(String name){
    this.userName = name;
  }
  public String getUserName(){
    return userName;
  }
}