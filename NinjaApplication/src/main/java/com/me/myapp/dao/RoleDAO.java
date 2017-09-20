package com.me.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.exception.RoleException;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.Organization;
import com.me.myapp.pojo.Role;

public class RoleDAO extends DAO{
	
	public RoleDAO() {
	}

	
	public List<Employee> getEmployees(String roleName) throws EmployeeException,  RoleException{
		try {
			begin();
			String q="";
			
			q="from Role where roleName= :roleName";
			Query q1 = getSession().createQuery(q);
			q1.setString("roleName",roleName);
			Role role=(Role)q1.uniqueResult();
			List<Employee> employees =(List<Employee>) role.getEmployees();
			commit();
			return employees;
		} catch (HibernateException e) {
			rollback();
			throw new RoleException("Could not get Role with name " + roleName, e);
		}
	}
	 
	public Role register(Role role)
			throws OrganizationException, RoleException {
		try {
			begin();
			System.out.println("inside RoleDAO");			
			getSession().save(role);
			commit();
			return role;

		} catch (HibernateException e) {
			rollback();
			throw new RoleException("Exception while creating role: " + e.getMessage());
		}
	}

	public void delete(Role role) throws RoleException {
		try {
			begin();
			getSession().delete(role);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new RoleException("Could not delete Role " +role.getRoleName(), e);
		}
	}
	
	public Role getRoleByName(String roleName)throws RoleException,OrganizationException{
		
		begin();
		String q="";
		q="from Role where roleName= :roleName";
		Query q1 = getSession().createQuery(q);
		q1.setString("roleName",roleName);
		Role role=(Role)q1.uniqueResult();
		commit();
		return role;
		
	}
	
	

	 public void update(Role role) throws  RoleException {
	        try {
	        	
	            begin();
	        	
	            getSession().update(role);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new RoleException("Could not update Role", e);
	        }
	    }
}
