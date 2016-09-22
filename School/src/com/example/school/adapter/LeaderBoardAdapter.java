package com.example.school.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.school.R;
import com.example.school.customcalendar.CircleImageViewTransform;
import com.example.school.model.LeaderBoardData;
import com.example.school.utility.ConstantUtility;
import com.squareup.picasso.Picasso;

public class LeaderBoardAdapter extends ArrayAdapter<LeaderBoardData> {
	private Typeface _customFontR, _customFontB;
	private Context mContext;
	
	public LeaderBoardAdapter(Context context, ArrayList<LeaderBoardData> data) {
        super(context, 0, data);
    	// load custom fonts
        mContext = context;
		_customFontR = Typeface.createFromAsset(context.getAssets(),
				"fonts/American_Typewriter_Regular.ttf");
		_customFontB = Typeface.createFromAsset(context.getAssets(),
				"fonts/American_Typewriter_Bold.ttf");
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {

    	 // Get the data item for this position
    	 LeaderBoardData data = getItem(position);    
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.leaderboard_list_row, parent, false);
        }
        // Lookup view for data population
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.iv_user_image);
        
        TextView listCount = (TextView) convertView.findViewById(R.id.list_count);
        listCount.setTypeface(_customFontB);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_user_name);
        tvName.setTypeface(_customFontB);
        TextView tvRank = (TextView) convertView.findViewById(R.id.tv_user_rank);
        tvRank.setTypeface(_customFontR);
        TextView tvMarks = (TextView) convertView.findViewById(R.id.tv_user_marks);
        tvMarks.setTypeface(_customFontR);
        
        // Populate the data into the view using the data object
        listCount.setText(""+(position+1));
        tvName.setText(ConstantUtility.toCamelCase(data.getSt_name()));
        tvRank.setText(data.getSt_rank());
        tvMarks.setText(data.getSt_marks());

        if(data.getSt_image()!="")
        {
    		Picasso.with(mContext).load(data.getSt_image())
			.placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .resize(90,90)         
			.transform(new CircleImageViewTransform()).into(ivImage);
        }
        else
        {
        	Bitmap image = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.demo);
            ivImage.setImageBitmap(new CircleImageViewTransform().transform(image));
        }
        
        //        tvTitle.setText(data.getTitle());
//        tvSubTitle.setText(data.getSub_title());
//        tvDate.setText(data.getDate());

        // Return the completed view to render on screen
        return convertView;
    }
 }