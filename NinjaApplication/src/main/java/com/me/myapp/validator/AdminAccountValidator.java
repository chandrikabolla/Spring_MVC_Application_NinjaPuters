package com.me.myapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.myapp.dao.AdminDAO;
import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.pojo.Admin;
import com.me.myapp.pojo.AdminUserAccount;
import com.me.myapp.pojo.Employee;


@Component
public class AdminAccountValidator implements Validator{

	
	
	public boolean supports(Class aClass) {
		return aClass.equals(AdminUserAccount.class);
	}

	public void validate(Object obj, Errors errors) {
		AdminUserAccount aua = (AdminUserAccount) obj;
		
		//System.out.print("Inside accountvalidator:"+"\n "+aua.getUserName()+aua.getPassword()+"\n");
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.adminUserAccount", "UserName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.adminUserAccount", "Password Required");
		
		
		
		if(errors.hasErrors())
		{
			return;
		}
		
		// check if admin account exists or not
		/*else{
	
			Admin admin = null;
			try {
				AdminDAO adminDao=new AdminDAO();
				admin=adminDao.getAdmin(aua);
			} catch (EmployeeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OrganizationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Validating admin account:"+ admin);
			if(admin==null)
			{
				System.out.println("No user found:");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.adminUserAccount", "UserName Incorrect");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.adminUserAccount", "Password Incorrect");
			
				return;
			}
			else{
				if(admin.getAdminUserAccount()!=null)
				{
				String username=admin.getAdminUserAccount().getUserName();
				String password=admin.getAdminUserAccount().getPassword();
				
				if(username.equalsIgnoreCase(aua.getUserName())&&password.equals(aua.getPassword()))
				{
					return;
				}
				else{
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.adminUserAccount", "UserName Incorrect");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.adminUserAccount", "Password Incorrect");
				
					return;
					
				}
				}
				else{
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.adminUserAccount", "UserName Incorrect");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.adminUserAccount", "Password Incorrect");
				
					return;
				}
				
			}
			
		}*/
	
		
		
	}
}
