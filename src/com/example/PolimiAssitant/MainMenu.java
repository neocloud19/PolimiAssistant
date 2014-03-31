package com.example.PolimiAssitant;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by alejo on 3/15/14.
 */
public class MainMenu extends Activity {
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // If your minSdkVersion is 11 or higher, instead use:
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}