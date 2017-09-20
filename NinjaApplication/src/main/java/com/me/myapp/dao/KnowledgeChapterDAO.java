package com.me.myapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.myapp.exception.EmployeeException;
import com.me.myapp.exception.OrganizationException;
import com.me.myapp.pojo.KnowledgeChapter;
import com.me.myapp.pojo.Organization;

public class KnowledgeChapterDAO extends DAO {

	
	public KnowledgeChapterDAO(){
		
	}
	 public void update(KnowledgeChapter kc) throws OrganizationException {
	        try {
	        	
	            begin();
	        	
	            getSession().update(kc);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new OrganizationException("Could not update KnowledgeChapter", e);
	        }
	    }
	
	 public KnowledgeChapter getKCBytitle(String title) throws EmployeeException, OrganizationException{
			try {
				begin();
				String q="";
				
				q="from KnowledgeChapter where title= :title";
				Query q1 = getSession().createQuery(q);
				q1.setString("title", title);
				
				KnowledgeChapter kc=(KnowledgeChapter)q1.uniqueResult();
				System.out.println("Title: "+kc.getTitle());
				commit();
				return kc;
			} catch (HibernateException e) {
				rollback();
				throw new OrganizationException("Could not get KnowledgeChapter with " + title, e);
			}
		}
	 
	 
	 public KnowledgeChapter register(KnowledgeChapter kc)
				throws OrganizationException {
			try {
				begin();
				System.out.println("inside DAO");			
				getSession().save(kc);
				commit();
				return kc;

			} catch (HibernateException e) {
				rollback();
				throw new OrganizationException("Exception while uploading KnowledgeChapter: " + e.getMessage());
			}
		}

		public void delete(KnowledgeChapter kc) throws OrganizationException {
			try {
				begin();
				getSession().delete(kc);
				commit();
			} catch (HibernateException e) {
				rollback();
				throw new OrganizationException("Could not delete knowledgeChapter " + kc.getTitle(), e);
			}
		}
}
