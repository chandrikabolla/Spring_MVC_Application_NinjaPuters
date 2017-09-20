package com.me.myapp.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="admin_table")
public class Admin {
	
	@Id 
	@Column(name="admin_ID",unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long adminid;
	
	@Column(name="adminName",nullable=false)
	private String name;
	
	
	@Column(name="adminEmail")
	private String adminEmail;
	
	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}


	@OneToOne
	@PrimaryKeyJoinColumn
	private Organization org;
	
	@OneToOne(mappedBy="admin",cascade=CascadeType.ALL)
	private AdminUserAccount adminUserAccount;
	
	public Admin(){
		
	}
	
	public Admin(String name){
		this.name=name;
	}

	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public long getAdminid() {
		return adminid;
	}

	public void setAdminid(long adminid) {
		this.adminid = adminid;
	}

	public AdminUserAccount getAdminUserAccount() {
		return adminUserAccount;
	}

	public void setAdminUserAccount(AdminUserAccount adminUserAccount) {
		this.adminUserAccount = adminUserAccount;
	}

	
	public String toString(){
		return this.name;
	}
}
