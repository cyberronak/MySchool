package com.example.school;

import java.util.ArrayList;
import java.util.List;

import com.example.school.adapter.LeaderBoardAdapter;
import com.example.school.adapter.NoticeAdapter;
import com.example.school.model.LeaderBoardData;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class RankActivity extends AppCompatActivity {

	private Toolbar toolbar;
	private TextView toolbarTitle;
	private Typeface _customFontR, _customFontB;
	private ArrayList<LeaderBoardData> arrayOfLBData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);

		String activityTitle;
		Bundle extras = getIntent().getExtras();
		if (extras != null)
			activityTitle = extras.getString("RankActivityTitle");
		else
			activityTitle = "Rank";
		
		// load custom fonts
		_customFontB = Typeface.createFromAsset(getAssets(),
				"fonts/American_Typewriter_Bold.ttf");

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
		toolbarTitle.setTypeface(_customFontB);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(
				R.drawable.ic_action_arrow_left);
		toolbarTitle.setText(activityTitle);

		arrayOfLBData = new ArrayList<LeaderBoardData>();
		arrayOfLBData.add(new LeaderBoardData("Pandya Vrushali", "11",
				"163/200", ""));
		arrayOfLBData.add(new LeaderBoardData("Patel Vijay", "54", "126/200",
				""));
		arrayOfLBData.add(new LeaderBoardData("Solanki Ketan", "1", "180/200",
				""));
		arrayOfLBData.add(new LeaderBoardData("Patel payal", "23", "156/200",
				""));
		arrayOfLBData.add(new LeaderBoardData("Virja kalpesh ", "65",
				"133/200", ""));
		arrayOfLBData.add(new LeaderBoardData("josi dhruvil", "34", "173/200",
				""));
		arrayOfLBData
				.add(new LeaderBoardData("Patel Joti", "145", "73/200", ""));
		arrayOfLBData.add(new LeaderBoardData("Pandya Vrushali", "21",
				"152/200", ""));

		ListView listView = (ListView) findViewById(R.id.leader_list_view);

		// Create the adapter to convert the array to views
		LeaderBoardAdapter adapter = new LeaderBoardAdapter(this, arrayOfLBData);
		// Attach the adapter to a ListView
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		finish();
		// closing transition animations
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			// closing transition animations
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
