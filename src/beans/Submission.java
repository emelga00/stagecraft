package beans;

public class Submission
{
/*==============================================================================================================*/
//                                VARIABLES/FIELDS  
/*==============================================================================================================*/
//scope  |type  |name     |  comments/descriptions
//--------------------------------------------------------------------------------------------------------------  
  private int    subID;    //primary key for submission
  private int    projID;   //foreign key for project table
  private int    userID;   //foreign key for user table --used to get userName from user table
  private String URL;      //the submission will either contain a URL or a Blob, but not both
  private byte[] blob;     //the submission will either contain a URL or a Blob, but not both
  private String type;     //contains the type of submission ("video", "image", or "plan")
  private String date;     //contains the date the submission was added
  private String category; //contains the category that the user manually types ('cast', 'costume', etc..)
  private String fileName; //used to store the fileName and used to extract the extension for later formatting
  private String userName; //used to get the user name from the user table and display in viewSubmissions 
                           //userName not stored in DB
  
/*==============================================================================================================*/
//                                  GET AND SET METHODS  
/*==============================================================================================================*/
  
  //SUBMISSION ID
  public void setSubID(int subID){
    this.subID = subID;
  }
  public int getSubID(){
    return subID;
  }
  
  //PROJECT ID
  public void setProjID(int projID){
    this.projID = projID;
  }
  public int getProjID(){
    return projID;
  }
  
  //USER ID
  public void setUserID(int usrID){
    this.userID = usrID;
  }
  public int getUserID(){
    return userID;
  }
  
  //URL
  public void setURL(String URL){
    this.URL = URL;
  }
  public String getURL(){
    return URL;
  }
  
  //BLOB
  public void setBlob(byte[] blob){
    this.blob = blob;
  }
  public byte[] getBlob(){
    return blob;
  }
  
  //TYPE
  public void setType(String type){
    this.type = type;
  }
  public String getType(){
    return type;
  }
  
  //DATE
  public void setDate(String date){
    this.date = date;
  }
  public String getDate(){
    return date;
  }
  
  //CATEGORY
  public void setCategory(String cat){
    this.category = cat;
  }
  public String getCategory(){
    return category;
  }
  
  //USER NAME (NOT STORED IN DB)
  public void setUserName(String name){
    this.userName = name;
  }
  public String getUserName(){
    return userName;
  }
  
  //FILE NAME
  public void setFileName(String name){
    this.fileName = name;
  }
  public String getFileName(){
    return fileName;
  }
}