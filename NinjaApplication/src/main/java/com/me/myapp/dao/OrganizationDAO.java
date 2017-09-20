package com.me.myapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.pojo.Announcement;
import com.me.myapp.pojo.Contact;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.Organization;
import com.me.myapp.pojo.Role;




@SuppressWarnings("deprecation")
public class OrganizationDAO extends DAO {

	public OrganizationDAO() {
	}
	
	public ArrayList<Organization> listOfOrgs() throws OrganizationException{
		try {
        	
            begin();
        	String q="from Organization";
        	Query query=getSession().createQuery(q);
            ArrayList<Organization> orgs=new ArrayList<Organization>();
            orgs=(ArrayList<Organization>) query.list();
            commit();
            return orgs;
        } catch (HibernateException e) {
            rollback();
            throw new OrganizationException("Could not update Organization", e);
        }
		
	}

	
	 public void update(Organization org) throws OrganizationException {
	        try {
	        	
	            begin();
	        	System.out.println("Org is updated");
	            getSession().update(org);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new OrganizationException("Could not update Organization", e);
	        }
	    }
	
	 public Organization getOrgByname(String orgName) throws EmployeeException, OrganizationException{
			try {
				begin();
				String q="";
				
				q="from Organization where orgName= :orgName";
				Query q1 = getSession().createQuery(q);
				q1.setString("orgName", orgName);
				
				Organization org=(Organization) q1.uniqueResult();
				if(org!=null)
				{
				System.out.println("Org name: "+org.getOrgName());
				}
				commit();
				return org;
			} catch (HibernateException e) {
				rollback();
				throw new OrganizationException("Could not get organization with " + orgName, e);
			}
		}
	
	public List<Employee> getEmployees(String orgName) throws EmployeeException, OrganizationException{
		try {
			begin();
			String q="";
			
			q="from Organization where orgName= :orgName";
			Query q1 = getSession().createQuery(q);
			q1.setString("orgName", orgName);
			
			Organization org=(Organization) q1.uniqueResult();
			System.out.println("Org name: "+org.getOrgName());
			Set<Employee> emps=org.getEmployees();
			List<Employee> employees=new ArrayList<Employee>();
			for(Employee emp:emps){
				employees.add(emp);
			}
			 
			
			System.out.println("Employees size: "+employees.size());
			commit();
			return employees;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not get organization with " + orgName, e);
		}
	}
	 

	public List<Contact> getContactsByOrg(String orgName) throws EmployeeException, OrganizationException{
		try {
			begin();
			String q="";
			
			q="from Organization where orgName= :orgName";
			Query q1 = getSession().createQuery(q);
			q1.setString("orgName", orgName);
			
			Organization org=(Organization) q1.uniqueResult();
			System.out.println("Org name: "+org.getOrgName());
			List<Contact> contacts=org.getContacts();
			
			commit();
			return contacts;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not get organization with " + orgName, e);
		}
	}
	public Organization register(Organization org)
			throws OrganizationException {
		try {
			begin();
			System.out.println("inside DAO");			
			getSession().save(org);
			commit();
			return org;

		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Exception while creating Organization: " + e.getMessage());
		}
	}

	public void delete(Organization org) throws OrganizationException {
		try {
			begin();
			getSession().delete(org);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not delete user " + org.getOrgName(), e);
		}
	}
}