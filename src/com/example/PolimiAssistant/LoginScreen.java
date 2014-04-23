package com.example.PolimiAssistant;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.PolimiAssistant.utils.AccountUtils;
import com.example.PolimiAssistant.utils.AuthenticationConstants;
import com.example.PolimiAssistant.utils.SecurityUtils;

/**
 * LoginScreen
 */
public class LoginScreen extends AccountAuthenticatorActivity{

    /**
     * The tag used for the logs.
     */
    private static final String TAG = MainMenu.class.getSimpleName();

    /**
     * Declare the context of the application
     */
    private final Context context = this;

    /**
     * The user name input by the user.
     */
    private EditText usernameEditText;

    /**
     * The password input by the user.
     */
    private EditText passwordEditText;

    /**
     * The response passed by the service.
     * It is used to give the user name and the password to the account manager
     */
    private AccountAuthenticatorResponse response;

    /**
     * The account manager used to request and add account.
     */
    private AccountManager accountManager;

    //TODO: Remove this variables when the test is finish
    //Declare the strings that will be carried out to the following intent
    public final static String USERNAME = "com.example.PolimiAssitant.USERNAME";
    public final static String PASSWORD = "com.example.PolimiAssitant.PASSWORD";
    //TODO:

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*
         * HERE CONFIGURE THE DIPLAYING OPTIONS
         */

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

        /*
         * getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         * If your minSdkVersion is 11 or higher, instead use:
         */
        getActionBar().setDisplayHomeAsUpEnabled(true);

        /*
         * LISTENERS
         */

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
        final CheckBox checkBox;
        checkBox = (CheckBox) findViewById(R.id.loginscreen_checkstore);

        //2. Create the listener
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String dialogMessage;
                String dialogTitle;
                String dialogYes;
                String dialogNo;


