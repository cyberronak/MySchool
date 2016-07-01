package com.example.school;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.school.Handler.UserDataHandler;
import com.example.school.connection.AsyncInterface;
import com.example.school.model.CalendarData;
import com.example.school.model.CalendarData.EventType;
import com.example.school.utility.StringConst;
import com.example.school.webservice.WebService;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements AsyncInterface {
	private SharedPreferences _shpref;
	private ProgressDialog _pDialog;

	private EditText emailText;
	private EditText passwordText;
	private Button _loginButton;
	private TextView _title, _forgotLink;
	private String email, password;
	private Typeface _customFontR, _customFontB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		// load custom fonts
		_customFontR = Typeface.createFromAsset(getAssets(),
				"fonts/American_Typewriter_Regular.ttf");
		_customFontB = Typeface.createFromAsset(getAssets(),
				"fonts/American_Typewriter_Bold.ttf");

		// opening transition animations
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

		_title = (TextView) findViewById(R.id.tv_login_title);
		_title.setTypeface(_customFontB);
		emailText = (EditText) findViewById(R.id.input_email);
		emailText.setTypeface(_customFontR);
		passwordText = (EditText) findViewById(R.id.input_password);
		passwordText.setTypeface(_customFontR);
		_loginButton = (Button) findViewById(R.id.btn_login);
		_loginButton.setTypeface(_customFontR);
		_forgotLink = (TextView) findViewById(R.id.link_forgot);
		_forgotLink.setTypeface(_customFontR);

		_shpref = getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);

		_loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// login();
				if (!validate()) {
					onLoginFailed();
					return;
				}

				_pDialog = new ProgressDialog(v.getContext());
				_pDialog.setMessage(StringConst.LOADING_MSG);
				_pDialog.setIndeterminate(false);
				_pDialog.setCancelable(false);
				_pDialog.show();

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
				AlertDialog.Builder builder = new AlertDialog.Builder(v
						.getContext());
				builder.setCancelable(true);
				LayoutInflater inflater = LayoutInflater.from(v.getContext());

				View dialogView = inflater
						.inflate(R.layout.forgot_dialog, null);
				builder.setView(dialogView);

				final TextView _forgotTitle = (TextView) dialogView
						.findViewById(R.id.tv_forgot_title);
				_forgotTitle.setTypeface(_customFontR);

				final EditText _forgotemail = (EditText) dialogView
						.findViewById(R.id.input_forgot_email);
				_forgotemail.setTypeface(_customFontR);
				// Button _forgotSubmitButton = (Button)
				// dialogView.findViewById(R.id.btn_forgot_submit);
				// _loginButton.setTypeface(_customFontR);
				//

				builder.setPositiveButton("Submit",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// positive button logic

							}
						});

				final AlertDialog dialog = builder.create();
				// display dialog
				dialog.show();

				dialog.getButton(AlertDialog.BUTTON_POSITIVE)
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								// Do stuff, possibly set wantToCloseDialog to
								// true
								String forgotemail = _forgotemail.getText()
										.toString();
								if (forgotemail.isEmpty()
										|| !android.util.Patterns.EMAIL_ADDRESS
												.matcher(forgotemail).matches()) {
									_forgotemail
											.setError(StringConst.VALID_EMAIL);
									return;
								} else {
									_forgotemail.setError(null);
								}

								Toast.makeText(
										getApplicationContext(),
										"Successfully password updation link sent to your mail...",
										Toast.LENGTH_SHORT).show();

								dialog.dismiss();
							}
						});
			}
		});

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.finish();
		// closing transition animations
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	public void onLoginSuccess() {
		_loginButton.setEnabled(true);
		_pDialog.cancel();
		finish();
		// closing transition animations
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	public void onLoginFailed() {
		Toast.makeText(getBaseContext(), StringConst.ERROR_SIGN_IN,
				Toast.LENGTH_LONG).show();
		_loginButton.setEnabled(true);
	}

	public boolean validate() {
		boolean valid = true;
		email = emailText.getText().toString();
		password = passwordText.getText().toString();

		if (email.isEmpty()
				|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
						.matches()) {
			emailText.setError(StringConst.VALID_EMAIL);
			valid = false;
		} else {
			emailText.setError(null);

			if (password.isEmpty() || password.length() < 4
					|| password.length() > 10) {
				passwordText.setError(StringConst.VALID_PASSWORD);
				valid = false;
			} else {
				passwordText.setError(null);
			}
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
				_pDialog.cancel();
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
					_shpref = getSharedPreferences(StringConst.My_PREFERENCES,
							Context.MODE_PRIVATE);

					SharedPreferences.Editor editor = _shpref.edit();

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
					_pDialog.cancel();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
