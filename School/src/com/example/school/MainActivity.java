package com.example.school;

import com.example.school.customcalendar.CalendarFragment;
import com.example.school.drawer.FragmentDrawer;
import com.example.school.drawer.FragmentDrawer.FragmentDrawerListener;
import com.example.school.fragment.AboutFragment;
import com.example.school.fragment.AnnouncementFragment;
import com.example.school.fragment.AttendenceFragment;
import com.example.school.fragment.HomeFragment;
import com.example.school.fragment.LeaderboardFragment;
import com.example.school.fragment.SettingFragment;
import com.example.school.fragment.TestFragment;
import com.example.school.utility.StringConst;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
		FragmentDrawerListener {

	private static String TAG = MainActivity.class.getSimpleName();

	private Toolbar toolbar;
	private FragmentDrawer drawerFragment;
	private SharedPreferences shpref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

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
			fragment = new HomeFragment();
			title = getString(R.string.title_home);
			break;
		case 1:
			fragment = new AnnouncementFragment();
			title = getString(R.string.title_announcement);
			break;
		case 2:
			fragment = new TestFragment();
			title = getString(R.string.title_test);
			break;
		case 3:
			fragment = new AttendenceFragment();
			title = getString(R.string.title_attendence);
//			startActivity(new Intent(getApplicationContext(), CalendarFragment.class));
			break;
		case 4:
			fragment = new LeaderboardFragment();
			title = getString(R.string.title_leaderboard);
			break;
		case 5:
			fragment = new SettingFragment();
			title = getString(R.string.title_settings);
			break;
		case 6:
			fragment = new AboutFragment();
			title = getString(R.string.title_about);
			break;
		case 7:
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

			// set the toolbar title
			getSupportActionBar().setTitle(title);
		}
	}
}
