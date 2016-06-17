package com.example.school.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
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
import com.example.school.model.AnnouncementData;
import com.example.school.utility.ConstantUtility;

public class AnnouncementAdapter extends ArrayAdapter<AnnouncementData> {
    public AnnouncementAdapter(Context context, ArrayList<AnnouncementData> data) {
        super(context, 0, data);
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
    	 AnnouncementData data = getItem(position);    
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.announcement_list_row, parent, false);
        }
        // Lookup view for data population
        View verticalLine=(View)convertView.findViewById(R.id.vertical_line);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.news_title);
        TextView tvSubTitle = (TextView) convertView.findViewById(R.id.news_sub_title);
        TextView tvDate = (TextView) convertView.findViewById(R.id.news_date);
        TextView tvTime = (TextView) convertView.findViewById(R.id.news_time);
        
        // Populate the data into the view using the data object
        verticalLine.setBackgroundColor(Color.parseColor(ConstantUtility.getNewLineColor(data.getType())));
        tvTitle.setText(data.getTitle());
        tvSubTitle.setText(data.getSub_title());
        tvDate.setText(ConstantUtility.getDateFormDate(data.getDate()));
        tvTime.setText(ConstantUtility.getTimeFormDate(data.getDate()));

        // Return the completed view to render on screen
        return convertView;
    }
 }