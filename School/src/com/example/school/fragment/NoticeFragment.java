package com.example.school.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.school.R;
import com.example.school.adapter.NoticeAdapter;
import com.example.school.model.NoticeData;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class NoticeFragment extends Fragment {

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
		View rootView = inflater.inflate(R.layout.fragment_notice,
				container, false);
		ArrayList<NoticeData> arrayOfUsers = new ArrayList<NoticeData>();
		arrayOfUsers.add(new NoticeData(
				NoticeData.ANNOUNCEMENT_TYPE.NEWS,
				"Admission cut-off list",
				"Admission 2016 cut-off list available",
				"Admission 2016 cut-off list available on  noticeboard",
				"2016-06-12 12:30:00"));
		arrayOfUsers.add(new NoticeData(
				NoticeData.ANNOUNCEMENT_TYPE.NEWS,
				"Notice for school fees",
				"School fees submission 2016",
				"School fees submission 2016 scheduled on 1st July 2016.",
				"2016-06-19 11:30:00"));
		arrayOfUsers.add(new NoticeData(
				NoticeData.ANNOUNCEMENT_TYPE.EVENTS,
				"Cricket tournament 2016",
				"Cricket tournament 2016 fourm filling",
				"Cricket tournament 2016 fourm filling started on 10:30 Am at student section",
				"2016-06-22 10:30:00"));
		arrayOfUsers.add(new NoticeData(
				NoticeData.ANNOUNCEMENT_TYPE.EXAMS,
				"Mid-Exam-2016",
				"Mid-Exam-2016 scheduled availble on notice board",
				"Mid-Exam-2016 scheduled started from 2nd week on July 2016",
				"2016-06-20 11:30:00"));
		// Create the adapter to convert the array to views
		NoticeAdapter adapter = new NoticeAdapter(getActivity(),
				arrayOfUsers);
		// Attach the adapter to a ListView
		ListView listView = (ListView) rootView
				.findViewById(R.id.announcement_list_view);
		listView.setAdapter(adapter);
		// Inflate the layout for this fragment
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}