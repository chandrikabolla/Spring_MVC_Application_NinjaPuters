package com.me.myapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.pojo.Admin;
import com.me.myapp.pojo.AdminUserAccount;
import com.me.myapp.pojo.Organization;
import com.me.myapp.pojo.UserAccount;

public class AdminDAO extends DAO{
	
	
	public AdminDAO() {
	}

	 public void updateAdminUserAccount(AdminUserAccount aua) throws OrganizationException {
	        try {
	        	
	            begin();
	        	
	            getSession().update(aua);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new OrganizationException("Could not update Organization", e);
	        }
	    }
	 
	 public Organization getOrgByname(Admin admin) throws EmployeeException, OrganizationException{
			try {
				begin();
				String q="";
				long org_id=admin.getAdminid();
				q="from Organization where org_id= :org_id";
				Query q1 = getSession().createQuery(q);
				q1.setLong("org_id", org_id);
				
				Organization org=(Organization) q1.uniqueResult();
				System.out.println("Org name: "+org.getOrgName());
				commit();
				return org;
			} catch (HibernateException e) {
				rollback();
				throw new OrganizationException("Could not get organization with the admin " +admin.getName() , e);
			}
		}
	 
	 
	 public Admin getAdmin(AdminUserAccount aua) throws EmployeeException, OrganizationException{
			try {
				begin();
				String q="";
				String query="from UserAccount where userName= :userName and password= :password";
				Query qu=getSession().createQuery(query);
				qu.setString("userName",aua.getUserName());
				qu.setString("password", aua.getPassword());
				UserAccount uaccount=(UserAccount)qu.uniqueResult();
				Admin admin=null;
				if(uaccount!=null)
				{
					
				}
				commit();
				return admin;
			} catch (HibernateException e) {
				rollback();
				throw new OrganizationException("Could not get organization with the admin " +aua.getUserName() , e);
			}
		}
	 
	 public AdminUserAccount getAdminUserAccount(AdminUserAccount aua) throws EmployeeException, OrganizationException{
			try {
				begin();
				String q="";
				String query="from UserAccount where userName= :userName";
				Query qu=getSession().createQuery(query);
				qu.setString("userName",aua.getUserName());
				UserAccount auaccount=(UserAccount)qu.uniqueResult();
				System.out.println("aua: "+auaccount);

				commit();
				return (AdminUserAccount) auaccount;
			} catch (HibernateException e) {
				rollback();
				throw new OrganizationException("Could not get organization with the admin " +aua.getUserName() , e);
			}
		}

	 public Admin getadminByName(String adminname)
	 {
		 try{
			 begin();
			 String q="from Admin where name=:name";
			 Query q1=getSession().createQuery(q);
			 q1.setString("name",adminname);
			 Admin admin=(Admin) q1.uniqueResult();
			 
			 
			 commit();
			 return admin;
		 }
		 catch(HibernateException e)
		 {
			 rollback();
		 }
		return null;
	 }
}
