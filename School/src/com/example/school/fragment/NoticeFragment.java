package com.example.school.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.school.MainActivity;
import com.example.school.R;
import com.example.school.Handler.UserDataHandler;
import com.example.school.adapter.MySpinnerAdapter;
import com.example.school.adapter.NoticeAdapter;
import com.example.school.connection.AsyncInterface;
import com.example.school.model.NoticeData;
import com.example.school.model.StateData;
import com.example.school.model.TestData;
import com.example.school.utility.ConstantUtility;
import com.example.school.utility.StringConst;
import com.example.school.webservice.WebService;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.style.AlignmentSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NoticeFragment extends Fragment {

	private Typeface _customFontR, _customFontB;
	private ArrayList<NoticeData> arrayOfNotice;
	private SharedPreferences shpref;
	private ListView listView;
	public NoticeFragment() {
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
		ArrayList<NameValuePair> valuePairs = handler.getNoticeData(String.valueOf(schoolId));

		WebService service = new WebService(getActivity(),
				StringConst.SCHOOL_NOTICE, valuePairs);
		service.mListener = interfaceListener;
		service.execute();
		
		View rootView = inflater.inflate(R.layout.fragment_notice, container,
				false);

		listView = (ListView) rootView
				.findViewById(R.id.notice_list_view);
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
			} else if (flag.equals(StringConst.SCHOOL_NOTICE)) {
				String finalResult = (String) result;
				JSONObject json_data;
				try {
					json_data = new JSONObject(finalResult);
					String code = (json_data
							.getString(StringConst.RESPONSE_CODE));
					if (Integer.parseInt(code) == 200) {
						
						JSONArray notice_array = json_data.getJSONArray(StringConst.JSON_DATA);

						arrayOfNotice = new ArrayList<NoticeData>();

						
						for (int i = 0; i < notice_array.length(); i++) {
							JSONObject notice_data = notice_array.getJSONObject(i);
							
							String noticeSub = notice_data.getString(StringConst.NOTICE_SUB);
							String noticeDate = notice_data.getString(StringConst.NOTICE_DATE);
							String noticeDesc = notice_data.getString(StringConst.NOTICE);

							arrayOfNotice.add(new NoticeData(
									NoticeData.ANNOUNCEMENT_TYPE.NEWS,
									noticeSub, noticeDesc, noticeDesc,
									noticeDate));
						}
						
						// Create the adapter to convert the array to views
						NoticeAdapter adapter = new NoticeAdapter(getActivity(), arrayOfNotice);
						// Attach the adapter to a ListView

						listView.setAdapter(adapter);
						listView.setOnItemClickListener(mListener);
						// Inflate the layout for this fragment
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
	};
	
	OnItemClickListener mListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					getContext());

			LinearLayout diagLayout = new LinearLayout(getContext());
			diagLayout.setOrientation(LinearLayout.VERTICAL);
			diagLayout.setPadding(10, 10, 10, 10);

			ScrollView scroller = new ScrollView(getContext());
			LinearLayout lLayout = new LinearLayout(getContext());
			lLayout.setOrientation(LinearLayout.VERTICAL);
			lLayout.setGravity(Gravity.CENTER_HORIZONTAL);
			lLayout.setPadding(5, 5, 5, 5);

			// Lookup view for data population
			TextView tvTitle = new TextView(getContext());
			TextView tvSubTitle = new TextView(getContext());
			TextView tvDesc = new TextView(getContext());

			tvTitle.setTypeface(_customFontB);
			tvSubTitle.setTypeface(_customFontR);
			tvDesc.setTypeface(_customFontR);

			tvTitle.setTextColor(getContext().getResources().getColor(
					R.color.colorPrimaryDark));
			tvTitle.setTextSize(22);

			tvSubTitle.setPadding(0, 0, 0, 30);

			// Populate the data into the view using the data object
			tvTitle.setText(arrayOfNotice.get(position).getTitle());
			tvSubTitle.setText(arrayOfNotice.get(position).getSub_title());
			tvDesc.setText(arrayOfNotice.get(position).getDesc());

			tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
			tvSubTitle.setGravity(Gravity.CENTER_HORIZONTAL);
			tvDesc.setGravity(Gravity.CENTER_HORIZONTAL);

			lLayout.addView(tvTitle);
			lLayout.addView(tvSubTitle);
			lLayout.addView(tvDesc);

			scroller.addView(lLayout);

			Button btnCancel = new Button(getContext());
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 5, 5, 5);
			btnCancel.setBackgroundDrawable(getActivity().getResources()
					.getDrawable(R.drawable.shadow_card));
			btnCancel.setText("Cancel");
			btnCancel.setTextColor(Color.WHITE);
			btnCancel.setLayoutParams(params);
			btnCancel.setGravity(Gravity.CENTER_HORIZONTAL);
			btnCancel.setTypeface(_customFontR);
			diagLayout.addView(scroller);
			diagLayout.addView(btnCancel);
			alertDialog.setView(diagLayout);

			// alertDialog.setPositiveButton("Cancel",
			// new DialogInterface.OnClickListener() {
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// // positive button logic
			//
			// }
			// });

			final AlertDialog dialog = alertDialog.create();
			btnCancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});

			// display dialog
			dialog.show();
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