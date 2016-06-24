package com.example.school;

import java.util.ArrayList;
import java.util.List;

import com.example.school.utility.ConstantUtility;
import com.example.school.utility.StringConst;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SchoolAuthentication extends AppCompatActivity implements
		OnItemSelectedListener {
	private SharedPreferences shpref;
	private Spinner spinnerState, spinnerCity, spinnerSchool;
	private Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_city_school);

		shpref = getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);

		spinnerState = (Spinner) findViewById(R.id.spState);
		ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter
				.createFromResource(getApplicationContext(),
						R.array.state_array, R.layout.spinner_layout);
		stateAdapter.setDropDownViewResource(R.layout.spinner_item);
		spinnerState.setAdapter(stateAdapter);

		spinnerCity = (Spinner) findViewById(R.id.spCity);
		ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter
				.createFromResource(getApplicationContext(),
						R.array.city_array, R.layout.spinner_layout);
		cityAdapter.setDropDownViewResource(R.layout.spinner_item);
		spinnerCity.setAdapter(cityAdapter);

		spinnerSchool = (Spinner) findViewById(R.id.spSchool);
		List<String> list = new ArrayList<String>();
		list.add("Select School Name");
		ArrayAdapter dataAdapter = new ArrayAdapter<String>(this,
				R.layout.spinner_layout, list);
		dataAdapter.setDropDownViewResource(R.layout.spinner_item);
		dataAdapter.notifyDataSetChanged();
		spinnerSchool.setAdapter(dataAdapter);
		
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (spinnerCity.getSelectedItem().toString().trim().length() > 0
						&& spinnerSchool.getSelectedItem().toString().trim()
								.length() > 0) {
					SharedPreferences.Editor editor = shpref.edit();

					editor.putString(StringConst.MY_CITY, spinnerCity
							.getSelectedItem().toString());
					editor.putString(StringConst.MY_SCHOOL, spinnerSchool
							.getSelectedItem().toString());

					editor.commit();

					Intent intent = new Intent(getApplicationContext(),
							LoginActivity.class);
					startActivity(intent);
					finish();
					// closing transition animations
					overridePendingTransition(R.anim.slide_in_left,
							R.anim.slide_out_right);
				} else
					Toast.makeText(getApplicationContext(),
							StringConst.SPINNER_ERRO, Toast.LENGTH_SHORT)
							.show();
			}
		});

		spinnerCity.setOnItemSelectedListener(this);

		String userFn = shpref.getString(StringConst.FIRSTNAME, "");
		String userLn = shpref.getString(StringConst.LASTNAME, "");
		String userEmail = shpref.getString(StringConst.EMAIL, "");
		String userUserName = shpref.getString(StringConst.USERNAME, "");
		String userDate = shpref.getString(StringConst.CREATED_AT, "");

		if (ConstantUtility.notEmpty(userFn)
				&& ConstantUtility.notEmpty(userLn)
				&& ConstantUtility.notEmpty(userEmail)
				&& ConstantUtility.notEmpty(userUserName)
				&& ConstantUtility.notEmpty(userDate)) {
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);

		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ArrayAdapter<String> dataAdapter = null;
		String sp1 = String.valueOf(spinnerCity.getSelectedItem());
		if (sp1.contentEquals("Ahmedabad")) {
			List<String> list = new ArrayList<String>();
			list.add("Ahmedabad International school");
			list.add("Amrit  Jyoti High School");
			list.add("St.Kabir School");
			list.add("St.Xaviers School");
			list.add("Hebron School");
			dataAdapter = new ArrayAdapter<String>(this,
					R.layout.spinner_layout, list);
			dataAdapter.setDropDownViewResource(R.layout.spinner_item);
			dataAdapter.notifyDataSetChanged();
			spinnerSchool.setAdapter(dataAdapter);
		} else if (sp1.contentEquals("Surat")) {
			List<String> list = new ArrayList<String>();
			list.add("Atmiya Vidya Mandir");
			list.add("Delhi Public School");
			list.add("Maharaja Agrasen Vidyalaya");
			list.add("Nirman Vidhya Niketan School");
			list.add("Vidya Bharti Hindi Vidyalaya");
			dataAdapter = new ArrayAdapter<String>(this,
					R.layout.spinner_layout, list);
			dataAdapter.setDropDownViewResource(R.layout.spinner_item);
			dataAdapter.notifyDataSetChanged();
			spinnerSchool.setAdapter(dataAdapter);
		} else if (sp1.contentEquals("Vadodara")) {
			List<String> list = new ArrayList<String>();
			list.add("Reliance School");
			list.add("Motnath Vidyalaya");
			list.add("St.Thomas School");
			list.add("Roosevelt High School");
			list.add("Shree Narayan Vidyalaya");
			dataAdapter = new ArrayAdapter<String>(this,
					R.layout.spinner_layout, list);
			dataAdapter.setDropDownViewResource(R.layout.spinner_item);
			dataAdapter.notifyDataSetChanged();
			spinnerSchool.setAdapter(dataAdapter);
		} else if (sp1.contentEquals("Rajkot")) {
			List<String> list = new ArrayList<String>();
			list.add("Modi School");
			list.add("Dhaval Straed World School");
			list.add("Jawahar Navodaya Vidyalaya");
			list.add("Shree Auro English School");
			list.add("Sunflower English School");
			dataAdapter = new ArrayAdapter<String>(this,
					R.layout.spinner_layout, list);
			dataAdapter.setDropDownViewResource(R.layout.spinner_item);
			dataAdapter.notifyDataSetChanged();
			spinnerSchool.setAdapter(dataAdapter);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
