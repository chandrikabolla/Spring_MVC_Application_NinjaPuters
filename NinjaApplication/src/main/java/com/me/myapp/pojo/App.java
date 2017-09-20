package com.me.myapp.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {

	private String appName;
	private List<String> developers=new ArrayList<String>();
	private Date launchDate;
	private Date lastUpdated;
	
	private List<Integer> versions=new ArrayList<Integer>();
	
	public App(String appName,ArrayList<String> developers,Date launchDate,Date lastUpdated,ArrayList<Integer> versions){
		
		this.appName=appName;
		this.developers=developers;
		this.launchDate=launchDate;
		this.lastUpdated=lastUpdated;
		this.versions=versions;
		
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<String> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<String> developers) {
		this.developers = developers;
	}

	public List<Integer> getVersions() {
		return versions;
	}

	public void setVersions(List<Integer> versions) {
		this.versions = versions;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
}
