package com.me.myapp.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="file_table")
public class FileUpload {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fileUploadID",unique=true, nullable = false)
	private long fileUploadID;
	
	@Transient
	private CommonsMultipartFile file;
	
	@Column(name="filename",unique=true,nullable=false)
	private String filename;
	
	@ManyToOne
	@JoinColumn(name="knowledgeChapterid",nullable=false)
	private KnowledgeChapter knowledgeChapter;
	
	@Column(name="fileString",unique=true,nullable=false)
	private String fileString;
	
	public FileUpload(){
		
	}
	
	


	public long getFileUploadID() {
		return fileUploadID;
	}




	public void setFileUploadID(long fileUploadID) {
		this.fileUploadID = fileUploadID;
	}




	public CommonsMultipartFile getFile() {
		return file;
	}


	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public KnowledgeChapter getKnowledgeChapter() {
		return knowledgeChapter;
	}


	public void setKnowledgeChapter(KnowledgeChapter knowledgeChapter) {
		this.knowledgeChapter = knowledgeChapter;
	}




	public String getFileString() {
		return fileString;
	}




	public void setFileString(String fileString) {
		this.fileString = fileString;
	}
	
	
	

}
