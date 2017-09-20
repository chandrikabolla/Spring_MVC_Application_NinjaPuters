package com.me.myapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.myapp.dao.OrganizationDAO;
import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.Organization;

@Component
public class OrganizationValidator implements Validator{

	@Autowired
	@Qualifier("orgDao")
	OrganizationDAO orgDao;
	
	
	public boolean supports(Class aClass) {
		return aClass.equals(Organization.class);
	}

	public void validate(Object obj, Errors errors) {
		Organization newOrg= (Organization) obj;
		
		System.out.println("In validator: " +newOrg.getOrgName());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgName", "error.invalid.newOrg", "Organization name is required");
		
		// check if user exists
		
		if(errors.hasErrors())
		{
			return;
		}
		
	
		
	
		
	}
}
