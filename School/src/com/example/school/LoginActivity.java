package com.example.school;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.school.Handler.UserDataHandler;
import com.example.school.connection.AsyncInterface;
import com.example.school.utility.ConstantUtility;
import com.example.school.utility.StringConst;
import com.example.school.webservice.WebService;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements AsyncInterface {
	private SharedPreferences shpref;
	private ProgressDialog pDialog;

	private EditText _emailText;
	private EditText _passwordText;
	private Button _loginButton;
	private TextView _forgotLink;
	private String email, password;
	private Typeface custom_font;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);

		// load custom fonts
		custom_font = Typeface.createFromAsset(getAssets(), "fonts/American_Typewriter_Regular.ttf");

		//opening transition animations
		overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_right);
		
		_emailText = (EditText) findViewById(R.id.input_email);
		_emailText.setTypeface(custom_font);
		_passwordText = (EditText) findViewById(R.id.input_password);
		_passwordText.setTypeface(custom_font);
		_loginButton = (Button) findViewById(R.id.btn_login);
		_loginButton.setTypeface(custom_font);
		_forgotLink = (TextView) findViewById(R.id.link_forgot);
		_forgotLink.setTypeface(custom_font);

		shpref = getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);
		
		_loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// login();
				if (!validate()) {
					onLoginFailed();
					return;
				}

				pDialog = new ProgressDialog(v.getContext());
				pDialog.setMessage(StringConst.LOADING_MSG);
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();

				UserDataHandler handler = new UserDataHandler();
				ArrayList<NameValuePair> valuePairs = handler.loginUser(email,
						password);

				_loginButton.setEnabled(false);

				WebService service = new WebService(getApplicationContext(),
						StringConst.SIGN_IN, valuePairs);
				service.mListener = LoginActivity.this;
				service.execute();
			}
		});

		_forgotLink.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
				builder.setTitle("Forgot Password");
				builder.setCancelable(true);
				LayoutInflater inflater = LayoutInflater.from(getApplication());

				View dialogView = inflater.inflate(R.layout.forgot_dialog, null);
				builder.setView(dialogView);

				final EditText _forgotEmail = (EditText) dialogView.findViewById(R.id.input_forgot_email);
				_forgotEmail.setTypeface(custom_font);
//				Button _forgotSubmitButton = (Button) dialogView.findViewById(R.id.btn_forgot_submit);
//				_loginButton.setTypeface(custom_font);
//

				builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// positive button logic
						String forgotEmail =_forgotEmail.getText().toString();
						if (forgotEmail.isEmpty()
								|| !android.util.Patterns.EMAIL_ADDRESS.matcher(forgotEmail)
										.matches()) {
							_forgotEmail.setError(StringConst.Valid_EMAIL);
							return;
						} else {
							_forgotEmail.setError(null);
						}
						
						Toast.makeText(getApplicationContext(), "Successfully password updation link sent to your mail...", Toast.LENGTH_SHORT).show();
					}
				});

				AlertDialog dialog = builder.create();
				// display dialog
				dialog.show();
			}
		});
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
		//closing transition animations
		overridePendingTransition(R.anim.slide_in_right,
				R.anim.slide_out_left);	}

	public void onLoginSuccess() {
		_loginButton.setEnabled(true);
		pDialog.cancel();
		finish();
		//closing transition animations
		overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_right);
	}

	public void onLoginFailed() {
		Toast.makeText(getBaseContext(), StringConst.ERROR_SIGN_IN,
				Toast.LENGTH_LONG).show();
		_loginButton.setEnabled(true);
	}

	public boolean validate() {
		boolean valid = true;
		email = _emailText.getText().toString();
		password = _passwordText.getText().toString();

		if (email.isEmpty()
				|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
						.matches()) {
			_emailText.setError(StringConst.Valid_EMAIL);
			valid = false;
		} else {
			_emailText.setError(null);
		}

		if (password.isEmpty() || password.length() < 4
				|| password.length() > 10) {
			_passwordText.setError(StringConst.Valid_PASSWORD);
			valid = false;
		} else {
			_passwordText.setError(null);
		}

		return valid;
	}

	@Override
	public void asyncResult(Object result, String flag) {
		// TODO Auto-generated method stub
		if (flag.equals(StringConst.CONNECTION)) {
			if ((Boolean) result == true) {

			} else {
				Toast.makeText(this, StringConst.ERROR_ON_NETWORK,
						Toast.LENGTH_LONG).show();
				_loginButton.setEnabled(true);
				pDialog.cancel();
			}
		} else if (flag.equals(StringConst.SIGN_IN)) {
			String finalResult = (String) result;
			JSONObject json_data;
			try {
				json_data = new JSONObject(finalResult);
				String success = (json_data
						.getString(StringConst.RESPONSE_SUCCESS));
				if (Integer.parseInt(success) == 1) {
					Toast.makeText(this, StringConst.SUCCESS_SIGN_IN,
							Toast.LENGTH_SHORT).show();

					JSONObject json_user = json_data
							.getJSONObject(StringConst.USER_DATA);
					shpref = getSharedPreferences(StringConst.My_PREFERENCES,
							Context.MODE_PRIVATE);

					SharedPreferences.Editor editor = shpref.edit();

					editor.putString(StringConst.FIRSTNAME,
							json_user.getString(StringConst.FIRSTNAME));
					editor.putString(StringConst.LASTNAME,
							json_user.getString(StringConst.LASTNAME));
					editor.putString(StringConst.EMAIL,
							json_user.getString(StringConst.EMAIL));
					editor.putString(StringConst.USERNAME,
							json_user.getString(StringConst.USERNAME));
					editor.putString(StringConst.CREATED_AT,
							json_user.getString(StringConst.CREATED_AT));
					editor.commit();

					Intent upanel = new Intent(getApplicationContext(),
							MainActivity.class);
					upanel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(upanel);
					onLoginSuccess();
				} else {
					Toast.makeText(this, StringConst.NO_RESOURCE_FOUND,
							Toast.LENGTH_SHORT).show();
					_loginButton.setEnabled(true);
					pDialog.cancel();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
