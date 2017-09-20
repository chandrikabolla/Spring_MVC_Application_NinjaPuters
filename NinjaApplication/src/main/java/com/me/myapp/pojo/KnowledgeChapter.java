package com.me.myapp.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="knowledgechapter_table")
public class KnowledgeChapter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="knowledgeChapterid",unique=true, nullable = false)
	private long knowledgeChapterid;
	
	
	@Column(name="title",unique=true,nullable=false)
	private String title;
	
	
	
	
	@Column(name="numOffiles",nullable=false)
	private Long numOffiles;
	
	@OneToMany(mappedBy="knowledgeChapter",cascade=CascadeType.ALL)
	private List<FileUpload> fileUploads=new ArrayList<FileUpload>();
	
	
	
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization org;
	
	public KnowledgeChapter(){
		
	}



	public long getKnowledgeChapterid() {
		return knowledgeChapterid;
	}



	public void setKnowledgeChapterid(long knowledgeChapterid) {
		this.knowledgeChapterid = knowledgeChapterid;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public Organization getOrg() {
		return org;
	}



	public void setOrg(Organization org) {
		this.org = org;
	}

	




	public Long getNumOffiles() {
		return numOffiles;
	}



	public void setNumOffiles(Long numOffiles) {
		this.numOffiles = numOffiles;
	}



	public List<FileUpload> getFileUploads() {
		return fileUploads;
	}



	public void setFileUploads(List<FileUpload> fileUploads) {
		this.fileUploads = fileUploads;
	}



	
	
	
	
	
}
