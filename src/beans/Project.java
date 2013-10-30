package beans;

public class Project 
{
	private int    projectID;       //the ID of this project
	private String name;            //the name of the project
	private String description;     //the description of the project
	private int    userID;          //the user ID of the person who submitted the project
  private String submittedBy;     //the name of the user who submitted the project
  private int    organizationID;  //the organization ID of the organization associated with the user 
  private String organization;    //the name of the organization associate with the user
  private String createdDate;     //date project was created
  private String lastUpdatedDate; //date project was last updated
  private int    lastUpdatedID;   //the user ID of the user who last updated the project
  private String lastUpdatedBy;   //the name of the user who last updated this project
  private int    bannerPicID;     //the id of the picture to use for the banner
  private byte[] bannerPicture;   //the picture used for the banner
	
	public int getProjectID()
  {
    return projectID;
  }
	
  public void setProjectID(int projectID)
  {
    this.projectID = projectID;
  }
  
  public int getUserID()
  {
    return userID;
  }
  
  public void setUserID(int userID)
  {
    this.userID = userID;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getDesc()
  {
    return description;
  }
  
  public void setDesc(String description)
  {
    this.description = description;
  }
  
  public String getSubmittedBy()
  {
    return submittedBy;
  }
  
  public void setSubmittedBy(String submittedBy)
  {
    this.submittedBy = submittedBy;
  }
  
  public int getOrganizationID()
  {
    return organizationID;
  }

  public void setOrganizationID(int organizationID)
  {
    this.organizationID = organizationID;
  }
  
  public String getOrganization()
  {
    return organization;
  }
  
  public void setOrganization(String organization)
  {
    this.organization = organization;
  }
  
  public String getCreatedDate()
  {
    return createdDate;
  }
  
  public void setCreatedDate(String createdDate)
  {
    this.createdDate = createdDate;
  }
  
  public String getLastUpdatedDate()
  {
    return lastUpdatedDate;
  }
  
  public void setLastUpdatedDate(String lastUpdatedDate)
  {
    this.lastUpdatedDate = lastUpdatedDate;
  }
  
  public int getLastUpdatedID()
  {
    return lastUpdatedID;
  }

  public void setLastUpdatedID(int lastUpdatedID)
  {
    this.lastUpdatedID = lastUpdatedID;
  }
  
  public String getLastUpdatedBy()
  {
    return lastUpdatedBy;
  }
  
  public void setLastUpdatedBy(String lastUpdatedBy)
  {
    this.lastUpdatedBy = lastUpdatedBy;
  }
  
  public int getBannerPicID()
  {
    return bannerPicID;
  }

  public void setBannerPicID(int bannerPicID)
  {
    this.bannerPicID = bannerPicID;
  }
  
  public byte[] getBannerPicture()
  {
    return bannerPicture;
  }
  
  public void setBannerPicture(byte[] bannerPicture)
  {
    this.bannerPicture = bannerPicture;
  }
}