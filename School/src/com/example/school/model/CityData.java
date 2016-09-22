package com.example.school.model;

import java.util.ArrayList;
import java.util.List;

public class CityData {

	private int city_id;
	private String city_name;

	public static List<CityData> cityList = new ArrayList<CityData>();

	public List<CityData> getCityList() {
		return cityList;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
}