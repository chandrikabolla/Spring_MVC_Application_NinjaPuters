package com.me.myapp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Role_table")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleid",unique=true, nullable = false)
	private long roleid;
	
	@Column(name="roleName", nullable = false)
	private String roleName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="level")
	private Level level;
	
	@ManyToOne
	@JoinColumn(name="org_id",nullable=false)
	private Organization org;
	
	@OneToMany(mappedBy="empRole",cascade=CascadeType.ALL)
	private Set<Employee> employees=new HashSet<Employee>();
	
	public Role(){
		
	}
	
	public Role(String roleName,Level level,Organization org){
		this.roleName=roleName;
		this.level=level;
		this.org=org;
	}

	public long getRoleId() {
		return roleid;
	}

	public void setId(long id) {
		this.roleid = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
	
	@Override
	public String toString(){
		return roleName;
	}
	
}
