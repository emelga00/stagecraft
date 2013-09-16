package controller.actions;

public class View {
	private String responseType;
	private String page;
	
	public View(){
		this.setResponseType("FORWARD");
	}
	
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}