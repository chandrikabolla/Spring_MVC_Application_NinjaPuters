package com.me.myapp.controller;

import com.me.myapp.pojo.AdminUserAccount;
import com.me.myapp.pojo.EmployeeUserAccount;

public class UserAccountCreator {
	
	private String firstname;
	private String lastname;
	private String userName;
	private String lastName;
	
	
	public UserAccountCreator(String firstname,String lastname)
	{
	
		this.firstname=firstname;
		this.lastname=lastname;
	}

	public EmployeeUserAccount createUserAccount(){
		EmployeeUserAccount eua=new EmployeeUserAccount();
		
		eua.setUserName(lastname+""+firstname);
		eua.setPassword(firstname);
		
		
		return eua;
	}
	
	public static AdminUserAccount createAdminUserAccount(String name){
		
		AdminUserAccount aua=new AdminUserAccount();
		
		aua.setUserName(name);
		aua.setPassword(name);
		
		return aua;
		
	}
}
