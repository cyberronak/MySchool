package com.example.school.model;

public class StudentData {

	private static StudentData instance = null;

	protected StudentData() {
		// Exists only to defeat instantiation.
	}

	public static StudentData getInstance() {
		if (instance == null) {
			instance = new StudentData();
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	private int id;
	private String rollno;
	private String fistname;
	private String lastname;
	private String email;
	private String mobileno;
	private String dob;
	private String image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public String getFistname() {
		return fistname;
	}

	public void setFistname(String fistname) {
		this.fistname = fistname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
