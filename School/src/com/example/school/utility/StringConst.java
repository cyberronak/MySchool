package com.example.school.utility;

public class StringConst {
	// WebService Url's
	// School Authentication module URL'S
	// DEFAULT URL
	public static final String DEFAULT_URL="http://sellmarks.in/tejas/web_services/";
	
	// State Information:(Ex: http://sellmarks.in/tejas/web_services/state.php)
	public static final String URL_STATE_INFO = DEFAULT_URL + "state.php";

	// City Information:
	// (Ex: http://sellmarks.in/tejas/web_services/city.php?state_id=12)
	// Parameter-passing ==> state_id
	public static final String URL_CITY_INFO = DEFAULT_URL + "city.php";

	// School Select:
	// (Ex: http://sellmarks.in/tejas/web_services/school_select.php?city_id=12)
	// Parameter-passing ==> city_id
	public static final String URL_SCHOOL_INFO = DEFAULT_URL + "school_select.php";

	// Student Login:
	// (Ex: http://sellmarks.in/tejas/web_services/login.php?id=1&uname=tejc63@gmail.com&password=12345678)
	// Parameter-passing ==> id, uname(email & mobile no.) ,password
	public static final String URL_STUDENT_LOGIN = DEFAULT_URL + "login.php";

	// Forget Password:
	// (Ex: http://sellmarks.in/tejas/web_services/forget.php?id=2&uname=9725036879)
	// Parameter-passing ==> id, uname(email & mobile no.)
	public static final String URL_FORGET_PW = DEFAULT_URL + "forget.php";

	// About School:
	// (Ex: http://sellmarks.in/tejas/web_services/school_info.php?id=2)
	// Parameter-passing ==> id
	public static final String URL_ABOUT_SCHOOL = DEFAULT_URL + "school_info.php";

	// School Notice:
	// (Ex: http://sellmarks.in/tejas/web_services/notice.php?id=2)
	// Parameter-passing ==> id
	public static final String URL_SCHOOL_NOTICE = DEFAULT_URL + "notice.php";

	// Change Password:
	// (Ex: http://sellmarks.in/tejas/web_services/change_password.php?id=1&student_id=2&opassword=25105075&npassword=124578) 
	// Parameter-passing ==> id, student_id, opassword, npassword
	public static final String URL_CHANGE_PW = DEFAULT_URL + "change_password.php";
	
	// RK TEMP URL'S
//	public static final String URL_SIGN_IN = "http://ronak.hostei.com/android/index.php";
//	public static final String URL_SIGN_UP = "http://ronak.hostei.com/android/index.php";
//	public static final String URL_FORGOT = "http://ronak.hostei.com/android/index.php";

	public static final String CONNECTION = "Connection";
	public static final String ERROR_ON_NETWORK = "Error in Network Connection";

	// URL Flags
	// Parameter Passing
	// Parameter For State
	public static final String CITY_PARM = "state_id";

	// Parameter For City
	public static final String SHCOOL_PARM = "city_id";	
	
	// Parameter For Login, Notice and About School ID is same
	public static final String ID_PARAM= "id";
	public static final String UNAME_PARAM= "uname";
	public static final String PASSWORD_PARAM= "password";


	// School Authentication module flags
	// State flag
	public static final String STATE_ITEM = "state";
	// City flag
	public static final String CITY_ITEM = "city";
	// School flag
	public static final String SCHOOL_ITEM = "school_list";
	// About school flag
	public static final String ABOUT_SCHOOL_ITEM = "about_school";
	// Login flag
	public static final String STUDENT_LOGIN = "login";
	// Forgot password flag
	public static final String FORGOT_PW = "forgot_pw";
	// Change password flag
	public static final String CHANGE_PW = "change_pw";
	// School notice flag
	public static final String SCHOOL_NOTICE = "notice";
	
	// RK TEMP FLAGS
	public static final String AUTHENTICATE = "Authenticate";
	public static final String SIGN_IN = "login";
	public static final String SIGN_UP = "register";

	// School API response
	public static final String RESPONSE_CODE = "code";
	public static final String JSON_DATA = "data";
	
	// State response data flags
	public static final String S_ID= "sid";
	public static final String S_NAME= "sname";
	
	// City response data flags
	public static final String C_ID= "cid";
	public static final String C_NAME= "cname";
	
	// School response data flags
	public static final String SCHOOL_ID= "school_id";
	public static final String SCHOOL_NAME= "school_name";	
	
	// Login response data flags
	public static final String STUDENT_ID = "student_id";
	public static final String STUDENT_ROLL_NO = "rollno";
	public static final String STUDENT_FNAME = "sname";
	public static final String STUDENT_LNAME = "name";
	public static final String STUDENT_EMAIL = "email";
	public static final String STUDENT_DOB = "bdate";
	public static final String STUDENT_MOBILE_NO = "mno";
	public static final String STUDENT_IMAGE = "image";
	
	// School Notice response data flags
	public static final String NOTICE = "notic";
	public static final String NOTICE_SUB= "notic_sub";
	public static final String NOTICE_DATE = "notic_date";
	
	// About School response data flags
	public static final String SINFO_NAME= "school_name";
	public static final String SINFO_ADDRESS= "saddress";	
	public static final String SINFO_CITY= "scity";	
	public static final String SINFO_STATE= "sstate";	
	public static final String SINFO_EMIAL= "semail";	
	public static final String SINFO_MOB= "smno";	
	public static final String SINFO_LOGO= "slogo";	
	public static final String SINFO_INFO= "info";
	
	// Session SharedPreferences manage
	public static final String My_PREFERENCES = "SessionManage";
	public static final String SINFO_PREFERENCES = "SchoolInfo";

	// RK TEMP FLAGS
	public static final String RESPONSE_FLAG = "Flag";
	public static final String RESPONSE_SUCCESS = "success";
	public static final String RESPONSE_ERROR = "error";
	public static final String Flag = "flag";
	public static final String EMAIL_EXP = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})";

	// Toast messages
	public static final String SUCCESS_LOGIN = "Successfully login";
	public static final String ERROR_SIGN_IN = "Error occured in sign-in";
	public static final String SUCCESS_SIGN_UP = "Successfully sign-up";
	public static final String ERROR_SIGN_UP = "Error occured in  sign-up";
	public static final String SUCCESS_PASSWORD = "Successfully password updated";
	public static final String LOADING_MSG = "Please wait loading...";

	// SchoolAuthentication form
	public static final String MY_STATE = "State";	
	public static final String MY_CITY = "City";
	public static final String MY_SCHOOL = "School";
	public static final String MY_SCHOOL_ID = "School_Id";
	public static final String SPINNER_ERRO = "Please select spinner value...";

	// Login form
	public static final String ENTER_USERNAME = "Please enter username";
	public static final String ENTER_PASSWORD = "Please enter password";
	public static final String VALID_EMAIL_PHONE = "Enter a valid email address or mobile-no";
	public static final String VALID_PASSWORD = "Between 4 and 10 alphanumeric characters";
	public static final String NO_RESOURCE_FOUND = "Sorry no resource found...";

	// Registration form
	public static final String USERNAME = "uname";
	public static final String FIRSTNAME = "fname";
	public static final String LASTNAME = "lname";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String CREATED_AT = "created_at";

	public static final String USER_EXIST = "User already exists";

}
