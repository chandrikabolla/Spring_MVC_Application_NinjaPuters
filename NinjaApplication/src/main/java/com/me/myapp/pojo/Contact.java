package com.me.myapp.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="contact_table")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contactID",unique=true, nullable = false)
	private long contactID;
	
	@Column(name="contactName",nullable=false,unique=true)
	private String contactName;
	
	@Column(name="contactEmail",nullable=false,unique=true)
	private String contactEmail;
	
	@OneToOne
	@JoinColumn(name="empid")
	private Employee ownEmployee;
	
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization org;
	
	
	@ManyToMany
	@JoinColumn
	private List<Employee> employees=new ArrayList<Employee>();
	
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}


	
	
	public Contact(String contactName,String contactEmail){
		
		this.contactName=contactName;
		this.contactEmail=contactEmail;
		
	}

	public Contact(){
		
	}
	public long getContactID() {
		return contactID;
	}

	public void setContactID(long contactID) {
		this.contactID = contactID;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	
	
	

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getOwnEmployee() {
		return ownEmployee;
	}

	public void setOwnEmployee(Employee ownEmployee) {
		this.ownEmployee = ownEmployee;
	}

	
	public String toString(){
		return this.contactName;
	}
	

}
