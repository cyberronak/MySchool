package com.example.school.model;

public class NoticeData {
	public enum ANNOUNCEMENT_TYPE
	{
		NEWS,
		EVENTS,
		EXAMS		
	}
	
	private ANNOUNCEMENT_TYPE type;
	private String title;
	private String sub_title;
	private String desc;
	private String date;
	
	public NoticeData(ANNOUNCEMENT_TYPE type, String title, String sub_title, String desc, String date) {
		// TODO Auto-generated constructor stub
		this.type=type;
		this.title=title;
		this.sub_title=sub_title;
		this.desc=desc;
		this.date=date;
	}
	
	public ANNOUNCEMENT_TYPE getType() {
		return type;
	}
	public void setType(ANNOUNCEMENT_TYPE type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
