package com.example.school.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.school.R;
import com.example.school.RankActivity;
import com.example.school.adapter.MySpinnerAdapter;
import com.example.school.model.TestData;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LeaderboardFragment extends Fragment {

	private Spinner _spinnerTestList;
	private Button _btnRank, _btnSubject;
	private Typeface _customFontR, _customFontB;

	private String[] subjectList = { "Maths", "Science", "Social-Science",
			"Gujrati", "Hindi", "English" };

	public LeaderboardFragment() {
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

		View rootView = inflater.inflate(R.layout.fragment_leaderboard,
				container, false);

		/*--------------State spinner--------------*/
		_spinnerTestList = (Spinner) rootView.findViewById(R.id.sp_test_list);
		setInitTestList();

		_btnRank = (Button) rootView.findViewById(R.id.btn_rank);
		_btnRank.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (_spinnerTestList.getSelectedItemPosition() != 0) {
					Intent intent = new Intent(getActivity(),
							RankActivity.class);
					intent.putExtra("RankActivityTitle", "Rank");
					startActivity(intent);
					// closing transition animations
					getActivity().overridePendingTransition(
							R.anim.slide_in_left, R.anim.slide_out_right);
				} else
					Toast.makeText(getContext(),
							"First please select dropdown value",
							Toast.LENGTH_SHORT).show();
			}
		});

		_btnSubject = (Button) rootView.findViewById(R.id.btn_subject);
		_btnSubject.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				TextView tvTitle = new TextView(getContext());

				tvTitle.setBackgroundColor(getActivity().getResources()
						.getColor(R.color.colorPrimaryDark));
				tvTitle.setText(R.string.title_subject);
				tvTitle.setTextSize(22);
				tvTitle.setTextColor(Color.WHITE);
				tvTitle.setGravity(Gravity.CENTER);
				tvTitle.setTypeface(_customFontB);
				tvTitle.setPadding(0, 20, 0, 20);

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						getContext());
				alertDialog.setCustomTitle(tvTitle);
				List<String> listData = Arrays.asList(subjectList);

				MySpinnerAdapter arrayAdapter = new MySpinnerAdapter(
						getActivity(), R.layout.subject_dialog_row, listData);

				alertDialog.setAdapter(arrayAdapter, alertDialogListener);
				// LinearLayout diagLayout = new LinearLayout(getContext());
				// diagLayout.setOrientation(LinearLayout.VERTICAL);
				//
				// TextView tvTitle = new TextView(getContext());
				//
				// tvTitle.setBackgroundColor(getActivity().getResources()
				// .getColor(R.color.colorPrimaryDark));
				// tvTitle.setText(R.string.title_subject);
				// tvTitle.setTextSize(22);
				// tvTitle.setTextColor(Color.WHITE);
				// tvTitle.setGravity(Gravity.CENTER);
				// tvTitle.setTypeface(_customFontB);
				// tvTitle.setPadding(0, 10, 0, 10);
				//
				// ScrollView scroller = new ScrollView(getContext());
				//
				// LinearLayout lLayout = new LinearLayout(getContext());
				// lLayout.setOrientation(LinearLayout.VERTICAL);
				// lLayout.setGravity(Gravity.CENTER);
				// lLayout.setPadding(5, 5, 5, 5);
				//
				// int size = subjectList.length;
				// for (int i = 0; i < size; i++) {
				// // Lookup view for data population
				// TextView tvListItem = new TextView(getContext());
				//
				// // Populate the data into the view using the data object
				// tvListItem.setId(i);
				// tvListItem.setText(subjectList[i]);
				// tvListItem.setGravity(Gravity.CENTER_HORIZONTAL);
				// tvListItem.setTypeface(_customFontR);
				// tvListItem.setTextColor(getContext().getResources()
				// .getColor(R.color.colorPrimaryDark));
				// tvListItem.setTextSize(22);
				// tvListItem.setOnClickListener(mListener);
				// tvListItem.setPadding(0, 10, 0, 10);
				//
				// View view = new View(getContext());
				// view.setLayoutParams(new LinearLayout.LayoutParams(
				// LayoutParams.MATCH_PARENT, 1));
				// view.setBackgroundColor(Color.GRAY);
				//
				// lLayout.addView(tvListItem);
				// if (i != (size - 1))
				// lLayout.addView(view);
				// }
				//
				// scroller.addView(lLayout);
				//
				// diagLayout.addView(tvTitle);
				// diagLayout.addView(scroller);
				// alertDialog.setView(diagLayout);

				// alertDialog.setPositiveButton("Cancel",
				// new DialogInterface.OnClickListener() {
				// @Override
				// public void onClick(DialogInterface dialog, int which) {
				// // positive button logic
				//
				// }
				// });

				final AlertDialog dialog = alertDialog.create();
				dialog.setCancelable(true);
				// display dialog
				dialog.show();
			}
		});

		// Inflate the layout for this fragment
		return rootView;
	}

	// Button Rank and Subject click
	OnClickListener mListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case 0:
				Toast.makeText(getContext(), subjectList[0], Toast.LENGTH_SHORT)
						.show();
				break;
			case 1:
				Toast.makeText(getContext(), subjectList[1], Toast.LENGTH_SHORT)
						.show();
				break;
			case 2:
				Toast.makeText(getContext(), subjectList[2], Toast.LENGTH_SHORT)
						.show();
				break;
			case 3:
				Toast.makeText(getContext(), subjectList[3], Toast.LENGTH_SHORT)
						.show();
				break;
			case 4:
				Toast.makeText(getContext(), subjectList[4], Toast.LENGTH_SHORT)
						.show();
				break;
			case 5:
				Toast.makeText(getContext(), subjectList[5], Toast.LENGTH_SHORT)
						.show();
				break;

			default:
				break;
			}
		}
	};

	DialogInterface.OnClickListener alertDialogListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), RankActivity.class);
			intent.putExtra("RankActivityTitle", subjectList[which]);
			startActivity(intent);
			// closing transition animations
			getActivity().overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);

			// Toast.makeText(getContext(), subjectList[which],
			// Toast.LENGTH_SHORT).show();
		}
	};

	private void setInitTestList() {
		// TODO Auto-generated method stub
		List<String> testList = new ArrayList<String>();
		testList.add("Select Test Data");
		testList.add("Test-1");
		testList.add("Test-2");
		testList.add("Test-3");
		testList.add("Test-4");

		MySpinnerAdapter stateAdapter = new MySpinnerAdapter(getContext(),
				R.layout.spinner_layout, testList);
		stateAdapter.setDropDownViewResource(R.layout.spinner_item);
		_spinnerTestList.setAdapter(stateAdapter);
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