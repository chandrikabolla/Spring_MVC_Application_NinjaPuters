package com.me.myapp.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


public enum Level {
	
	
	Administrative("Administrative"),Executive("Executive"),Operational("Operational");
	
	private String value;
    private Level(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value=value;
    }

    public static ArrayList<String> getValues(){
    	List<String> levels=new ArrayList<String>();
    	for(Level l:values()){
    		levels.add(l.getValue());
    	}
    	return (ArrayList<String>) levels; 
    }
    
    public static List<Level> getLevels(){
    	List<Level> levels=new ArrayList<Level>();
    	levels.add(Level.Administrative);
    	levels.add(Level.Executive);
    	levels.add(Level.Operational);
    	
    	return levels;
    }
    
    @Override
    public String toString() {
        return value;
    }

}
