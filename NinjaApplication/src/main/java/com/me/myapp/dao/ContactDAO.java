package com.me.myapp.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.myapp.exception.OrganizationException;
import com.me.myapp.exception.RoleException;
import com.me.myapp.pojo.Contact;
import com.me.myapp.pojo.Organization;
import com.me.myapp.pojo.Role;

public class ContactDAO extends DAO {
	
	
	public Contact register(Contact contact)
			throws OrganizationException, RoleException {
		try {
			begin();
			System.out.println("inside RoleDAO");			
			getSession().save(contact);
			commit();
			return contact;

		} catch (HibernateException e) {
			rollback();
			throw new RoleException("Exception while creating contact: " + e.getMessage());
		}
	}

	public void delete(Contact contact) throws RoleException {
		try {
			begin();
			getSession().delete(contact);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new RoleException("Could not delete Role " +contact.getContactName(), e);
		}
	}
	
	public Contact getContactByName(String contactname)throws RoleException,OrganizationException{
		
		begin();
		String q="";
		q="from Contact where contactName= :contactName";
		Query q1 = getSession().createQuery(q);
		q1.setString("contactName",contactname);
		Contact contact=(Contact)q1.uniqueResult();
		commit();
		return contact;
		
	}
	public ArrayList<Contact> getContacts() throws RoleException
	{
		try{
			begin();
			String q="";
			q="from Contact";
			Query q1=getSession().createQuery(q);
			ArrayList<Contact> contacts=(ArrayList<Contact>) q1.list();
			return contacts;
			
		}
		catch (HibernateException e) {
            rollback();
            throw new RoleException("Could not update Role", e);
        }
	}


	 public void update(Contact contact) throws  RoleException {
	        try {
	        	
	            begin();
	        	
	            getSession().update(contact);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new RoleException("Could not update Role", e);
	        }
	    }

}
