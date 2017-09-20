package com.me.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="employeeuseraccount_table")
@PrimaryKeyJoinColumn(name = "useraccountID")
public class EmployeeUserAccount extends UserAccount{
	
	@OneToOne
	@JoinColumn(name="empid")
	private Employee employee;
	
	
	public EmployeeUserAccount(){
		
	
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
