package com.example.school.fragment;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.school.R;
import com.example.school.adapter.TestAdapter;
import com.example.school.adapter.TestAdapter.MyClickListener;
import com.example.school.model.TestData;
import com.example.school.model.TestResult;
import com.example.school.utility.ConstantUtility;

import android.R.color;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TopicFragment extends Fragment {

	public enum TABLE_TYPE {
		TEST, TOPIC
	}

	boolean arrowBool = true;

	private RecyclerView mRecyclerView;
	private TestAdapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private Typeface _customFontR, _customFontB;

	int marks = 0;
	int totalMarks = 0;
	double percentage = 0;
	boolean isExtraRow = true;
	
	public TopicFragment() {
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

		final View rootView = inflater.inflate(R.layout.fragment_test,
				container, false);
		mRecyclerView = (RecyclerView) rootView
				.findViewById(R.id.dashboard_recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);
		mAdapter = new TestAdapter(getActivity(),getDataSet());
		mAdapter.setOnItemClickListener(myItemClickListener);
		mRecyclerView.setAdapter(mAdapter);

		// Inflate the layout for this fragment
		return rootView;
	}

	TestAdapter.MyClickListener myItemClickListener = new TestAdapter.MyClickListener() {

		@Override
		public void onItemClick(int position, View v) {
			// TODO Auto-generated method stub

			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					getContext());

			LinearLayout diagLayout = new LinearLayout(getContext());
			diagLayout.setOrientation(LinearLayout.VERTICAL);

			if (TestData.tblList.get(position).getParent() != null)
				((ViewGroup) TestData.tblList.get(position).getParent())
						.removeView(TestData.tblList.get(position));
			diagLayout.addView(TestData.tblList.get(position));

			alertDialog.setView(diagLayout);
//			alertDialog.setPositiveButton("Ok",
//					new DialogInterface.OnClickListener() {
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							// positive button logic
//
//						}
//					});

			AlertDialog dialog = alertDialog.create();
			dialog.setCancelable(true);
			// display dialog
			dialog.show();
		}
	};

	private ArrayList<TestData> getDataSet() {
		Bitmap imageArrow = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.ic_action_arrow_right);

		ArrayList<TestData> testData = new ArrayList<TestData>();
		ArrayList<TestResult> testResult = new ArrayList<TestResult>();

		// here we have passing 3 args 1->subject, 2-> higest marks in exam and 3-> out of getting marks
		TestResult rs = new TestResult("M1", "89", "69");
		testResult.add(rs);
		rs = new TestResult("M2", "76", "49");
		testResult.add(rs);
		rs = new TestResult("M2", "40", "40");
		testResult.add(rs);
		
		TestData td = new TestData("Maths", "5", testResult, imageArrow);
		TestData.tblList.add(createTopicTableLayout(testResult.size(), 4, td, 60));
		testData.add(td);

		testResult.clear();
		rs = new TestResult("P1", "89", "69");
		testResult.add(rs);
		rs = new TestResult("P2", "76", "49");
		testResult.add(rs);
		rs = new TestResult("P3", "40", "40");
		testResult.add(rs);
		td = new TestData("Physics", "65", testResult, imageArrow);
		TestData.tblList.add(createTopicTableLayout(testResult.size(), 4, td, 75));
		testData.add(td);

		testResult.clear();
		rs = new TestResult("Ch1", "89", "69");
		testResult.add(rs);
		rs = new TestResult("ChP2", "76", "49");
		testResult.add(rs);
		rs = new TestResult("ChP3", "40", "40");
		testResult.add(rs);
		td = new TestData("Chemistry", "65", testResult, imageArrow);
		TestData.tblList.add(createTopicTableLayout(testResult.size(), 4, td, 63));
		testData.add(td);

		testResult.clear();
		rs = new TestResult("E1", "89", "69");
		testResult.add(rs);
		rs = new TestResult("E2", "76", "49");
		testResult.add(rs);
		rs = new TestResult("E3", "40", "40");
		testResult.add(rs);
		td = new TestData("English", "65", testResult, imageArrow);
		TestData.tblList.add(createTopicTableLayout(testResult.size(), 4, td, 84));
		testData.add(td);

		testResult.clear();
		rs = new TestResult("Env1", "89", "69");
		testResult.add(rs);
		rs = new TestResult("Env2", "76", "49");
		testResult.add(rs);
		rs = new TestResult("Env3", "40", "40");
		testResult.add(rs);
		td = new TestData("Environment", "65", testResult, imageArrow);
		TestData.tblList.add(createTopicTableLayout(testResult.size(), 4, td, 77));
		testData.add(td);
		
		return testData;
	}
	
	private void clearTotalMarks()
	{
		totalMarks=0;
		marks=0;		
	}

	/**
	 * Create TableLayout programmatically
	 */
	public TableLayout createTopicTableLayout(int rows, int columns, TestData td, int cutOfMarks) {

		// CREATE TABLE
		TableLayout tableLayout = new TableLayout(getActivity());
		tableLayout.setStretchAllColumns(true);

		// ADD HEADER
		tableLayout.addView(addHeader(td.getTitle(), rows, columns));

		// ADD ROW
		for (int x = 0; x <= td.getResult().size(); x++) {
			TestResult rs;
			if (x > 0)
				rs = td.getResult().get(x - 1);
			else
				rs = td.getResult().get(0);

			// ADD TABLEROW TO TABLELAYOUT
			tableLayout.addView(addTableRow(x, rs.getSubject(),
					rs.getSubjMark(), rs.getSubjTotal(), !isExtraRow, cutOfMarks));
		}

		clearTotalMarks();
		return tableLayout;
	}

	private TableRow addHeader(String title, int rows, int columns) {
		// CREATE TABLE ROW
		TableRow tableRow = new TableRow(getActivity());

		// CREATE PARAM FOR MARGINING
		TableRow.LayoutParams aParams = new TableRow.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		aParams.topMargin = 2;
		aParams.rightMargin = 2;
		aParams.span = columns;

		// CREATE TEXTVIEW
		TextView a = new TextView(getActivity());
		// SET PARAMS
		a.setLayoutParams(aParams);
		// SET GRAVITY
		a.setGravity(Gravity.CENTER);
		// SET BACKGROUND COLOR
		a.setBackgroundColor(getActivity().getResources().getColor(
				R.color.colorPrimaryDark));
		// SET PADDING
		a.setPadding(20, 20, 20, 20);
		// SET TEXTVIEW TEXT, TYPEFACE AND COLOR
		a.setText(title);
		a.setTypeface(_customFontB);
		a.setTextColor(Color.WHITE);
		// ADD TEXTVIEW TO TABLEROW
		tableRow.addView(a);
		return tableRow;
	}

	private TableRow addTableRow(int x, String col1Subj, String col1Marks,
			String col1TotleMarks, boolean isExtraRow, int cutOfMarks) {
		// CREATE TABLE ROW
		TableRow tableRow = new TableRow(getActivity());

		// CREATE PARAM FOR MARGINING
		TableRow.LayoutParams aParams = new TableRow.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		aParams.topMargin = 2;
		aParams.rightMargin = 2;

		TableRow.LayoutParams bParams = new TableRow.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bParams.topMargin = 2;
		bParams.rightMargin = 2;

		TableRow.LayoutParams cParams = new TableRow.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		cParams.topMargin = 2;
		cParams.rightMargin = 2;

		TableRow.LayoutParams dParams = new TableRow.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		dParams.topMargin = 2;
		dParams.rightMargin = 2;
		
		// CREATE TEXTVIEW
		TextView a = new TextView(getActivity());
		TextView b = new TextView(getActivity());
		TextView c = new TextView(getActivity());
		TextView d = new TextView(getActivity());

		// SET PARAMS
		a.setLayoutParams(aParams);
		b.setLayoutParams(bParams);
		c.setLayoutParams(cParams);
		d.setLayoutParams(dParams);

		// SET PADDING
		a.setPadding(20, 20, 20, 20);
		b.setPadding(20, 20, 20, 20);
		c.setPadding(20, 20, 20, 20);
		d.setPadding(20, 20, 20, 20);

		// SET TEXTVIEW TEXT
		if (x == 0) {
			
			// SET TEXT VALUE
			a.setText("Topic");
			b.setText("CutOff");
			c.setText("Higest");
			d.setText("Marks");
			
			// SET TEXT COLOR
			a.setTextColor(Color.WHITE);
			b.setTextColor(Color.WHITE);
			c.setTextColor(Color.WHITE);
			d.setTextColor(Color.WHITE);
			
			// SET TEXT TYPEFACE
			a.setTypeface(_customFontB);
			b.setTypeface(_customFontB);
			c.setTypeface(_customFontB);
			d.setTypeface(_customFontB);

			// SET TEXT BACKGROUND-COLOR
			a.setBackgroundColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			b.setBackgroundColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			c.setBackgroundColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			d.setBackgroundColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
		} else {
			
			// SET TEXT VALUE
			a.setText(col1Subj);
			b.setText(col1Marks);
			c.setText(col1TotleMarks);
			d.setText(String.valueOf(cutOfMarks));
			
			// SET TEXT TYPEFACE
			a.setTypeface(_customFontR);
			b.setTypeface(_customFontR);
			c.setTypeface(_customFontR);
			d.setTypeface(_customFontR);

			// SET TEXT COLOR
			a.setTextColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			b.setTextColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			c.setTextColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			d.setTextColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));

			// SET BACKGROUND COLOR
			a.setBackgroundColor(Color.WHITE);
			b.setBackgroundColor(Color.WHITE);
			c.setBackgroundColor(Color.WHITE);
			d.setBackgroundColor(Color.WHITE);
			
			// ADDED BORDER TO TABLEROW
			a.setBackgroundResource(R.drawable.table_cell_boarder);
			b.setBackgroundResource(R.drawable.table_cell_boarder);
			c.setBackgroundResource(R.drawable.table_cell_boarder);
			d.setBackgroundResource(R.drawable.table_cell_boarder);
		}
		
//		if(x > 0)
//		{
//			marks += Integer.parseInt(col1Marks);
//			totalMarks += Integer.parseInt(col1TotleMarks);			
//		}

		// ADD TEXTVIEW TO TABLEROW
		tableRow.addView(a);
		tableRow.addView(b);
		tableRow.addView(c);
		tableRow.addView(d);

		return tableRow;
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