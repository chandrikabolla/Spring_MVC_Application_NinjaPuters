package com.me.myapp.exception;

public class OrgImageException extends Exception{

	
	public OrgImageException(String message)
	{
		super("OrgImageException-"+message);
	}
	
	public OrgImageException(String message, Throwable cause)
	{
		super("OrgImageException-"+message,cause);
	}
}
