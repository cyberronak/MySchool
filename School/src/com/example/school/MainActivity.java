package com.example.school;

import java.lang.reflect.Field;

import com.example.school.customcalendar.CalendarFragment;
import com.example.school.drawer.FragmentDrawer;
import com.example.school.drawer.FragmentDrawer.FragmentDrawerListener;
import com.example.school.fragment.AboutFragment;
import com.example.school.fragment.ExamFragment;
import com.example.school.fragment.NoticeFragment;
import com.example.school.fragment.AttendenceFragment;
import com.example.school.fragment.RemarkFragment;
import com.example.school.fragment.StudentCornerFragment;
import com.example.school.fragment.LeaderboardFragment;
import com.example.school.fragment.TestFragment;
import com.example.school.fragment.TopicFragment;
import com.example.school.utility.StringConst;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
		FragmentDrawerListener {

	private static String TAG = MainActivity.class.getSimpleName();

	private Toolbar toolbar;
	private FragmentDrawer drawerFragment;
	private SharedPreferences shpref;
	private TextView toolbarTitle;
	private Typeface _customFontR, _customFontB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// load custom fonts
		_customFontB = Typeface.createFromAsset(getAssets(),
				"fonts/American_Typewriter_Bold.ttf");

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
		toolbarTitle.setTypeface(_customFontB);
		
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		drawerFragment = (FragmentDrawer) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_navigation_drawer);
		drawerFragment.setUp(R.id.fragment_navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
		drawerFragment.setDrawerListener(this);

		// display the first navigation drawer view on app launch
		displayView(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDrawerItemSelected(View view, int position) {
		// TODO Auto-generated method stub
		displayView(position);
	}

	private void displayView(int position) {
		Fragment fragment = null;
		String title = getString(R.string.app_name);

		switch (position) {
		case 0:
			fragment = new NoticeFragment();
			title = getString(R.string.title_notice);
			break;
		case 1:
			fragment = new AttendenceFragment();
			title = getString(R.string.title_attendence);
			break;
//		case 2:
//			fragment = new StudentCornerFragment();
//			title = getString(R.string.title_student_corner);
//			break;
		case 3:
			fragment = new TestFragment();
			title = getString(R.string.title_testview);
			break;
		case 4:
			fragment = new ExamFragment();
			title = getString(R.string.title_exam);
			break;
		case 5:
			fragment = new TopicFragment();
			title = getString(R.string.title_topic);
			break;
		case 6:
			fragment = new RemarkFragment();
			title = getString(R.string.title_remark);
			break;
		case 7:
			fragment = new LeaderboardFragment();
			title = getString(R.string.title_leaderboard);
			break;
		case 8:
			fragment = new AboutFragment();
			title = getString(R.string.title_about);
			break;
		case 9:
			shpref = getSharedPreferences(StringConst.My_PREFERENCES,
					MODE_PRIVATE);
			shpref.edit().clear().commit();

			Intent intent = new Intent(getApplicationContext(),
					SchoolAuthentication.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			Toast.makeText(getApplicationContext(), "Successfully logout.",
					Toast.LENGTH_SHORT).show();
			finish();
			// closing transition animations
			overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.replace(R.id.container_body, fragment);
			fragmentTransaction.commit();

			toolbarTitle.setText(title);
		}
	}
}
