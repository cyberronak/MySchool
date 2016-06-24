package com.example.school;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.example.school.adapter.MySpinnerAdapter;
import com.example.school.utility.ConstantUtility;
import com.example.school.utility.StringConst;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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
	
	private Typeface custom_font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_city_school);

		// load custom fonts
		custom_font = Typeface.createFromAsset(getAssets(), "fonts/American_Typewriter_Regular.ttf");
		
		shpref = getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);

		/*--------------State spinner--------------*/
		spinnerState = (Spinner) findViewById(R.id.spState);
		setInitState();
		spinnerState.setOnItemSelectedListener(this);

		/*--------------City spinner--------------*/
		spinnerCity = (Spinner) findViewById(R.id.spCity);
		setInitCity();
		spinnerCity.setOnItemSelectedListener(this);

		/*--------------School spinner--------------*/
		spinnerSchool = (Spinner) findViewById(R.id.spSchool);
		setInitSchool();

		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setTypeface(custom_font);
		
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (spinnerCity.getSelectedItemPosition() > 0
						&& spinnerSchool.getSelectedItemPosition() > 0) {
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

	private void setInitState() {
		List<String> listState = new ArrayList<String>();
		listState.add("Select State Name");
		listState.add("Chennai");
		listState.add("Delhi");
		listState.add("Gujrat");
		listState.add("Kolkata");
		listState.add("Mumbai");

		MySpinnerAdapter stateAdapter = new MySpinnerAdapter(this,
				R.layout.spinner_layout, listState);
		stateAdapter.setDropDownViewResource(R.layout.spinner_item);
		spinnerState.setAdapter(stateAdapter);
	}

	private void setInitCity() {
		List<String> listCity = new ArrayList<String>();
		listCity.add("Select City Name");
		MySpinnerAdapter cityAdapter = new MySpinnerAdapter(this,
				R.layout.spinner_layout, listCity);
		cityAdapter.setDropDownViewResource(R.layout.spinner_item);
		spinnerCity.setAdapter(cityAdapter);
		cityAdapter.notifyDataSetChanged();
	}

	private void setInitSchool() {
		List<String> list = new ArrayList<String>();
		list.add("Select School Name");
		MySpinnerAdapter dataAdapter = new MySpinnerAdapter(this,
				R.layout.spinner_layout, list);
		dataAdapter.setDropDownViewResource(R.layout.spinner_item);
		dataAdapter.notifyDataSetChanged();
		spinnerSchool.setAdapter(dataAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (parent.getId()) {
		case R.id.spState:
			MySpinnerAdapter cityAdapter = null;
			String sp1 = String.valueOf(spinnerState.getSelectedItem());
			if (sp1.contentEquals("Gujrat")) {
				List<String> listCity = new ArrayList<String>();
				listCity.add("Select City Name");
				listCity.add("Ahemdabad");
				listCity.add("Surat");
				listCity.add("Rajkot");
				listCity.add("Vadodara");
				cityAdapter = new MySpinnerAdapter(this,
						R.layout.spinner_layout, listCity);
				cityAdapter.setDropDownViewResource(R.layout.spinner_item);
				spinnerCity.setAdapter(cityAdapter);
				cityAdapter.notifyDataSetChanged();
			} else if (sp1.contentEquals("Select State Name")) {
				setInitCity();
				setInitSchool();
			} else
			{
				Toast.makeText(getApplicationContext(),
						StringConst.NO_RESOURCE_FOUND, Toast.LENGTH_SHORT)
						.show();
				setInitCity();
				setInitSchool();
			}
			break;
		case R.id.spCity:
			MySpinnerAdapter dataAdapter = null;
			String sp2 = String.valueOf(spinnerCity.getSelectedItem());
			if (sp2.contentEquals("Ahemdabad")) {
				List<String> list = new ArrayList<String>();
				list.add("Select School Name");
				list.add("Ahmedabad International school");
				list.add("Amrit  Jyoti High School");
				list.add("St.Kabir School");
				list.add("St.Xaviers School");
				list.add("Hebron School");
				dataAdapter = new MySpinnerAdapter(this,
						R.layout.spinner_layout, list);
				dataAdapter.setDropDownViewResource(R.layout.spinner_item);
				spinnerSchool.setAdapter(dataAdapter);
				dataAdapter.notifyDataSetChanged();
			} else if (sp2.contentEquals("Surat")) {
				List<String> list = new ArrayList<String>();
				list.add("Select School Name");
				list.add("Atmiya Vidya Mandir");
				list.add("Delhi Public School");
				list.add("Maharaja Agrasen Vidyalaya");
				list.add("Nirman Vidhya Niketan School");
				list.add("Vidya Bharti Hindi Vidyalaya");
				dataAdapter = new MySpinnerAdapter(this,
						R.layout.spinner_layout, list);
				dataAdapter.setDropDownViewResource(R.layout.spinner_item);
				spinnerSchool.setAdapter(dataAdapter);
				dataAdapter.notifyDataSetChanged();
			} else if (sp2.contentEquals("Vadodara")) {
				List<String> list = new ArrayList<String>();
				list.add("Select School Name");
				list.add("Reliance School");
				list.add("Motnath Vidyalaya");
				list.add("St.Thomas School");
				list.add("Roosevelt High School");
				list.add("Shree Narayan Vidyalaya");
				dataAdapter = new MySpinnerAdapter(this,
						R.layout.spinner_layout, list);
				dataAdapter.setDropDownViewResource(R.layout.spinner_item);
				spinnerSchool.setAdapter(dataAdapter);
				dataAdapter.notifyDataSetChanged();
			} else if (sp2.contentEquals("Rajkot")) {
				List<String> list = new ArrayList<String>();
				list.add("Select School Name");
				list.add("Modi School");
				list.add("Dhaval Straed World School");
				list.add("Jawahar Navodaya Vidyalaya");
				list.add("Shree Auro English School");
				list.add("Sunflower English School");
				dataAdapter = new MySpinnerAdapter(this,
						R.layout.spinner_layout, list);
				dataAdapter.setDropDownViewResource(R.layout.spinner_item);
				spinnerSchool.setAdapter(dataAdapter);
				dataAdapter.notifyDataSetChanged();
			}
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
