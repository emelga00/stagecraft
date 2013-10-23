package beans;

public class Project 
{
	private String projectID;
	private String name;
	private String description;
	private String userID;
  private String userName;
  private String orgID;
  private String organizationName;
  private String createdDate;
  private String lastUpdatedDate;
  private String lastUpdatedID;
  private String lastUpdatedName;
  private String bannerPicID;
  private byte[] bannerPicture;
	
	public String getProjectID()
  {
    return projectID;
  }
	
  public void setProjectID(String projectID)
  {
    this.projectID = projectID;
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
  
  public String getUserID()
  {
    return userID;
  }
  
  public void setUserID(String userID)
  {
    this.userID = userID;
  }
  
  public String getUserName()
  {
    return userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getOrgID()
  {
    return orgID;
  }

  public void setOrgID(String orgID)
  {
    this.orgID = orgID;
  }
  
  public String getOrganizationName()
  {
    return organizationName;
  }
  
  public void setOrganizationName(String organizationName)
  {
    this.organizationName = organizationName;
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

  public String getLastUpdatedID()
  {
    return lastUpdatedID;
  }

  public void setLastUpdatedID(String lastUpdatedID)
  {
    this.lastUpdatedID = lastUpdatedID;
  }

  public String getLastUpdatedName()
  {
    return lastUpdatedName;
  }

  public void setLastUpdatedName(String lastUpdatedName)
  {
    this.lastUpdatedName = lastUpdatedName;
  }

  public String getBannerPicID()
  {
    return bannerPicID;
  }

  public void setBannerPicID(String bannerPicID)
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