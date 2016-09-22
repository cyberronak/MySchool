package com.example.school.model;

public class LeaderBoardData {

	public LeaderBoardData(String st_name, String st_rank, 
			String st_marks,String st_image) {
		// TODO Auto-generated constructor stub
		this.st_name=st_name;
		this.st_rank=st_rank;
		this.st_marks=st_marks;
		this.st_image=st_image;
	}

	private String st_name;
	private String st_rank;
	private String st_marks;
	private String st_image;

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getSt_rank() {
		return st_rank;
	}

	public void setSt_rank(String st_rank) {
		this.st_rank = st_rank;
	}

	public String getSt_marks() {
		return st_marks;
	}

	public void setSt_marks(String st_marks) {
		this.st_marks = st_marks;
	}

	public String getSt_image() {
		return st_image;
	}

	public void setSt_image(String st_image) {
		this.st_image = st_image;
	}
}
