package com.example.school.fragment;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.school.R;
import com.example.school.Handler.UserDataHandler;
import com.example.school.adapter.NoticeAdapter;
import com.example.school.connection.AsyncInterface;
import com.example.school.model.NoticeData;
import com.example.school.utility.StringConst;
import com.example.school.webservice.WebService;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AboutFragment extends Fragment {

	TextView lblAddress, lblEmail, lblContact, lblDesc;
	TextView txtAddress, txtEmail, txtContact, txtDesc;
	private Typeface _customFontR, _customFontB;

	private SharedPreferences shpref;

	public AboutFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// load custom fonts
		_customFontR = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/American_Typewriter_Regular.ttf");
		_customFontB = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/American_Typewriter_Bold.ttf");

		shpref = getActivity().getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);
		
		int schoolId = shpref.getInt(StringConst.MY_SCHOOL_ID, 0);
		
		UserDataHandler handler = new UserDataHandler();
		ArrayList<NameValuePair> valuePairs = handler.getSchoolInfo(String.valueOf(schoolId));

		WebService service = new WebService(getActivity(),
				StringConst.ABOUT_SCHOOL_ITEM, valuePairs);
		service.mListener = interfaceListener;
		service.execute();
		
		View rootView = inflater.inflate(R.layout.fragment_about, container,
				false);
		
		lblAddress =(TextView)rootView.findViewById(R.id.lbl_address);
		lblAddress.setTypeface(_customFontB);
		lblEmail =(TextView)rootView.findViewById(R.id.lbl_email);
		lblEmail.setTypeface(_customFontB);
		lblContact =(TextView)rootView.findViewById(R.id.lbl_contact);
		lblContact.setTypeface(_customFontB);
		lblDesc =(TextView)rootView.findViewById(R.id.lbl_desc);
		lblDesc.setTypeface(_customFontB);

		txtAddress =(TextView)rootView.findViewById(R.id.txt_address);
		txtAddress.setTypeface(_customFontR);
		txtEmail =(TextView)rootView.findViewById(R.id.txt_email);
		txtEmail.setTypeface(_customFontR);
		txtContact =(TextView)rootView.findViewById(R.id.txt_contact);
		txtContact.setTypeface(_customFontR);
		txtDesc =(TextView)rootView.findViewById(R.id.txt_desc);
		txtDesc.setTypeface(_customFontR);

		// Inflate the layout for this fragment
		return rootView;
	}

	AsyncInterface interfaceListener = new AsyncInterface() {
		
		@Override
		public void asyncResult(Object result, String flag) {
			// TODO Auto-generated method stub
			if (flag.equals(StringConst.CONNECTION)) {
				if ((Boolean) result == true) {

				} else {
					Toast.makeText(getActivity(), StringConst.ERROR_ON_NETWORK,
							Toast.LENGTH_LONG).show();
				}
			} else if (flag.equals(StringConst.ABOUT_SCHOOL_ITEM)) {
				String finalResult = (String) result;
				JSONObject json_data;
				try {
					json_data = new JSONObject(finalResult);
					String code = (json_data
							.getString(StringConst.RESPONSE_CODE));
					if (Integer.parseInt(code) == 200) {
						
						JSONArray sinfo_array = json_data.getJSONArray(StringConst.JSON_DATA);

						JSONObject sinfo_data = sinfo_array.getJSONObject(0);
						
						String sName = sinfo_data.getString(StringConst.SINFO_NAME);
						String sAddress = sinfo_data.getString(StringConst.SINFO_ADDRESS);
						String sCity = sinfo_data.getString(StringConst.SINFO_CITY);
						String sState = sinfo_data.getString(StringConst.SINFO_STATE);
						String sEmail = sinfo_data.getString(StringConst.SINFO_EMIAL);
						String sMobileNo = sinfo_data.getString(StringConst.SINFO_MOB);
						String sLogo = sinfo_data.getString(StringConst.SINFO_LOGO);
						String sInfo = sinfo_data.getString(StringConst.SINFO_INFO);

//						SharedPreferences _shpref = getActivity().getSharedPreferences(StringConst.SINFO_PREFERENCES,
//								Context.MODE_PRIVATE);
//
//						SharedPreferences.Editor editor = _shpref.edit();
//
//						editor.putString(StringConst.SINFO_NAME,sName);
//						editor.putString(StringConst.SINFO_ADDRESS,sAddress);
//						editor.putString(StringConst.SINFO_CITY,sCity);
//						editor.putString(StringConst.SINFO_STATE,sState);
//						editor.putString(StringConst.SINFO_EMIAL,sEmail);
//						editor.putString(StringConst.SINFO_MOB,sMobileNo);
//						editor.putString(StringConst.SINFO_LOGO,sLogo);
//						editor.putString(StringConst.SINFO_INFO,sInfo);
//						editor.commit();
						
						String schoolAddress = sName +", "+ sAddress +", "+ sCity +", "+ sState;
						txtAddress.setText(schoolAddress);
						txtEmail.setText(sEmail);
						txtContact.setText(sMobileNo);
						txtDesc.setText(sInfo);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}