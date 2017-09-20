package com.me.myapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.myapp.pojo.Employee;

@Component
public class EmployeeLoginValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Employee.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		
		Employee employee = (Employee) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeUserAccount.userName", "error.invalid.employee", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeUserAccount.password", "error.invalid.employee", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empid", "error.invalid.employee", "Employee id Required");
		
	}

}
