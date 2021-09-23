package com.todo.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
//    private Date current_date;


    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM--dd HH::mm:ss");
        this.current_date = df.format(date);
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

    public void setCurrent_date(Date date) {
    	
        DateFormat df = new SimpleDateFormat("yyyy-MM--dd HH::mm:ss");
        this.current_date = df.format(date);
    }
    
    public void setCurrent_date(String date) {
        this.current_date = date;
    }
    
    public String toSaveString() {
    	return title + "##" + desc + "##" + current_date + "\n";
    }
}
