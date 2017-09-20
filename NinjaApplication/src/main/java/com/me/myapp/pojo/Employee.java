package com.me.myapp.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="employee_table")
@PrimaryKeyJoinColumn(name = "personID")
public class Employee extends Person {
	
	
	@Column(name="empid",unique=true, nullable = false)
	private String empid;
	

	@Transient
	private Email personalEmailID=new Email();
	
	@Transient 
	private Email companyEmailID=new Email();
	
	@Transient
	private Address permanentAddress=new Address();
	
	@Transient
	private Address temporaryAddress=new Address();
	
	public Email getPersonalEmailID() {
		return personalEmailID;
	}

	public void setPersonalEmailID(Email personalEmailID) {
		this.personalEmailID = personalEmailID;
	}

	public Email getCompanyEmailID() {
		return companyEmailID;
	}

	public void setCompanyEmailID(Email companyEmailID) {
		this.companyEmailID = companyEmailID;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Address getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(Address temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

	@ManyToOne
	@JoinColumn(name="org_id",nullable=false)
	private Organization org;
	
	@Enumerated(EnumType.STRING)
	@Column(name="level")
	private Level level;
	
	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
	private List<Email> emailids=new ArrayList<Email>();
	
	@ManyToOne
	@JoinColumn(name="roleid")
	private Role empRole;
	
	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
	private List<Address> addresses=new ArrayList<Address>();
	
	@ManyToMany
	private List<Contact> contacts=new ArrayList<Contact>();
	
	@OneToOne(mappedBy="employee",cascade=CascadeType.ALL)
	private EmployeeUserAccount employeeUserAccount;
	
	@OneToOne(mappedBy="ownEmployee",cascade=CascadeType.ALL)
	private Contact contact;
	
	
	public Employee(){
		
	}
	
	public Employee(String empid,Organization org,List<Address> addresses,EmployeeUserAccount eua,Role empRole,Contact contact){
		this.empid=empid;
		this.org=org;
		//this.level=level;
		this.empRole=empRole;
		this.addresses=addresses;
		this.employeeUserAccount=eua;
		this.contact=contact;
		this.personalEmailID.setEmailtype(EmailType.PersonalEmailId);
		this.companyEmailID.setEmailtype(EmailType.CompanyEmailId);
		
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Role getEmpRole() {
		return empRole;
	}

	public void setEmpRole(Role empRole) {
		this.empRole = empRole;
	}

	

	

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	

	public List<Email> getEmailids() {
		return emailids;
	}

	public void setEmailids(List<Email> emailids) {
		this.emailids = emailids;
	}

	public EmployeeUserAccount getEmployeeUserAccount() {
		return employeeUserAccount;
	}

	public void setEmployeeUserAccount(EmployeeUserAccount employeeUserAccount) {
		this.employeeUserAccount = employeeUserAccount;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	
	

}
