package com.me.myapp.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.myapp.exception.OrgImageException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.pojo.Announcement;
import com.me.myapp.pojo.OrgImage;

public class OrgImageDAO extends DAO {

	public ArrayList<OrgImage> getOrgAlbum(String orgName)
	{
		ArrayList<OrgImage> orgImages=new ArrayList<OrgImage>();
		try{
			begin();
			Criteria crit=getSession().createCriteria(OrgImage.class);
			crit.createAlias("org","org");
			crit.add(Restrictions.eq("org.orgName",orgName ));
			orgImages=(ArrayList<OrgImage>) crit.list();
			commit();
			return orgImages;
		}catch(HibernateException e){
			
		}
		
		
		return orgImages;
		
	}
	public OrgImage register(OrgImage orgImage) throws OrgImageException 
		 {
		try {
			begin();
			System.out.println("inside OrgIamgeDAO");			
			getSession().save(orgImage);
			commit();
			return orgImage;

		} catch (HibernateException e) {
			rollback();
			throw new OrgImageException("Exception while uploading Image into Org: " + e.getMessage());
		}
	}

	public void delete(OrgImage orgImage) throws OrgImageException  {
		try {
			begin();
			getSession().delete(orgImage);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OrgImageException("Could not delete "+orgImage.getOccasion(), e);
		}
	}
	
	public OrgImage getImageByOccasion(String occasion) throws OrgImageException{
		try{
		begin();
		String q="";
		q="from OrgImage where occasion= :occasion";
		Query q1 = getSession().createQuery(q);
		q1.setString("occasion",occasion);
		OrgImage orgImage=(OrgImage)q1.uniqueResult();
		commit();
		return orgImage;
		}catch(HibernateException e)
		{
			rollback();
            throw new OrgImageException("Could not get an image with occasion:"+occasion, e);
		}
		
	}

	 public void update(OrgImage orgImage) throws OrgImageException  {
	        try {
	        	
	            begin();
	        	
	            getSession().update(orgImage);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new OrgImageException("Could not update Role", e);
	        }
	    }
}
