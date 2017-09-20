package com.me.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="adminuseraccount_table")
@PrimaryKeyJoinColumn(name = "useraccountID")
public class AdminUserAccount extends UserAccount{

	
	@OneToOne
	@JoinColumn(name="adminid")
	private Admin admin;
	
	
	public AdminUserAccount(){
		
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	
}
