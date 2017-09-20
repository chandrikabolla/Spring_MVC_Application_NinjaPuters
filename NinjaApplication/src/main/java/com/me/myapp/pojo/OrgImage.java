package com.me.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GeneratorType;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.persistence.Lob;

import java.sql.Blob;
@Entity
@Table(name="orgImage_Table")
public class OrgImage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="image_id",unique=true, nullable = false)
	private long image_id;
	public long getImage_id() {
		return image_id;
	}

	public void setImage_id(long image_id) {
		this.image_id = image_id;
	}

	@Transient
	private CommonsMultipartFile file;
	
	@Column(name="fileString",nullable=false)
	private String fileString;
	
	@Column(name="filename",unique=true,nullable=false)
	private String filename;
	
	@Column(name="occasion",unique=true,nullable=false)
	private String occasion;
	
	@Column(name="content")
	@Lob
	private Blob content;
	
	@Column(name="content_type")
	private String contentType;
	
	public String getOccasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization org;
	
	public OrgImage()
	{
		
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

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getFileString() {
		return fileString;
	}

	public void setFileString(String fileString) {
		this.fileString = fileString;
	}
	
	
}
