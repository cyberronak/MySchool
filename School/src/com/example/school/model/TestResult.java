package com.example.school.model;

public class TestResult {
	private String subject;
	private String subjTotal;
	private String subjMark;

	public TestResult(String subject, String subjTotal, String subjMark) {
		// TODO Auto-generated constructor stub
		this.subject = subject;
		this.subjTotal = subjTotal;
		this.subjMark = subjMark;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubjTotal() {
		return subjTotal;
	}

	public void setSubjTotal(String subjTotal) {
		this.subjTotal = subjTotal;
	}

	public String getSubjMark() {
		return subjMark;
	}

	public void setSubjMark(String subjMark) {
		this.subjMark = subjMark;
	}
}
