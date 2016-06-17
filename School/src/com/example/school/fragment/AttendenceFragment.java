package com.example.school.fragment;

import com.example.school.R;
import com.example.school.customcalendar.CalendarView;
import com.example.school.model.CalendarData.EventType;
import com.example.school.utility.ConstantUtility;
import com.example.school.utility.ConstantUtility.BG_ROUND;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AttendenceFragment extends Fragment {
 
    public AttendenceFragment() {
        // Required empty public constructor
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attendence, container, false);

        CalendarView cv = ((CalendarView)rootView.findViewById(R.id.calendar_view));
   
        ImageView ivExam = ((ImageView)rootView.findViewById(R.id.iv_exam));
        ImageView ivEvent = ((ImageView)rootView.findViewById(R.id.iv_event));
        ImageView ivHoliday = ((ImageView)rootView.findViewById(R.id.iv_holiyday));

        setBackGroundColor(ivExam, EventType.Exam);
        setBackGroundColor(ivEvent, EventType.Event);
        setBackGroundColor(ivHoliday, EventType.Holiday);

        // Inflate the layout for this fragment
        return rootView;
    }
 
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
 
    @Override
    public void onDetach() {
        super.onDetach();
    }
    
    private void setBackGroundColor(ImageView image,EventType type) {
		String mColorCode = ConstantUtility.getColorCodeFromEventType(type,BG_ROUND.DARK_COLOR);
		String lighColor= ConstantUtility.getColorCodeFromEventType(type,BG_ROUND.LIGT_COLOR);
		
		StateListDrawable drawable = (StateListDrawable)image.getBackground();
		DrawableContainerState dcs = (DrawableContainerState)drawable.getConstantState();
		Drawable[] drawableItems = dcs.getChildren();
		GradientDrawable bgShape = (GradientDrawable)drawableItems[0];// item 1 
		
		//solid color
		bgShape.setColor(Color.parseColor(mColorCode));
		//stroke
		bgShape.setStroke(3, Color.parseColor(lighColor));
		
	}
}