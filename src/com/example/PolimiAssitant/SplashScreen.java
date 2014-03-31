package com.example.PolimiAssitant;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {
    /**
     * Called when the activity is first created.
     */

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*
         * Avoid banding problems with the gradient background
         */

        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);

        /*
         * Avoid orientation change and titles in the window for the
         * splash screen.
         */

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen);



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                // This method will be executed once the timer is over
                // Start your app main activity
                Intent goToLogin = new Intent(SplashScreen.this, LoginScreen.class);
                startActivity(goToLogin);

                // close this activity
                finish();

                //Declare the animations needed for the activity
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }

        }, SPLASH_TIME_OUT);

    }
}
