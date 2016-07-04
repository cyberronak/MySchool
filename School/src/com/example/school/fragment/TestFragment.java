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

public class TestFragment extends Fragment {

	public enum TABLE_TYPE {
		TEST, TOPIC
	}

	boolean arrowBool = true;

	private RecyclerView mRecyclerView;
	private TestAdapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;

	int marks = 0;
	int totalMarks = 0;
	double percentage = 0;
	boolean isExtraRow = true;

	public TestFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_test,
				container, false);
		mRecyclerView = (RecyclerView) rootView
				.findViewById(R.id.dashboard_recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);
		mAdapter = new TestAdapter(getDataSet());
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
			alertDialog.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// positive button logic

						}
					});

			AlertDialog dialog = alertDialog.create();
			// display dialog
			dialog.show();
		}
	};

	private ArrayList<TestData> getDataSet() {
		Bitmap imageArrow = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.arrow_down);

		ArrayList<TestData> testData = new ArrayList<TestData>();
		ArrayList<TestResult> testResult = new ArrayList<TestResult>();

		TestResult rs = new TestResult("Maths", "100", "69");
		testResult.add(rs);
		rs = new TestResult("English", "100", "49");
		testResult.add(rs);

		TestData td = new TestData("Test-1", "5", testResult, imageArrow);
		TestData.tblList.add(createTableLayout(2, 3, td));
		testData.add(td);

		td = new TestData("Test-2", "65", testResult, imageArrow);
		TestData.tblList.add(createTableLayout(2, 3, td));
		testData.add(td);

		return testData;
	}

	/**
	 * Create TableLayout programmatically
	 */
	public TableLayout createTableLayout(int rows, int columns, TestData td) {

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
					rs.getSubjMark(), rs.getSubjTotal(), !isExtraRow));
		}

		// ADD FOOTER
		tableLayout.addView(addTableRow(-1, "Total", String.valueOf(marks),
				String.valueOf(totalMarks), isExtraRow));
		tableLayout.addView(addTableRow(-1, "Percent(%)",
				String.valueOf(marks / td.getResult().size()), "", isExtraRow));
		tableLayout.addView(addTableRow(-1, "Rank", td.getRank(), "",
				isExtraRow));

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
		// SET TEXTVIEW TEXT AND COLOR
		a.setText(title);
		a.setTextColor(Color.WHITE);
		// ADD TEXTVIEW TO TABLEROW
		tableRow.addView(a);
		return tableRow;
	}

	private TableRow addTableRow(int x, String col1Subj, String col1Marks,
			String col1TotleMarks, boolean isExtraRow) {
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

		// CREATE TEXTVIEW
		TextView a = new TextView(getActivity());
		TextView b = new TextView(getActivity());
		TextView c = new TextView(getActivity());

		// SET PARAMS
		a.setLayoutParams(aParams);
		b.setLayoutParams(bParams);
		c.setLayoutParams(cParams);


		// SET PADDING
		a.setPadding(20, 20, 20, 20);
		b.setPadding(20, 20, 20, 20);
		c.setPadding(20, 20, 20, 20);

		// SET TEXTVIEW TEXT
		if (x == 0) {
			a.setText("Subject");
			b.setText("Marks");
			c.setText("TotalMarks");
			
			a.setTextColor(Color.WHITE);
			b.setTextColor(Color.WHITE);
			c.setTextColor(Color.WHITE);
			
			a.setBackgroundColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			b.setBackgroundColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			c.setBackgroundColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
		} else {

			// SET BACKGROUND COLOR
			a.setBackgroundColor(Color.WHITE);
			b.setBackgroundColor(Color.WHITE);
			c.setBackgroundColor(Color.WHITE);

			String str = x > 0 ? String.valueOf(x) + ") " : "";
			a.setText(str + col1Subj);
			b.setText(col1Marks);
			c.setText(col1TotleMarks);
		}

		if (x > 0) {
			marks += Integer.parseInt(col1Marks);
			totalMarks += Integer.parseInt(col1TotleMarks);
		} else {
			a.setTypeface(null, Typeface.BOLD);
			a.setTextColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			b.setTextColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			b.setTypeface(null, Typeface.BOLD);

			c.setTextColor(getActivity().getResources().getColor(
					R.color.colorPrimaryDark));
			c.setTypeface(null, Typeface.BOLD);
		}

		// ADDED BORDER TO TABLEROW
		a.setBackgroundResource(R.drawable.table_cell_boarder);
		b.setBackgroundResource(R.drawable.table_cell_boarder);
		c.setBackgroundResource(R.drawable.table_cell_boarder);

		// ADD TEXTVIEW TO TABLEROW
		tableRow.addView(a);
		tableRow.addView(b);
		tableRow.addView(c);

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