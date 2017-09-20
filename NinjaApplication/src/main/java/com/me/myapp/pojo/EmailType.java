package com.me.myapp.pojo;

public enum EmailType {
	
	CompanyEmailId("CompanyEmailId"),PersonalEmailId("PersonalEmailId");
	
	private String value;
    private EmailType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value=value;
    }

}
