package com.example.PolimiAssistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
 * SplashScreen.java
 *
 * This is the entry point of the application. In here the applications decides
 * if the user will go to the main menu or to the login screen, by verifying the
 * existence of an account.
 */
public class SplashScreen extends Activity {

    /**
     * Log tag
     */
    private static final String TAG = SplashScreen.class.getSimpleName();

    /**
     * Declare the context of the application
     */
    private final Context context = this;

    /**
     * Time that the splash screen will last
     */
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


        /*
         * This method allows the splash screen to last more time showing
         * the logo of the application
         */
        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {

                // This method will be executed once the timer is over
                // Start your app main activity
                Intent goToLogin = new Intent(SplashScreen.this, LoginScreen.class);

                //Send the intent id to know where is it coming from
                goToLogin.putExtra("COMING_FROM", context.getClass().getSimpleName());
                startActivity(goToLogin);

                // close this activity
                finish();

                //Declare the animations needed for the activity
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }

        }, SPLASH_TIME_OUT);

    }
}
