package com.me.myapp.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="org_table")
public class Organization {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="org_id",unique=true, nullable = false)
	private long org_id;
	
	
	@Column(name="orgName",unique=true, nullable = false)
	private String orgName;
	
	@OneToOne(mappedBy="org",cascade=CascadeType.ALL)
	private Admin admin;
	
	
	
	
	@OneToMany(mappedBy="org")
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<Employee> employees=new HashSet<Employee>();
	
	
	@OneToMany(mappedBy="org")
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<Role> roles=new HashSet<Role>();
	
	@OneToMany(mappedBy="org",cascade=CascadeType.ALL)
	private List<Announcement> announcements=new ArrayList<Announcement>();
	
	@OneToMany(mappedBy="org",cascade=CascadeType.ALL)
	private List<KnowledgeChapter> knowledgeHeap=new ArrayList<KnowledgeChapter>();
	
	@OneToMany(mappedBy="org",cascade=CascadeType.ALL)
	private List<OrgImage> OrgIamges=new ArrayList<OrgImage>();
	
	@OneToMany(mappedBy="org",cascade=CascadeType.ALL)
	private List<Contact> contacts=new ArrayList<Contact>();
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<OrgImage> getOrgIamges() {
		return OrgIamges;
	}

	public void setOrgIamges(List<OrgImage> orgIamges) {
		OrgIamges = orgIamges;
	}

	public Organization(){
		
	}
	
	public Organization(String orgName,Admin admin){
		this.orgName=orgName;
		this.admin=admin;
	}


	public long getOrg_id() {
		return org_id;
	}

	public void setOrg_id(long org_id) {
		this.org_id = org_id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	

	public List<KnowledgeChapter> getKnowledgeHeap() {
		return knowledgeHeap;
	}

	public void setKnowledgeHeap(List<KnowledgeChapter> knowledgeHeap) {
		this.knowledgeHeap = knowledgeHeap;
	}

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}

	

	public String toString(){
		return this.orgName;
	}
	
	
	
	

}
