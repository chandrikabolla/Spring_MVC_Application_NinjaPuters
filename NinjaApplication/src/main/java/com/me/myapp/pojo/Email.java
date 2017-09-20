package com.me.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "email_table")
public class Email {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emailid",unique=true, nullable = false)
	private long emailid;

	@Column(name = "email_address")
	private String emailAddress;

	
	@Enumerated(EnumType.STRING)
	@Column(name="emailtype")
	private EmailType emailtype;
	
	@ManyToOne
	@JoinColumn(name="empid",nullable=false)
	private Employee employee;

	public Email(String emailAddress,EmailType emailtype) {
		
		this.emailAddress=emailAddress;
		this.emailtype=emailtype;
	}

	public Email(){
		
	}
	

	public long getEmailId() {
		return emailid;
	}

	public void setEmailId(long id) {
		this.emailid = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public long getEmailid() {
		return emailid;
	}

	public void setEmailid(long emailid) {
		this.emailid = emailid;
	}

	public EmailType getEmailtype() {
		return emailtype;
	}

	public void setEmailtype(EmailType emailtype) {
		this.emailtype = emailtype;
	}

	



}
