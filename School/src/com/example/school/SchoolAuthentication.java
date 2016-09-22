package com.example.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSInput;

import com.example.school.Handler.UserDataHandler;
import com.example.school.adapter.MySpinnerAdapter;
import com.example.school.connection.AsyncInterface;
import com.example.school.model.CityData;
import com.example.school.model.SchoolData;
import com.example.school.model.StateData;
import com.example.school.utility.ConstantUtility;
import com.example.school.utility.StringConst;
import com.example.school.webservice.WebService;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SchoolAuthentication extends AppCompatActivity implements
		OnItemSelectedListener,AsyncInterface {
	private SharedPreferences _shpref;
	private Spinner _spinnerState, _spinnerCity, _spinnerSchool;
	private Button _btnSubmit;
	private TextView _title;
	private Typeface _customFontR, _customFontB;

	private ProgressDialog _pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_city_school);

		// load custom fonts
		_customFontR = Typeface.createFromAsset(getAssets(),
				"fonts/American_Typewriter_Regular.ttf");
		_customFontB = Typeface.createFromAsset(getAssets(),
				"fonts/American_Typewriter_Bold.ttf");
		
		_shpref = getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);

		_title=(TextView) findViewById(R.id.tv_auth_title);
		_title.setTypeface(_customFontB);
		/*--------------State spinner--------------*/
		_spinnerState = (Spinner) findViewById(R.id.spState);
		setInitState();
		_spinnerState.setOnItemSelectedListener(this);

		/*--------------City spinner--------------*/
		_spinnerCity = (Spinner) findViewById(R.id.spCity);
		setInitCity();
		_spinnerCity.setOnItemSelectedListener(this);

		/*--------------School spinner--------------*/
		_spinnerSchool = (Spinner) findViewById(R.id.spSchool);
		setInitSchool();

		_btnSubmit = (Button) findViewById(R.id.btnSubmit);
		_btnSubmit.setTypeface(_customFontR);
		
		_btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String spinerCity = _spinnerCity.getSelectedItem().toString();
				String spinerSchool = _spinnerSchool.getSelectedItem().toString();
				if (ConstantUtility.validateSpinner(spinerCity)
						&& ConstantUtility.validateSpinner(spinerSchool)) {
					SchoolData school = getSchoolIdByName(spinerSchool);
					SharedPreferences.Editor editor = _shpref.edit();
					
					editor.putString(StringConst.MY_STATE, _spinnerState
							.getSelectedItem().toString());
					editor.putString(StringConst.MY_CITY, spinerCity);
					editor.putString(StringConst.MY_SCHOOL, spinerSchool);
					editor.putInt(StringConst.MY_SCHOOL_ID, school.getSchool_id());
					
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
	}

	private void setInitState() {

		_pDialog = new ProgressDialog(this);
		_pDialog.setMessage(StringConst.LOADING_MSG);
		_pDialog.setIndeterminate(false);
		_pDialog.setCancelable(false);
		_pDialog.show();
		
		ArrayList<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		
		WebService service = new WebService(getApplicationContext(),
				StringConst.STATE_ITEM, valuePairs);
		service.mListener = SchoolAuthentication.this;
		service.execute();
	}

	private void setInitCity() {
		List<String> listCity = new ArrayList<String>();
		listCity.add("Select City Name");
		MySpinnerAdapter cityAdapter = new MySpinnerAdapter(this,
				R.layout.spinner_layout, listCity);
		cityAdapter.setDropDownViewResource(R.layout.spinner_item);
		_spinnerCity.setAdapter(null);
		cityAdapter.notifyDataSetChanged();
		_spinnerCity.setAdapter(cityAdapter);
	}

	private void setInitSchool() {
		List<String> listSchool = new ArrayList<String>();
		listSchool.add("Select School Name");
		MySpinnerAdapter schoolAdapter = new MySpinnerAdapter(this,
				R.layout.spinner_layout, listSchool);
		schoolAdapter.setDropDownViewResource(R.layout.spinner_item);
		_spinnerSchool.setAdapter(null);
		schoolAdapter.notifyDataSetChanged();
		_spinnerSchool.setAdapter(schoolAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (parent.getId()) {
		case R.id.spState:
			String sp1 = String.valueOf(_spinnerState.getSelectedItem());
			if (sp1.contentEquals("Select State Name")) {
				setInitCity();
				setInitSchool();
			}
			else if (!sp1.contentEquals("Select State Name"))	{
				StateData state = getStateIdByName(sp1);
				
				_pDialog = new ProgressDialog(this);
				_pDialog.setMessage(StringConst.LOADING_MSG);
				_pDialog.setIndeterminate(false);
				_pDialog.setCancelable(false);
				_pDialog.show();
				
				ArrayList<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
				valuePairs.add(new BasicNameValuePair(StringConst.CITY_PARM, String.valueOf(state.getState_id())	));
				
				WebService service = new WebService(getApplicationContext(),
						StringConst.CITY_ITEM, valuePairs);
				service.mListener = SchoolAuthentication.this;
				service.execute();				
			}
			else
			{
//				Toast.makeText(getApplicationContext(),
//						StringConst.NO_RESOURCE_FOUND, Toast.LENGTH_SHORT)
//						.show();
				setInitCity();
				setInitSchool();
			}
			break;
		case R.id.spCity:
			String sp2 = String.valueOf(_spinnerCity.getSelectedItem());
			if (sp2.contentEquals("Select State Name")) {
				setInitSchool();
			}
			else if (!sp2.contentEquals("Select State Name") && sp2.length() > 0)	{
				CityData city = getCityIdByName(sp2);
				
				if(city != null)
				{
					_pDialog = new ProgressDialog(this);
					_pDialog.setMessage(StringConst.LOADING_MSG);
					_pDialog.setIndeterminate(false);
					_pDialog.setCancelable(false);
					_pDialog.show();
					
					ArrayList<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
					valuePairs.add(new BasicNameValuePair(StringConst.SHCOOL_PARM, String.valueOf(city.getCity_id())));
					
					WebService service = new WebService(getApplicationContext(),
							StringConst.SCHOOL_ITEM, valuePairs);
					service.mListener = SchoolAuthentication.this;
					service.execute();					
				}
				else
				{
//					Toast.makeText(getApplicationContext(),
//							StringConst.NO_RESOURCE_FOUND, Toast.LENGTH_SHORT)
//							.show();
					setInitSchool();
				}
			}
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asyncResult(Object result, String flag) {
		// TODO Auto-generated method stub
		if (flag.equals(StringConst.CONNECTION)) {
			if ((Boolean) result == true) {

			} else {
				Toast.makeText(this, StringConst.ERROR_ON_NETWORK,
						Toast.LENGTH_LONG).show();
				_pDialog.cancel();
			}
		} else if (flag.equals(StringConst.STATE_ITEM)) {
			String finalResult = (String) result;
			JSONObject json_data;
			try {
				json_data = new JSONObject(finalResult);
				String code = (json_data
						.getString(StringConst.RESPONSE_CODE));
				if (Integer.parseInt(code) == 200) {
					
					JSONArray state_array = json_data.getJSONArray(StringConst.JSON_DATA);

					StateData.stateList.clear();
					
					List<String> listState = new ArrayList<String>();
					for (int i = 0; i < state_array.length(); i++) {
						JSONObject state_data = state_array.getJSONObject(i);
						
						int sid = Integer.parseInt(state_data.getString(StringConst.S_ID));
						String sname = state_data.getString(StringConst.S_NAME);
						StateData state = new StateData();
						state.setState_id(sid);
						state.setState_name(sname);						
						
						StateData.stateList.add(state);
						listState.add(sname);
					}
					
					Collections.sort(listState);
					listState.add(0, "Select State Name");

					MySpinnerAdapter stateAdapter = new MySpinnerAdapter(this,
							R.layout.spinner_layout, listState);
					stateAdapter.setDropDownViewResource(R.layout.spinner_item);
					_spinnerState.setAdapter(stateAdapter);
					_pDialog.cancel();
				} else {
					Toast.makeText(this, StringConst.NO_RESOURCE_FOUND,
							Toast.LENGTH_SHORT).show();
					setInitCity();
					setInitSchool();
					_pDialog.cancel();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else if (flag.equals(StringConst.CITY_ITEM)) {

			MySpinnerAdapter cityAdapter = null;
			String finalResult = (String) result;
			JSONObject json_data;
			try {
				json_data = new JSONObject(finalResult);
				String code = (json_data
						.getString(StringConst.RESPONSE_CODE));
				if (Integer.parseInt(code) == 200) {
					
					JSONArray city_array = json_data.getJSONArray(StringConst.JSON_DATA);
					
					List<String> listCity = new ArrayList<String>();
					for (int i = 0; i < city_array.length(); i++) {
						JSONObject city_data = city_array.getJSONObject(i);
						
						int cid = Integer.parseInt(city_data.getString(StringConst.C_ID));
						String cname = city_data.getString(StringConst.C_NAME);
						CityData city = new CityData();
						city.setCity_id(cid);
						city.setCity_name(cname);
						
						CityData.cityList.add(city);
						listCity.add(cname);
					}
					
					Collections.sort(listCity);
					cityAdapter = new MySpinnerAdapter(this,
							R.layout.spinner_layout, listCity);
					cityAdapter.setDropDownViewResource(R.layout.spinner_item);
					_spinnerCity.setAdapter(cityAdapter);
					cityAdapter.notifyDataSetChanged();
					
					_pDialog.cancel();
				} else {
					Toast.makeText(this, StringConst.NO_RESOURCE_FOUND,
							Toast.LENGTH_SHORT).show();
					setInitCity();
					setInitSchool();
					_pDialog.cancel();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else if (flag.equals(StringConst.SCHOOL_ITEM)) {
			String finalResult = (String) result;
			JSONObject json_data;
			try {
				json_data = new JSONObject(finalResult);
				String code = (json_data
						.getString(StringConst.RESPONSE_CODE));
				if (Integer.parseInt(code) == 200) {
					
					JSONArray school_array = json_data.getJSONArray(StringConst.JSON_DATA);
					
					List<String> listSchool = new ArrayList<String>();
					for (int i = 0; i < school_array.length(); i++) {
						JSONObject school_data = school_array.getJSONObject(i);
						
						int schoolid = Integer.parseInt(school_data.getString(StringConst.SCHOOL_ID));
						String schoolname = school_data.getString(StringConst.SCHOOL_NAME);
						SchoolData school = new SchoolData();
						school.setSchool_id(schoolid);
						school.setSchool_name(schoolname);						
						
						SchoolData.schoolList.add(school);
						listSchool.add(schoolname);
					}

					Collections.sort(listSchool);
					MySpinnerAdapter schoolAdapter = null;
					schoolAdapter = new MySpinnerAdapter(this,
							R.layout.spinner_layout, listSchool);
					schoolAdapter.setDropDownViewResource(R.layout.spinner_item);
					_spinnerSchool.setAdapter(schoolAdapter);
					schoolAdapter.notifyDataSetChanged();
					
					_pDialog.cancel();
				} else {
//					Toast.makeText(this, StringConst.NO_RESOURCE_FOUND,
//							Toast.LENGTH_SHORT).show();
					_pDialog.cancel();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
//	public StateData containsName(final List<StateData> list, final String name){
//	    return list.stream().map(StateData::getName).filter(name::equals).findFirst().isPresent();
//	}
	
	private StateData getStateIdByName(String statename)
	{
		StateData state= null;		
		for(StateData listData:StateData.stateList)
		{
			if(listData.getState_name().equals(statename))
				return listData;
		}
		return state; 
	}

	private CityData getCityIdByName(String cityname)
	{
		CityData state= null;	
		for(CityData listData:CityData.cityList)
		{
			if(listData.getCity_name().equals(cityname))
				return listData;
		}
		return state; 
	}
	
	private SchoolData getSchoolIdByName(String schoolname)
	{
		SchoolData state= null;	
		for(SchoolData listData:SchoolData.schoolList)
		{
			if(listData.getSchool_name().equals(schoolname))
				return listData;
		}
		return state; 
	}
}
