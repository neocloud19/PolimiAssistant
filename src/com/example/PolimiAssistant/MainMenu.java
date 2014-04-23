package com.example.PolimiAssistant;

import android.app.Activity;
import android.os.Bundle;
import com.example.PolimiAssistant.R;

/**
 * MainMenu.java
 *
 * This is the entry point of the application. In here the applications decides
 * if the user will go to the
 */
public class MainMenu extends Activity {

    /**
     * The tag used for the logs.
     */
    private static final String TAG = MainMenu.class.getSimpleName();

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // If your minSdkVersion is 11 or higher, instead use:
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}