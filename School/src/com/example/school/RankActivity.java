package com.example.school;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class RankActivity extends AppCompatActivity {

	private Toolbar toolbar;
	private TextView toolbarTitle;
	private Typeface _customFontR, _customFontB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		// load custom fonts
		_customFontB = Typeface.createFromAsset(getAssets(),
				"fonts/American_Typewriter_Bold.ttf");

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
		toolbarTitle.setTypeface(_customFontB);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_arrow_left);
		toolbarTitle.setText(R.string.title_rank);
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
