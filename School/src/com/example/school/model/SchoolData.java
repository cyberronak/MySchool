package com.example.school.model;

import java.util.ArrayList;
import java.util.List;

public class SchoolData {

	private int school_id;
	private String school_name;

	public static List<SchoolData> schoolList = new ArrayList<SchoolData>();
	
	public List<SchoolData> getSchoolList() {
		return schoolList;
	}
	
	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
}
