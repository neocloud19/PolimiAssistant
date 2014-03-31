package com.example.PolimiAssitant;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

/**
 * LoginScreen
 */
public class LoginScreen extends Activity {

    public final static String USERNAME = "com.example.PolimiAssitant.USERNAME";
    public final static String PASSWORD = "com.example.PolimiAssitant.PASSWORD";

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
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.login_screen);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // If your minSdkVersion is 11 or higher, instead use:
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Login class that process the login information and
     * calls the correct classes to handle this.
     *
     * @param view
     */
    public void login(View view){

        boolean credentials = true;
        String message;

        //1. Declare the intent of calling the Activity Main menu
        Intent intent = new Intent(this, MainMenu.class);

        //2. Obtain the password and username to make the login
        EditText edittext_username = (EditText)findViewById(R.id.loginscreen_input_username);
        EditText edittext_password = (EditText)findViewById(R.id.loginscreen_input_password);

        //TODO: Test code to see how everything works

        //Obtain the text from the view text
        String username = edittext_username.getText().toString();
        String password = edittext_password.getText().toString();

        //Pass the values to the intent
        intent.putExtra(USERNAME, username);
        intent.putExtra(PASSWORD, password);

        //3. Test the validity of the credentials

        if(credentials){

            //3.1 Load the acceptance message
            message = getResources().getString(R.string.valid_credentials);
            //3.2 Display the message
            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();

            //4. Start the new activity
            startActivity(intent);

        }else{

        }
    }
}