package it.pagopa.swclient.mil.debtposition.utils;

import lombok.Getter;

@Getter
public final class ErrorCodes {
    private ErrorCodes() {
    }

    public static final String MODULE_ID = "00DP";

    /*
     * Validation errors code from 000001 to 000199
     */
    public static final String ERROR_REQUESTID_MUST_NOT_BE_NULL                                  = MODULE_ID + "000001";
    public static final String ERROR_AUTHORIZATION_MUST_NOT_BE_NULL                              = MODULE_ID + "000002";
    public static final String ERROR_CLIENT_ID_MUST_NOT_BE_NULL                                   = MODULE_ID + "000003";
    public static final String ERROR_SECRET_ID_MUST_NOT_BE_NULL                                   = MODULE_ID + "000004";
    public static final String ERROR_GRANT_TYPE_MUST_NOT_BE_NULL                                  = MODULE_ID + "000005";

    /*
     * Service errors code from 000200 to 000500
     */
    public static final String ERROR_GENERIC_TOKEN                                                 = MODULE_ID + "000200";
    public static final String ERROR_GENERIC_TOKEN_INFO                                            = MODULE_ID + "000201";

    /*
     * Error descriptions
     */
    private static final String ERROR_REQUESTID_MUST_NOT_BE_NULL_DESCR = "RequestId must not be null";
    private static final String ERROR_AUTHORIZATION_MUST_NOT_BE_NULL_DESCR = "Authorization must not be null";
    private static final String ERROR_CLIENT_ID_MUST_NOT_BE_NULL_DESCR = "clientId must not be null";
    private static final String ERROR_SECRET_ID_MUST_NOT_BE_NULL_DESCR = "secretId must not be null";
    private static final String ERROR_GRANT_TYPE_MUST_NOT_BE_NULL_DESCR = "grantType must not be null";

    private static final String ERROR_GENERIC_TOKEN_DESCR = "unexpected error occurred during get token";
    private static final String ERROR_GENERIC_TOKEN_INFO_DESCR = "unexpected error occurred during get token info";

    /*
     * Error complete message
     */
    public static final String ERROR_REQUESTID_MUST_NOT_BE_NULL_MSG = "[" + ERROR_REQUESTID_MUST_NOT_BE_NULL + "] " + ERROR_REQUESTID_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_AUTHORIZATION_MUST_NOT_BE_NULL_MSG = "[" + ERROR_AUTHORIZATION_MUST_NOT_BE_NULL + "] " + ERROR_AUTHORIZATION_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_CLIENT_ID_MUST_NOT_BE_NULL_MSG = "[" + ERROR_CLIENT_ID_MUST_NOT_BE_NULL + "] " + ERROR_CLIENT_ID_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_SECRET_ID_MUST_NOT_BE_NULL_MSG = "[" + ERROR_SECRET_ID_MUST_NOT_BE_NULL + "] " + ERROR_SECRET_ID_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_GRANT_TYPE_MUST_NOT_BE_NULL_MSG = "[" + ERROR_GRANT_TYPE_MUST_NOT_BE_NULL + "] " + ERROR_GRANT_TYPE_MUST_NOT_BE_NULL_DESCR;
    public static final String ERROR_GENERIC_TOKEN_MSG = "[" + ERROR_GENERIC_TOKEN + "] " + ERROR_GENERIC_TOKEN_DESCR;
    public static final String ERROR_GENERIC_TOKEN_INFO_MSG = "[" + ERROR_GENERIC_TOKEN_INFO + "] " + ERROR_GENERIC_TOKEN_INFO_DESCR;

}
