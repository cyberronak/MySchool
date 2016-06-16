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
	private Spinner spinnerCity, spinnerSchool;
	private Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_city_school);
		
		shpref = getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);
		
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
		}
		
		spinnerCity = (Spinner) findViewById(R.id.spCity);
		spinnerSchool = (Spinner) findViewById(R.id.spSchool);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(spinnerCity.getSelectedItem().toString().trim().length() > 0 && spinnerSchool.getSelectedItem().toString().trim().length() > 0 )
				{					
					SharedPreferences.Editor editor = shpref.edit();

					editor.putString(StringConst.MY_CITY,
							spinnerCity.getSelectedItem().toString());
					editor.putString(StringConst.MY_SCHOOL,
							spinnerSchool.getSelectedItem().toString());
					
					editor.commit();
					
					Intent intent = new Intent(getApplicationContext(),
							LoginActivity.class);
					startActivity(intent);
					finish();
					// closing transition animations
					overridePendingTransition(R.anim.activity_open_transition,
							R.anim.activity_close_translate);
				}
				else
					Toast.makeText(getApplicationContext(), StringConst.SPINNER_ERRO, Toast.LENGTH_SHORT).show();
			}
		});

		spinnerCity.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String sp1 = String.valueOf(spinnerCity.getSelectedItem());
		Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
		if (sp1.contentEquals("Income")) {
			List<String> list = new ArrayList<String>();
			list.add("Salary");
			list.add("Sales");
			list.add("Others");
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, list);
			dataAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			dataAdapter.notifyDataSetChanged();
			spinnerSchool.setAdapter(dataAdapter);
		}
		if (sp1.contentEquals("Expense")) {
			List<String> list = new ArrayList<String>();
			list.add("Conveyance");
			list.add("Breakfast");
			list.add("Purchase");
			ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, list);
			dataAdapter2
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			dataAdapter2.notifyDataSetChanged();
			spinnerSchool.setAdapter(dataAdapter2);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
