package com.me.myapp.exception;

public class OrganizationException extends Exception{
	
	public OrganizationException(String message)
	{
		super("OrganizationException-"+message);
	}
	
	public OrganizationException(String message, Throwable cause)
	{
		super("OrganizationException-"+message,cause);
	}
	

}
