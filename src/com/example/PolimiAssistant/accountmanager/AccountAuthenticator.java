package com.example.PolimiAssistant.accountmanager;

import android.accounts.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.PolimiAssistant.utils.AuthenticationConstants;
import com.example.PolimiAssistant.LoginScreen;

/**
 * AccountAuthenticator.java
 *
 */
public class AccountAuthenticator extends AbstractAccountAuthenticator {

    /**
     * The tag used for the logs.
     */
    private static final String TAG = AccountAuthenticator.class.getSimpleName();

    /**
     * Activity context
     */
    private final Context context;


    public AccountAuthenticator(Context context){
        super(context);
        this.context = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }


    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response,
                             String accountType,
                             String authTokenType,
                             String[] requiredFeatures,
                             Bundle options) throws NetworkErrorException {

        Log.d(AccountAuthenticator.TAG, "Adding new account");
        Log.d(AccountAuthenticator.TAG, "The auth token type is " + authTokenType);

        final Bundle reply;
        final Intent intent;

        reply = new Bundle();
        intent = new Intent(this.context, LoginScreen.class);

        intent.putExtra(AuthenticationConstants.AUTHTOKEN_TYPE, authTokenType);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        reply.putParcelable(AccountManager.KEY_INTENT, intent);

        return reply;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }
}
