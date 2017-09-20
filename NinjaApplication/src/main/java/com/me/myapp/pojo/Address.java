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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="address_table")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="addressid",unique=true, nullable = false)
	private long addressid;

	@Column(name = "street")
	private String street;

	@Enumerated(EnumType.STRING)
	@Column(name="addresstype")
	private AddressType addresstype;
	
	@ManyToOne
	@JoinColumn(name="empid",nullable=false)
	private Employee employee;

	public Address() {
	}

	public Address(String street,AddressType addresstype) {
		this.street =street;
		this.addresstype=addresstype;
	}

	public long getAddressId() {
		return addressid;
	}

	public void setId(long id) {
		this.addressid = id;
	}

	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public AddressType getAddresstype() {
		return addresstype;
	}

	public void setAddresstype(AddressType addresstype) {
		this.addresstype = addresstype;
	}

	
	
}
