package com.example.school;

import com.example.school.utility.StringConst;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity{
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    
    private TextView appName;
	private Typeface custom_font;
	private SharedPreferences _shpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.splash_screen);
		_shpref = getSharedPreferences(StringConst.My_PREFERENCES,
				Context.MODE_PRIVATE);

		// load custom fonts
		custom_font = Typeface.createFromAsset(getAssets(), "fonts/American_Typewriter_Regular.ttf");
		
    	appName=(TextView)findViewById(R.id.tv_splash_app_name);
    	appName.setTypeface(custom_font);
    	
		//opening transition animations
		overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_right);

    	new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
            	String state = _shpref.getString(StringConst.MY_STATE, "");
				String city = _shpref.getString(StringConst.MY_CITY, "");
				String school = _shpref.getString(StringConst.MY_SCHOOL, "");
				int school_id = _shpref.getInt(StringConst.MY_SCHOOL_ID, 0);
				int student_id = _shpref.getInt(StringConst.STUDENT_ID,0);
				
				Intent intent;
            	if(state != "" && city != "" && school != "" && school_id != 0 && student_id != 0)
            	{
					// Old Login
                    intent = new Intent(SplashScreen.this, MainActivity.class);
                	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    				
            	}
            	else
                	// Fresh Login
                    intent = new Intent(SplashScreen.this, SchoolAuthentication.class);
            	
                startActivity(intent);
        
                // close this activity
                finish();
        
				//closing transition animations
        		overridePendingTransition(R.anim.slide_in_left,
        				R.anim.slide_out_right); 
        	}
        }, SPLASH_TIME_OUT);
    }
}
