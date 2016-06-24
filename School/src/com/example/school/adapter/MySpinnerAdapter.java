package com.example.school.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MySpinnerAdapter extends ArrayAdapter<String> {
	// Initialise custom font, for example:
	// load custom fonts
	private Typeface custom_font = Typeface.createFromAsset(getContext()
			.getAssets(), "fonts/American_Typewriter_Regular.ttf");

	// (In reality I used a manager which caches the Typeface objects)
	// Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

	public MySpinnerAdapter(Context context, int resource, List<String> items) {
		super(context, resource, items);
	}

	// Affects default (closed) state of the spinner
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) super.getView(position, convertView, parent);
		view.setTypeface(custom_font);
		return view;
	}

	// Affects opened state of the spinner
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) super.getDropDownView(position, convertView,
				parent);
		view.setTypeface(custom_font);
		return view;
	}
}
