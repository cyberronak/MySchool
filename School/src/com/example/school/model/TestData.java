package com.example.school.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.widget.TableLayout;

public class TestData {

	private String title;
	private String rank;
	private List<TestResult> result;
	private Bitmap imageArrow;

	public static List<TableLayout> tblList = new ArrayList<TableLayout>();
		
	public TestData(String title, String rank, List<TestResult> result,
			Bitmap imageArrow) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.rank = rank;
		this.result = result;
		this.imageArrow = imageArrow;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public List<TestResult> getResult() {
		return result;
	}

	public void setResult(TestResult tr) {
		result.add(tr);
	}

	public Bitmap getImageArrow() {
		return imageArrow;
	}

	public void setImageArrow(Bitmap imageArrow) {
		this.imageArrow = imageArrow;
	}
}
