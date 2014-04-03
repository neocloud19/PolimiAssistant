package com.example.PolimiAssitant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.*;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * LoginScreen
 */
public class LoginScreen extends Activity {

    //TODO: Remove this variables when the test is finish
    //Declare the strings that will be carried out to the following intent
    public final static String USERNAME = "com.example.PolimiAssitant.USERNAME";
    public final static String PASSWORD = "com.example.PolimiAssitant.PASSWORD";

    //Declare the context of the application in case of need
    final Context context = this;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //////////////////DISPLAY CONFIGURATIONS///////////////////////

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

        //////////////////ACTION BAR CONFIGURATIONS///////////////////////

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // If your minSdkVersion is 11 or higher, instead use:
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //////////////////LISTENERS///////////////////////

        addListenerOnCheckStorage();
    }

    /**
     * Checks if the user agrees to store the username and
     * password in the device, facing all the possible
     * consequences and liberating the app from any
     * responsibility.
     */
    private void addListenerOnCheckStorage() {


        //1. Search for the checkbox
        final CheckBox check_box;
        check_box = (CheckBox) findViewById(R.id.loginscreen_checkstore);

        //2. Create the listener
        check_box.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String dialog_message;
                String dialog_title;
                String dialog_yes;
                String dialog_no;


                //3. If the box is checked, perform the corresponding actions
                if (((CheckBox) v).isChecked()) {

                    //3.1 Load the acceptance of responsibility strings

                    dialog_message = getResources().getString(R.string.
                            login_storing_responsibility_message);
                    dialog_title = getResources().getString(R.string.
                            login_storing_responsibility_title);
                    dialog_yes = getResources().getString(R.string.
                            login_storing_responsibility_yes);
                    dialog_no = getResources().getString(R.string.
                            login_storing_responsibility_no);

                    //3.1 Alert dialog initialization
                    AlertDialog.Builder responsibilityMessage = new AlertDialog.Builder(context);

                    //3.2 Set dialog title
                    responsibilityMessage.setTitle(dialog_title);

                    //3.3 Set dialog message
                    responsibilityMessage.setMessage(dialog_message);

                    //3.4 Declare dialog settings and actions

                    //Non cancellable dialog
                    responsibilityMessage.setCancelable(false);

                    //If the user accept the responsability
                    responsibilityMessage.setPositiveButton(dialog_yes,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //When clicked close the dialog
                                    dialog.cancel();

                                }
                            });

                    //If the user dont want to accept the responsability
                    responsibilityMessage.setNegativeButton(dialog_no,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //When clicked, uncheck the checkbox and close the dialog
                                    check_box.setChecked(false);
                                    dialog.cancel();

                                }
                            });

                    //4. Create and display the dialog
                    AlertDialog responsibilityDialog = responsibilityMessage.create();
                    responsibilityDialog.show();

                } else {
                    //No message displayed
                }
            }
        });
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
     * Login class that process the login information, login settings and
     * calls the correct classes to handle this.
     *
     * @param view
     *
     * TODO: Add the store key method and save the storing login details if needed
     */
    public void login(View view){

        boolean credentials = true;
        String message;

        //1. Declare the intent of calling the Activity Main menu
        Intent intent = new Intent(this, MainMenu.class);

        //2. Obtain the password and username to make the login
        EditText edittext_username = (EditText)findViewById(R.id.loginscreen_input_username);
        EditText edittext_password = (EditText)findViewById(R.id.loginscreen_input_password);

        //TODO: Test code to see how everything works, erase

        //Obtain the text from the view text
        String username = edittext_username.getText().toString();
        String password = edittext_password.getText().toString();

        //Pass the values to the intent
        intent.putExtra(USERNAME, username);
        intent.putExtra(PASSWORD, password);

        //TODO:

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