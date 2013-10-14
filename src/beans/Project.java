package beans;

public class Project 
{
	private int    projectID;
	private String name;
	private String description;
  private String submittedBy;
  private String organization;
  private String createdDate;
  private String lastUpdatedDate;
  private String lastUpdatedBy;
  private byte[] bannerPicture;
	
	public int getProjectID()
  {
    return projectID;
  }
	
  public void setProjectID(int projectID)
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
  
  public String getSubmittedBy()
  {
    return submittedBy;
  }
  
  public void setSubmittedBy(String submittedBy)
  {
    this.submittedBy = submittedBy;
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
  
  public String getLastUpdatedBy()
  {
    return lastUpdatedBy;
  }
  
  public void setLastUpdatedBy(String lastUpdatedBy)
  {
    this.lastUpdatedBy = lastUpdatedBy;
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