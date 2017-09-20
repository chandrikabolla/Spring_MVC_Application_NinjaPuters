package com.me.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.exception.RoleException;
import com.me.myapp.pojo.Address;
import com.me.myapp.pojo.AddressType;
import com.me.myapp.pojo.Contact;
import com.me.myapp.pojo.Email;
import com.me.myapp.pojo.EmailType;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.EmployeeUserAccount;
import com.me.myapp.pojo.Organization;
import com.me.myapp.pojo.Person;
import com.me.myapp.pojo.Role;


public class EmployeeDAO extends DAO{

	
	public EmployeeDAO() {
	}
	
	public void update(Employee emp) throws EmployeeException {
        try {
        	
            begin();
        	
           String q="update Employee set firstName=:firstName where empid=:empid";
           Query query=getSession().createQuery(q);
           query.setParameter("firstName",emp.getFirstName());
           query.setParameter("empid",emp.getEmpid());
           int result=query.executeUpdate();
           System.out.println("Rows affected : "+result);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new EmployeeException("Could not update Organization", e);
        }
    }

	
	public List<Employee> getEmployees(Long org_id) throws EmployeeException, OrganizationException{
		try {
			begin();
			String q="";
			
			q="from Employee where org_id= :org_id";
			Query q1 = getSession().createQuery(q);
			q1.setLong("org_id", org_id);
	
			List<Employee> employees =q1.list();
			commit();
			return employees;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not get organization with " + org_id, e);
		}
	}
	 
	
	public EmployeeUserAccount registerEUA(EmployeeUserAccount eua,Employee emp) throws EmployeeException{
		try{
			begin();
			getSession().saveOrUpdate(eua);
			commit();
			return eua;
		}
		catch(HibernateException e)
		{
			rollback();
			throw new EmployeeException("Registering employee user account: "+e.getMessage());
		}
	}
	 
	public Employee register(Employee emp)
			throws EmployeeException, RoleException, OrganizationException {
		try {
			begin();
			System.out.println("inside DAO");
			
			getSession().save(emp);
		
			commit();
			return emp;

		} catch (HibernateException e) {
			rollback();
			throw new EmployeeException("Exception while creating employee: " + e.getMessage());
		}
	}

	public void delete(Employee emp) throws OrganizationException {
		try {
			begin();
			getSession().delete(emp);
			commit();
		} catch (HibernateException e) {
			rollback();


		}
	}
	
	public Employee getEmployeeById(String empid) throws EmployeeException{
		
		try{
			begin();
			String q="from Employee where empid= :empid";
			Query query=getSession().createQuery(q);
			query.setString("empid", empid);
			
			Employee employee =(Employee)query.uniqueResult();
			commit();
			return employee;
		}
		catch(HibernateException e)
		{
			
			rollback();
			throw new EmployeeException("Could not get user: Employee with  " + empid, e);
		}
		
	}
}
