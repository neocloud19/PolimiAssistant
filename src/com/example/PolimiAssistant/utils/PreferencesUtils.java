package com.example.PolimiAssistant.utils;

/**
 * Created by alejo on 4/16/14.
 */

import android.content.Context;
import android.content.SharedPreferences;

import java.util.prefs.Preferences;

/**
 * Class utilized to save the user elemental data persistently.
 */
public class PreferencesUtils {

    /**
     * The tag utilized for the log.
     */
    private static final String TAG = Preferences.class.getSimpleName();

    /**
     * The name of the file utilized to store the data.
     */
    private static final String FILE_NAME = "Preferences";

    /**
     * The set of keys utilized.
     */
    /**
     * The key for the user name.
     */
    private static final String USERNAME_KEY = "username";

    /**
     * The default user name
     */
    public static final String DEFAULT_USERNAME = "";

    /**
     * The context passed by any Android's component.
     */
    private final Context context;

    /**
     * The shared preferences to save/restore the data.
     */
    private final SharedPreferences sharedPreferences;

    /**
     * The editor to save the data.
     */
    private final SharedPreferences.Editor editor;

    /**
     * The main constructor.
     * @param context The context passed by any Android's component.
     */
    public PreferencesUtils(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PreferencesUtils.FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /*
     * User name
     */
    /**
     * Obtains the user name stored in the shared preferences.
     * @return The user name if it has been stored
     *         "" if the user id has not been stored
     */
    public String getUsername() {
        String username = sharedPreferences.getString(PreferencesUtils.USERNAME_KEY, DEFAULT_USERNAME);
        return username;
    }

    /**
     * Stores the user name in the shared preferences.
     * @param username The user name to be stored
     */
    public void setUserName(String username) {
        editor.putString(PreferencesUtils.USERNAME_KEY, username);
        editor.commit();
    }

    /**
     * Remove everything from the shared preferences.
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }
}
