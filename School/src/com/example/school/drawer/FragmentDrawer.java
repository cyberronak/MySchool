package com.example.school.drawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.school.R;
import com.example.school.adapter.NavigationDrawerAdapter;
import com.example.school.customcalendar.CircleImageViewTransform;
import com.example.school.model.NavDrawerItem;
import com.example.school.model.StudentData;
import com.example.school.utility.ConstantUtility;
import com.example.school.utility.StringConst;
import com.squareup.picasso.Picasso;

public class FragmentDrawer extends Fragment {

	public static final String ROLL_NUMBER = "Roll Number : ";

	private static String TAG = FragmentDrawer.class.getSimpleName();

	private RecyclerView recyclerView;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private NavigationDrawerAdapter adapter;
	private View containerView;
	private static TypedArray icons = null;
	private static String[] titles = null;
	private FragmentDrawerListener drawerListener;
	private SharedPreferences shpref;

	public FragmentDrawer() {

	}

	public void setDrawerListener(FragmentDrawerListener listener) {
		this.drawerListener = listener;
	}

	public static List<NavDrawerItem> getData() {
		List<NavDrawerItem> data = new ArrayList<NavDrawerItem>();

		// preparing navigation drawer items
		for (int i = 0; i < titles.length; i++) {
			NavDrawerItem navItem = new NavDrawerItem();
			navItem.setIcon(icons.getDrawable(i));
			navItem.setTitle(titles[i]);
			data.add(navItem);
		}
		return data;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// drawer images
		icons = getActivity().getResources().obtainTypedArray(
				R.array.nav_drawer_image);
		// drawer labels
		titles = getActivity().getResources().getStringArray(
				R.array.nav_drawer_labels);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflating view layout
		View layout = inflater.inflate(R.layout.fragment_navigation_drawer,
				container, false);

		shpref = getActivity().getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);
		String prefRollNo = shpref.getString(StringConst.STUDENT_ROLL_NO,"");
		String prefName = shpref.getString(StringConst.STUDENT_FNAME,"") +" "+ shpref.getString(StringConst.STUDENT_LNAME,"");
		String prefMobil = shpref.getString(StringConst.STUDENT_MOBILE_NO,"");
		String prefImage = shpref.getString(StringConst.STUDENT_IMAGE,"");

		// Drawer student Image
		ImageView studentLogo = (ImageView) layout.findViewById(R.id.user_logo);
		Picasso.with(getActivity()).load(prefImage)
				.placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .resize(90,90)         
				.transform(new CircleImageViewTransform()).into(studentLogo);

		// Drawer Student Name
		TextView studentName = (TextView) layout.findViewById(R.id.user_title);
		studentName.setText(ConstantUtility.toCamelCase(prefName));

		// Drawer Roll-no
		TextView rollno = (TextView) layout.findViewById(R.id.user_roll_no);
		rollno.setText(ROLL_NUMBER + prefRollNo);

		// Drawer Mobile-no
		TextView mobileno = (TextView) layout.findViewById(R.id.user_mobile_no);
		mobileno.setText(prefMobil);

		recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

		adapter = new NavigationDrawerAdapter(getActivity(), getData());
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.addOnItemTouchListener(new RecyclerTouchListener(
				getActivity(), recyclerView, new ClickListener() {
					@Override
					public void onClick(View view, int position) {
						if(position != 2)
						{
							drawerListener.onDrawerItemSelected(view, position);
							mDrawerLayout.closeDrawer(containerView);							
						}
					}

					@Override
					public void onLongClick(View view, int position) {

					}
				}));

		return layout;
	}

	public void setUp(int fragmentId, DrawerLayout drawerLayout,
			final Toolbar toolbar) {
		containerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout,
				toolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				toolbar.setAlpha(1 - slideOffset / 2);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});
		mDrawerToggle.setDrawerIndicatorEnabled(false);
		mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_action_menuicon);
		mDrawerToggle
				.setToolbarNavigationClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
							mDrawerLayout.closeDrawer(Gravity.START);
						} else {
							mDrawerLayout.openDrawer(Gravity.START);
						}
					}
				});
	}

	public static interface ClickListener {
		public void onClick(View view, int position);

		public void onLongClick(View view, int position);
	}

	static class RecyclerTouchListener implements
			RecyclerView.OnItemTouchListener {

		private GestureDetector gestureDetector;
		private ClickListener clickListener;

		public RecyclerTouchListener(Context context,
				final RecyclerView recyclerView,
				final ClickListener clickListener) {
			this.clickListener = clickListener;
			gestureDetector = new GestureDetector(context,
					new GestureDetector.SimpleOnGestureListener() {
						@Override
						public boolean onSingleTapUp(MotionEvent e) {
							return true;
						}

						@Override
						public void onLongPress(MotionEvent e) {
							View child = recyclerView.findChildViewUnder(
									e.getX(), e.getY());
							if (child != null && clickListener != null) {
								clickListener.onLongClick(child,
										recyclerView.getChildPosition(child));
							}
						}
					});
		}

		@Override
		public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

			View child = rv.findChildViewUnder(e.getX(), e.getY());
			if (child != null && clickListener != null
					&& gestureDetector.onTouchEvent(e)) {
				clickListener.onClick(child, rv.getChildPosition(child));
			}
			return false;
		}

		@Override
		public void onTouchEvent(RecyclerView rv, MotionEvent e) {
		}

		@Override
		public void onRequestDisallowInterceptTouchEvent(
				boolean disallowIntercept) {

		}

	}

	public interface FragmentDrawerListener {
		public void onDrawerItemSelected(View view, int position);
	}
}
