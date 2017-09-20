package com.me.myapp.exception;

public class RoleException extends Exception{

	
	public RoleException(String message)
	{
		super("RoleException-"+message);
	}
	
	public RoleException(String message, Throwable cause)
	{
		super("RoleException-"+message,cause);
	}
	
}
