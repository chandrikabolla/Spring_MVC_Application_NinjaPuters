package com.me.myapp.pojo;

public enum AddressType {
	
	Permanent("Permanent"),Temporary("Temporary");

	private String value;
    private AddressType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value=value;
    }
}
