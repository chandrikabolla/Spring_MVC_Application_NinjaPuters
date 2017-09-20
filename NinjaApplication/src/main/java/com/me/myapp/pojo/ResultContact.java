package com.me.myapp.pojo;

public class ResultContact {
	
	private String contactname;
	
	private String contactEmail;
	
	
	public ResultContact(String contactname,String contactEmail){
		
		this.contactname=contactname;
		this.contactEmail=contactEmail;
		
	}


	public String getContactname() {
		return contactname;
	}


	public void setContactname(String contactname) {
		this.contactname = contactname;
	}


	public String getContactEmail() {
		return contactEmail;
	}


	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	
	

}