                //3. If the box is checked, perform the corresponding actions
                if (((CheckBox) v).isChecked()) {

                    //3.1 Load the acceptance of responsibility strings

                    dialogMessage = getResources().getString(R.string.
                            login_storing_responsibility_message);
                    dialogTitle = getResources().getString(R.string.
                            login_storing_responsibility_title);
                    dialogYes = getResources().getString(R.string.
                            login_storing_responsibility_yes);
                    dialogNo = getResources().getString(R.string.
                            login_storing_responsibility_no);

                    //3.1 Alert dialog initialization
                    AlertDialog.Builder responsibilityMessage = new AlertDialog.Builder(context);

                    //3.2 Set dialog title
                    responsibilityMessage.setTitle(dialogTitle);

                    //3.3 Set dialog message
                    responsibilityMessage.setMessage(dialogMessage);

                    //3.4 Declare dialog settings and actions

                    //Non cancellable dialog
                    responsibilityMessage.setCancelable(false);

                    //If the user accept the responsability
                    responsibilityMessage.setPositiveButton(dialogYes,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //When clicked close the dialog
                                    dialog.cancel();

                                }
                            });

                    //If the user dont want to accept the responsability
                    responsibilityMessage.setNegativeButton(dialogNo,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //When clicked, uncheck the checkbox and close the dialog
                                    checkBox.setChecked(false);
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


    /**
     * Login class that process the login information, login settings and
     * calls the correct classes to handle this.
     */
    public void login(View view){

        boolean validInput = true;

        //1. Declare the intent of calling the Activity Main menu
        Intent goToMainMenu = new Intent(this, MainMenu.class);

        //2. Obtain the password and username to make the login

        //2.1 Get the text view info
        usernameEditText = (EditText)findViewById(R.id.loginscreen_input_username);
        passwordEditText = (EditText)findViewById(R.id.loginscreen_input_password);

        //2.2 Extract the string from the textview
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        //Pass the values to the intent
        goToMainMenu.putExtra(USERNAME, username);
        goToMainMenu.putExtra(PASSWORD, password);

        //TODO:Debug
        Log.d(TAG, "The username is: "+username+" and the password is: "+password);

        //TODO: Complete the credential validation process

        // 3 Test the validity of the credentials, client side

        // 3.1 Check that the user name isnt empty
        if (username == null || username.equalsIgnoreCase("")) {

            Toast.makeText(context, getResources().getString(R.string.warning_username_empty),
                    Toast.LENGTH_LONG).show();
            validInput = false;

            return;
        }

        // 3.2 Check that the password isnt empty
        if (password == null || password.equalsIgnoreCase("")) {
            Toast.makeText(context, getResources().getString(R.string.warning_password_empty),
                    Toast.LENGTH_LONG).show();
            validInput = false;
            return;
        }

        //4 Test the validity of the credentials, server side

        //If the input was valid
        if(validInput){

            //TODO: Implement the code to test the credentials on the server

            //4.2 Check the username and password against the server

            /*
             * TODO: Implement method to communicate with the server.
             *
             * It has to have a method that given username and password
             * return a token or a null in order to know that something
             * went wrong with the login or manage the token
             *
             */

            final String oAuthToken;
            oAuthToken = "getOAuthToken(inputs...)";

            //TODO:Debug
            Log.d(TAG,"The token is: "+oAuthToken);

            //If the server give us an authorization token correctly
            if(oAuthToken != null){

                //4.2 Get the account manager and check if the account already exist
                accountManager = AccountManager.get(context);

                //TODO:Debug
                Log.d(TAG,"Check if account exist ");

                //TODO: Check if the data should be stored or not

                //If account exist
                if(AccountUtils.getUserAccount(context,username) != null){

                    //TODO: Insert the new authorization token and warn the user about changing the token
                    Toast.makeText(context, R.string.warning_account_already_exist,
                            Toast.LENGTH_LONG).show();

                    //Inform the user and finish the activity
                    Toast.makeText(context, R.string.info_add_account_successful, Toast.LENGTH_LONG).show();

                }else{

                    //4.3 Create new account
                    Account newUserAccount = new Account(username, AuthenticationConstants.ACCOUNT_TYPE);

                    //Try to bundle the new account
                    try{

                        String encryptedPassword = SecurityUtils.encryptToHex(password);
                        boolean accountCreated;

                        //TODO: Decide which method will be included, insert the password or just the token

                        //4.4 Add the account to the account manager
                        accountCreated = accountManager.addAccountExplicitly(newUserAccount,
                                encryptedPassword,null);

                        //4.5 Add the token to the account
                        accountManager.setAuthToken(newUserAccount,
                                AuthenticationConstants.PARAM_AUTHTOKEN_TYPE,oAuthToken);

                        //If the account was created successfully
                        if(accountCreated){

                            //4.6 Get the intent and check if its coming from the splash screen or not
                            Intent incomingIntent = getIntent();
                            String comingFrom = incomingIntent.getStringExtra("COMING_FROM");

                            //If its coming from the splash screen(default case)
                            if(comingFrom != null){

                                //4.6.1 If it comes from the splash screen sync the info to the DB
                                //TODO: RetrieveDataFromServer(...) , maybe here , maybe initiating the menu.

                                //4.6.2 Redirect the activity to the main menu
                                startActivity(goToMainMenu);

                            }else{

                                //4.6.3 If it comes from the account manager

                                //Get the intent extras
                                Bundle extras = getIntent().getExtras();

                                //Extract the response
                                AccountAuthenticatorResponse response = extras.getParcelable(
                                        AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);

                                //New info to give to the account manager
                                Bundle result = new Bundle();

                                //Bundle the data in order to give it to the account manager
                                result.putString(AccountManager.KEY_ACCOUNT_NAME, username);
                                result.putString(AccountManager.KEY_ACCOUNT_TYPE,
                                        AuthenticationConstants.ACCOUNT_TYPE);
                                response.onResult(result);

                                //Inform the user and finish the activity
                                Toast.makeText(context, R.string.info_add_account_successful, Toast.LENGTH_LONG).show();
                                finish();
                            }

                        }else{
                            Toast.makeText(context,R.string.error_creating_account,Toast.LENGTH_SHORT);
                        }


                    }catch(Exception e){

                        Log.e(TAG, e.getLocalizedMessage(), e);
                        Toast.makeText(context,R.string.error_creating_account,Toast.LENGTH_SHORT);
                    }

                }

            }else{

                //Error in login
                Toast.makeText(context, R.string.error_invalid_credentials,
                        Toast.LENGTH_LONG).show();
            }

        }else{

            //TODO:In case it is not valid, show the message

        }
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
}