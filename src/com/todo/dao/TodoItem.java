package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int id;
    
    public TodoItem(String title, String desc, String category, String due_date){
    	this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.category=category;
        this.due_date=due_date + current_date.substring(10);
    }
        
    public TodoItem(int id, String title, String desc, String category, String due_date){
        this.id = id;
    	this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date= f.format(new Date());
        this.category=category;
        this.due_date=due_date + current_date.substring(10);
    }
    
    public String getTitle() {
        return title;
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
    public int getId() {
		return id;
	}
    
    
    
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + current_date + "##" + due_date + "\n";
    }
    
    @Override
    public String toString() {
    	return "[" + category + "]" + title +  " - " +desc + " - " + current_date.substring(0,10) + " - " + due_date;
    }

	

	
}
