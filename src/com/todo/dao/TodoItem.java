package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
	private int id;
	private int importance;
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int completeness;
    private boolean is_done;
    
    
    public TodoItem(String title, String desc, int importance, String category, String due_date){
    	this.title=title;
        this.desc=desc;
        this.importance = importance;
        this.category=category;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.due_date=due_date.trim();
        if(due_date.length() <= 10)
        	this.due_date += this.current_date.substring(10);
        this.completeness = 0;
        this.is_done = false;
        
    }
        
    public TodoItem(int id, String title, String desc, int importance, String category, String due_date){
        this.id = id;
    	this.title=title;
        this.desc=desc;
        this.importance = importance;
        this.category=category;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.due_date=due_date.trim();
        if(due_date.length() <= 10)
        	this.due_date += this.current_date.substring(10);
        this.completeness = 0;
        this.is_done = false;
    }
    
    public TodoItem(String title, String desc, int importance, String category, String due_date, int completeness, boolean is_done){
    	this.title=title;
        this.desc=desc;
        this.importance = importance;
        this.category=category;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.due_date=due_date.trim();
        if(due_date.length() <= 10)
        	this.due_date += this.current_date.substring(10);
        this.completeness = completeness;
        this.is_done = is_done;
        
    }
        
    public TodoItem(int id, String title, String desc, int importance, String category, String due_date, int completeness, boolean is_done){
        this.id = id;
    	this.title=title;
        this.desc=desc;
        this.importance = importance;
        this.category=category;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.due_date=due_date.trim();
        if(due_date.length() <= 10)
        	this.due_date += this.current_date.substring(10);
        this.completeness = completeness;
        this.is_done = is_done;
    }
    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory() {
    	return category;
    }
    
    public void setCategory(String category) {
    	this.category = category;
    }
    
    public String getDue_date() {
    	return due_date;
    }
    
    public void setDue_date(String due_date) {
    	this.due_date = due_date;
    }
    public void setId(int id) {
		this.id = id;
	}
    
    public void setImportance(int importane) {
    	this.importance = importance;
    }
    
    public void setCompleteness(int completeness) {
    	this.completeness = completeness;
    }
    
    public void setIs_done(boolean is_done) {
    	this.is_done = is_done;
    }
    
    public int getId() {
		return this.id;
	}
    
    public int getImportance() {
    	return this.importance;
    }
    
    public int getCompleteness() {
    	return this.completeness;
    }
    
    public boolean getIs_done() {
    	return this.is_done;
    }
    
    
    public String toSaveString() {
    	return this.category + "##" + this.importance + "##" + this.title + "##" + this.desc + "##" + this.current_date + "##" + this.due_date + "##" + this.completeness + "##" + this.is_done + "\n";
    }
    
    @Override
    public String toString() {
    	String returnStr = "[" + this.category + "]" + "[중요도:" + this.importance + "] " + this.title +  " - " + this.desc + " - " + this.current_date.substring(0,10) + " - " + this.due_date + " - [완료율: " + this.completeness + "]";
    	if(this.is_done)
    		returnStr += " - [완료]";
    	else
    		returnStr += " - [미완료]";
    	return returnStr;
    }

	

	
}
