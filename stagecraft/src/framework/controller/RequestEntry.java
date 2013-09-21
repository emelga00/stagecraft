package framework.controller;

/***********************
 *  Authors: JLH 
 ***********************/
public class RequestEntry {
	private String urlMapping;
	private String responseType;
	private Command command;
	private String security;
	private String role;
	
	public RequestEntry(String urlMapping,String responseType,Command command,String security, String role){
		this.setUrlMapping(urlMapping);
		this.setResponseType(responseType);
		this.setCommand(command);
		this.setSecurity(security);
		this.setRole(role);
	}
	
	public String getUrlMapping() {
		return urlMapping;
	}
	public void setUrlMapping(String urlMapping) {
		this.urlMapping = urlMapping;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public Command getCommand() {
		return command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
