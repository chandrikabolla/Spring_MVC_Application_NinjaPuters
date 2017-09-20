package com.me.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.tools.config.Property;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.exception.RoleException;
import com.me.myapp.pojo.Address;
import com.me.myapp.pojo.AddressType;
import com.me.myapp.pojo.Announcement;
import com.me.myapp.pojo.Email;
import com.me.myapp.pojo.EmailType;
import com.me.myapp.pojo.Employee;
import com.me.myapp.pojo.EmployeeUserAccount;
import com.me.myapp.pojo.Organization;

public class AnnouncementDAO extends DAO{

	public AnnouncementDAO() {
	}
	
	public void update(Announcement announcement) throws EmployeeException {
        try {
        	
            begin();
        	
            getSession().update(announcement);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new EmployeeException("Could not update Announcment", e);
        }
    }

public ArrayList<Announcement> listAnnouncements(String orgname,int from,int max){
		
		try{
			begin();
			Criteria crit=getSession().createCriteria(Announcement.class);
			crit.createAlias("org","org");
			crit.add(Restrictions.eq("org.orgName",orgname ));
			crit.setFirstResult(from);
			crit.setMaxResults(max);
			ArrayList<Announcement> announcements=(ArrayList<Announcement>) crit.list();
			
			
			commit();
			return announcements;
		}
		catch(HibernateException e)
		{
			rollback();
			
		}
		return null;
	}
	
	 
	public Announcement register(Announcement announcement)
			throws EmployeeException, RoleException, OrganizationException {
		try {
			begin();
			System.out.println("inside DAO");
			
			getSession().save(announcement);
		
			commit();
			return announcement;

		} catch (HibernateException e) {
			rollback();
			throw new EmployeeException("Exception while creating announcement: " + e.getMessage());
		}
	}

	public void delete(Announcement ann) throws OrganizationException {
		try {
			begin();
			getSession().delete(ann);
			commit();
		} catch (HibernateException e) {
			rollback();


		}
	}
	
	public Announcement getAnnouncementByTitle(String title) throws EmployeeException{
		
		try{
			begin();
			String q="from Announcement where title= :title";
			Query query=getSession().createQuery(q);
			query.setString("title", title);
			
			Announcement ann=(Announcement)query.uniqueResult();
			commit();
			return ann;
			
		}
		catch(HibernateException e)
		{
			
			rollback();
			throw new EmployeeException("Could not get announcmenet  with title:  " +title, e);
		}
		
	}
}
