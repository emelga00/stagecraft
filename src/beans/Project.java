package beans;

public class Project 
{
	private int    projID;
	private String Name;
	private String Description;
	private String submittedBy;
	private String organization;
	
	//from credentialDB
		public void setProjID(int projID) {
			this.projID = projID;
		}
		public int getProjID() {
			return projID;
		}
		public void setName(String name) {
			this.Name = name;
		}
		public String getName() {
			return Name;
		}
		public void setDesc(String desc) {
			this.Description = desc;
		}
		public String getDesc() {
			return Description;
		}
		public void setSubmittedBy(String submittedBy) {
			this.submittedBy = submittedBy;
		}
		public String getSubmittedBy() {
			return submittedBy;
		}
		public void setOrganization(String organization) {
			this.organization = organization;
		}
		public String getOrganization() {
			return organization;
		}
}