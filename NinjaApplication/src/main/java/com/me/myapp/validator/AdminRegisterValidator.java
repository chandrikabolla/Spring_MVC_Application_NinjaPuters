package com.me.myapp.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.myapp.dao.AdminDAO;
import com.me.myapp.pojo.Admin;
import com.me.myapp.pojo.AdminUserAccount;
import com.me.myapp.pojo.Employee;
public class AdminRegisterValidator implements Validator{
	
	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;
	public boolean supports(Class aClass) {
		return aClass.equals(Admin.class);
	}

	public void validate(Object obj, Errors errors) {
		Admin aua = (Admin) obj;
		
	
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.admin", "Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adminEmail", "error.invalid.admin", "Email is Required");
		
		
		
		if(errors.hasErrors())
		{
			return;
		}
	}
}
