package com.me.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="announcement_table")
public class Announcement {

	
	@Id 
	@Column(name="announcement_ID",unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long announcement_ID;
	
	@Column(name="title",unique=true,nullable=false)
	private String title;
	
	@Column(name="message",nullable=false)
	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@ManyToOne
	@JoinColumn(name="org_id",nullable=false)
	private Organization org;
	
	public Announcement(){
		
	}

	public Announcement(String title)
	{
		this.title=title;
	}
	public long getAnnouncement_ID() {
		return announcement_ID;
	}

	public void setAnnouncement_ID(long announcement_ID) {
		this.announcement_ID = announcement_ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
	
	
}
