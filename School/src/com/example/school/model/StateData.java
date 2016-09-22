package com.example.school.model;

import java.util.ArrayList;
import java.util.List;

public class StateData {

	private int state_id;
	private String state_name;
	
	public static List<StateData> stateList = new ArrayList<StateData>();
	
	public List<StateData> getStateList() {
		return stateList;
	}
		
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
}
