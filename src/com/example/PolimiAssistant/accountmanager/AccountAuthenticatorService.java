package com.example.PolimiAssistant.accountmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * AccountAuthenticatorService.java
 *
 * The authentication service extends the abstract android.app.Service class.
 * An android service forms a component of the main application that signals
 * to Android that the application wants to preform some task that may be long
 * running or not necessarily require user interaction. One example could be a
 * synchronization or in our case authentication. The documentation states that
 * a service can be used by other applications so in our case we are going to have
 * Android's Account manager connect to our application to fetch the users online
 * account details.
 *
 * Authenticator service that returns a subclass of AbstractAccountAuthenticator
 * in onBind(). In our case, the AccountAuthenticator.
 *
 */
public class AccountAuthenticatorService extends Service {

    /**
     * The tag used for the logs.
     */
    private static final String TAG = AccountAuthenticatorService.class.getSimpleName();

    /**
     * Implement the onBind method to return an instance
     * member of your AccountAuthenticator.
     *
     * @param intent
     * @return IBinder
     */
    @Override
    public IBinder onBind(Intent intent) {

        Log.v(AccountAuthenticatorService.TAG, "Binding the service");

        return new AccountAuthenticator(this).getIBinder();
    }
}
