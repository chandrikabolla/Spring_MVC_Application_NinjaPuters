package com.me.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="useraccount_table")
@Inheritance(strategy=InheritanceType.JOINED)
public class UserAccount {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "useraccountID", unique=true, nullable = false)
	private long useraccountID;
	
	@Column(name = "userName",unique=true,nullable=false)
	private String userName;
	
	@Column(name ="password",unique=true,nullable=false)
	private String password;
	
	public UserAccount(){
		
	}


	public long getUseraccountID() {
		return useraccountID;
	}


	public void setUseraccountID(long useraccountID) {
		this.useraccountID = useraccountID;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
