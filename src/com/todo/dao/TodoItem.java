package com.todo.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private String category;
    private String current_date;
    private String due_date;
//    private Date current_date;
    
    public TodoItem(String title, String desc, String category){
        this.title=title;
        this.desc=desc;
        this.category = category;
        
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.setCurrent_date(df.format(date));
    }
    
    public TodoItem(String title, String category, String desc, String duedate){
        this.title=title;
        this.desc=desc;
        this.category = category;
        this.due_date = duedate;
        
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.setCurrent_date(df.format(date));
        this.setDue_date(duedate);
        
//        Calendar cur = Calendar.getInstance();
//        Calendar due = Calendar.getInstance();
//        cur.setTime(new Date());
//        due.setTime(new Date());
//        DateFormat dff = new SimpleDateFormat("yyyy/MM/dd");
//
//        due.add(Calendar.DATE,  );
//        
//        this.setCurrent_date(curDf.format(cur.getTime()) +  " - " + dueDf.format(due.getTime()));
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
    
    public String getCategory() {
    	return this.category;
    }
    
    public void setCategory(String category) {
    	this.category = category;
    }

    public String getCurrent_date() {
        return current_date;
    }

//    public void setCurrent_date(Date date) {
//    	
//        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH::mm:ss");
//        this.current_date = df.format(date);
//    }
    
    public void setCurrent_date(String date) {
        this.current_date = date;
    }
    
    public String getDue_date() {
    	return this.due_date;
    }
    
    public void setDue_date(String due_date) {
    	this.due_date = due_date + current_date.substring(10);
    }
    
    public String toSaveString() {
    	return title + "##" + category+ "##" + desc + "##" + current_date.substring(0,10)+ " - " + due_date;
    }
}
