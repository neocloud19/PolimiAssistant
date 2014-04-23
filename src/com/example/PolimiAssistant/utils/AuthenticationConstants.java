package com.example.PolimiAssistant.utils;

/**
 * AuthenticationConstants.java
 *
 * This class provide the general programming constants, used throughout
 * the application.
 */
public class AuthenticationConstants {

    /**
     * Account type string.
     */
    public static final String ACCOUNT_TYPE = "com.example.PolimiAssistant";

    /**
     * Authtoken type string.
     */
    public static final String AUTHTOKEN_TYPE = "com.example.PolimiAssistant";

    // TODO: AUTHENTICATION CONSTANTS put description of each one
    public static final String PARAM_AUTHTOKEN_TYPE = "auth.token";
    public static final String PARAM_CREATE = "create";
    public static final String EXTRA_REQUEST_CODE = "req.code";

    public static final int REQ_CODE_CREATE = 1;
    public static final int REQ_CODE_UPDATE = 2;

    public static final int RESP_CODE_SUCCESS = 0;
    public static final int RESP_CODE_ERROR = 1;
    public static final int RESP_CODE_CANCEL = 2;
}
