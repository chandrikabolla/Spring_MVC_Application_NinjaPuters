package com.me.myapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.myapp.pojo.Employee;

public class EmployeeRegisterValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Employee.class);
	}

	public void validate(Object obj, Errors errors) {
		Employee employee = (Employee) obj;
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.employee", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.employee", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empid", "error.invalid.employee", "Employee id Required");
		
		if(errors.hasErrors())
		{
			return;
		}
	}
}
