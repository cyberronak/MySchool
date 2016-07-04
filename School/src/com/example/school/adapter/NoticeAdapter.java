package com.example.school.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.school.R;
import com.example.school.model.NoticeData;
import com.example.school.utility.ConstantUtility;

public class NoticeAdapter extends ArrayAdapter<NoticeData> {
	private Typeface _customFontR, _customFontB;

	public NoticeAdapter(Context context, ArrayList<NoticeData> data) {
        super(context, 0, data);
    	// load custom fonts
		_customFontR = Typeface.createFromAsset(context.getAssets(),
				"fonts/American_Typewriter_Regular.ttf");
		_customFontB = Typeface.createFromAsset(context.getAssets(),
				"fonts/American_Typewriter_Bold.ttf");
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {

    	 // Get the data item for this position
    	 NoticeData data = getItem(position);    
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.notice_list_row, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.notice_title);
        tvTitle.setTypeface(_customFontB);
        TextView tvSubTitle = (TextView) convertView.findViewById(R.id.notice_sub_title);
        tvSubTitle.setTypeface(_customFontR);
        TextView tvDate = (TextView) convertView.findViewById(R.id.notice_date);
        tvDate.setTypeface(_customFontR);
        
        // Populate the data into the view using the data object
        tvTitle.setText(data.getTitle());
        tvSubTitle.setText(data.getSub_title());
        tvDate.setText(ConstantUtility.getDateFormDate(data.getDate()));

        // Return the completed view to render on screen
        return convertView;
    }
 }