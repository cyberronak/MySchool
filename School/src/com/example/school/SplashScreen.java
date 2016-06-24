package com.example.school;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.splash_screen);

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
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, SchoolAuthentication.class);
                startActivity(i);
        
                // close this activity
                finish();
        
				//closing transition animations
        		overridePendingTransition(R.anim.slide_in_left,
        				R.anim.slide_out_right);

            }
        }, SPLASH_TIME_OUT);
    }
}
