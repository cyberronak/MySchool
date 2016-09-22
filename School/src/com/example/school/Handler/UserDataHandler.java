package com.example.school.Handler;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.school.utility.StringConst;

public class UserDataHandler
{
	public ArrayList<NameValuePair>	nameValuePairs;
	
	public UserDataHandler()
	{
		// TODO Auto-generated constructor stub
		nameValuePairs = new ArrayList<NameValuePair>();
	}

	// Login Authentication
	public ArrayList<NameValuePair> loginUser(String id, String username, String password)
	{
		nameValuePairs.add(new BasicNameValuePair(StringConst.ID_PARAM, id));
		nameValuePairs.add(new BasicNameValuePair(StringConst.UNAME_PARAM, username));
		nameValuePairs.add(new BasicNameValuePair(StringConst.PASSWORD_PARAM, password));
		return nameValuePairs;
	}
	
	// Forgot Password
	public ArrayList<NameValuePair> forgotPassword(String id, String username)
	{
		nameValuePairs.add(new BasicNameValuePair(StringConst.ID_PARAM, id));
		nameValuePairs.add(new BasicNameValuePair(StringConst.UNAME_PARAM, username));
		return nameValuePairs;
	}
	
	// Register user detailes
	public ArrayList<NameValuePair> registerUser(String firstname,String lastname,String email,String username, String password)
	{
		nameValuePairs.add(new BasicNameValuePair(StringConst.Flag, StringConst.SIGN_UP));
		nameValuePairs.add(new BasicNameValuePair(StringConst.FIRSTNAME, firstname));
		nameValuePairs.add(new BasicNameValuePair(StringConst.LASTNAME, lastname));
		nameValuePairs.add(new BasicNameValuePair(StringConst.EMAIL, email));
		nameValuePairs.add(new BasicNameValuePair(StringConst.USERNAME, username));
		nameValuePairs.add(new BasicNameValuePair(StringConst.PASSWORD, password));
		return nameValuePairs;
	}

	public ArrayList<NameValuePair> getNoticeData(String id) {
		// TODO Auto-generated method stub
		nameValuePairs.add(new BasicNameValuePair(StringConst.ID_PARAM, id));
		return nameValuePairs;
	}
	
	public ArrayList<NameValuePair> getSchoolInfo(String id) {
		// TODO Auto-generated method stub
		nameValuePairs.add(new BasicNameValuePair(StringConst.ID_PARAM, id));
		return nameValuePairs;
	}
	
}
