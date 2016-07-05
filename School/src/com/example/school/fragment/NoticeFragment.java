package com.example.school.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.school.R;
import com.example.school.adapter.NoticeAdapter;
import com.example.school.model.NoticeData;
import com.example.school.model.TestData;
import com.example.school.utility.ConstantUtility;

import android.app.Activity;
import android.content.DialogInterface;
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
	private ArrayList<NoticeData> arrayOfUsers;

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

		View rootView = inflater.inflate(R.layout.fragment_notice, container,
				false);
		arrayOfUsers = new ArrayList<NoticeData>();
		arrayOfUsers.add(new NoticeData(NoticeData.ANNOUNCEMENT_TYPE.NEWS,
				"Admission cut-off list",
				"Admission 2016 cut-off list available",
				"Admission 2016 cut-off list available on  noticeboard",
				"2016-06-12 12:30:00"));
		arrayOfUsers.add(new NoticeData(NoticeData.ANNOUNCEMENT_TYPE.NEWS,
				"Notice for school fees", "School fees submission 2016",
				"School fees submission 2016 scheduled on 1st July 2016.",
				"2016-06-19 11:30:00"));
		arrayOfUsers
				.add(new NoticeData(
						NoticeData.ANNOUNCEMENT_TYPE.EVENTS,
						"Cricket tournament 2016",
						"Cricket tournament 2016 fourm filling",
						"Cricket tournament 2016 fourm filling started on 10:30 Am at student section",
						"2016-06-22 10:30:00"));
		arrayOfUsers.add(new NoticeData(NoticeData.ANNOUNCEMENT_TYPE.EXAMS,
				"Mid-Exam-2016",
				"Mid-Exam-2016 scheduled availble on notice board",
				"Mid-Exam-2016 scheduled started from 2nd week on July 2016",
				"2016-06-20 11:30:00"));
		// Create the adapter to convert the array to views
		NoticeAdapter adapter = new NoticeAdapter(getActivity(), arrayOfUsers);
		// Attach the adapter to a ListView
		ListView listView = (ListView) rootView
				.findViewById(R.id.notice_list_view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(mListener);
		// Inflate the layout for this fragment
		return rootView;
	}

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
			tvTitle.setText(arrayOfUsers.get(position).getTitle());
			tvSubTitle.setText(arrayOfUsers.get(position).getSub_title());
			tvDesc.setText(arrayOfUsers.get(position).getDesc());

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
			btnCancel.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.shadow_card));
			btnCancel.setText("Cancel");
			btnCancel.setTextColor(Color.WHITE);
			btnCancel.setLayoutParams(params);
			btnCancel.setGravity(Gravity.CENTER_HORIZONTAL);
			btnCancel.setTypeface(_customFontR);
			diagLayout.addView(scroller);
			diagLayout.addView(btnCancel);			
			alertDialog.setView(diagLayout);
			
//			alertDialog.setPositiveButton("Cancel",
//					new DialogInterface.OnClickListener() {
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							// positive button logic
//
//						}
//					});

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