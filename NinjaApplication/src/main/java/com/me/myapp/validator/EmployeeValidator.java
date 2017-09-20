package com.me.myapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.myapp.pojo.Employee;


@Component
public class EmployeeValidator implements Validator{

	public boolean supports(Class aClass) {
		return aClass.equals(Employee.class);
	}

	public void validate(Object obj, Errors errors) {
		Employee employee = (Employee) obj;
		try {
			System.out.println("For object: "+obj.getClass().getDeclaredField("empRole").getName());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("In validator: " +employee.getEmpRole());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.employee", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.employee", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empid", "error.invalid.employee", "Employee id Required");
	//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empid", "error.invalid.employee", "Employee id Required");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
		//		"Email Required");
		
		// check if user exists
	
		
	}
}
